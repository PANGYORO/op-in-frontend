package dev.opin.opinbackend.batch.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LicenseDto {
	private String key;
	private String name;
	@JsonProperty("spdx_id")
	private String spdxId;

	private String url;
}
