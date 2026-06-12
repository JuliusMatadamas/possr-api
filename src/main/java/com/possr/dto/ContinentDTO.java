package com.possr.dto;

import io.github.resilience4j.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContinentDTO {
	@Nullable
	private Long id;
	private String name;

}
