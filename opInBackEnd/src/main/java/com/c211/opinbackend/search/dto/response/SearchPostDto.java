package com.c211.opinbackend.search.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SearchPostDto {
	private Long id;

	private String title;
	private String content;

	private int commentCount;
	private int likeCount;

	private String imageUrl;

	private boolean mergeFl;
	private boolean closeState;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime date;

	private List<CommentDto> comments = new ArrayList<>();
}
