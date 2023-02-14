package com.c211.opinbackend.repo.service.s3;

import static com.c211.opinbackend.exception.repositroy.RepositoryExceptionEnum.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.c211.opinbackend.exception.repositroy.RepositoryRuntimeException;
import com.c211.opinbackend.repo.model.response.PostImageUploadResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadService {
	static private final String POST_DIR = "post";
	static private final String IMAGE_NAME = "image";
	private final AmazonS3Client amazonS3Client;
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public PostImageUploadResponse upload(MultipartFile multipartFile) throws IOException {
		String originalFileName = multipartFile.getOriginalFilename();
		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
		String newFileName = IMAGE_NAME + ext;
		File newFile = convert(multipartFile, originalFileName).orElseThrow(
			() -> new RepositoryRuntimeException(REPOSITORY_POST_IMAGE_UPLOAD_EXCEPTION)
		);
		String newFileFullName = getFullFileName(newFileName);
		String uploadImageUrl = putS3(newFile, newFileFullName);
		deleteTempFile(newFile);

		return PostImageUploadResponse.builder()
			.fileName(newFileName)
			.url(uploadImageUrl)
			.build();
	}

	private String getFullFileName(String fileName) {
		return new StringJoiner("/")
			.add(POST_DIR).add(UUID.randomUUID().toString())
			.add(fileName).toString();
	}

	private void deleteTempFile(File file) {
		if (file.delete()) {
			log.info("[SUCCESS] 임시 파일 삭제 완료");
		} else {
			log.info("[FAILED] 임시 파일 삭제 실패");
		}
	}

	private String putS3(File file, String fullName) {
		amazonS3Client.putObject(new PutObjectRequest(bucket, fullName, file).withCannedAcl(
			CannedAccessControlList.PublicRead));
		return amazonS3Client.getUrl(bucket, fullName).toString();
	}

	private Optional<File> convert(MultipartFile file, String imageName) throws IOException, IOException {
		File convertFile = new File(imageName);
		if (convertFile.createNewFile()) {
			try (FileOutputStream fos = new FileOutputStream(convertFile)) {
				fos.write(file.getBytes());
			}
			return Optional.of(convertFile);
		}
		return Optional.empty();
	}
}
