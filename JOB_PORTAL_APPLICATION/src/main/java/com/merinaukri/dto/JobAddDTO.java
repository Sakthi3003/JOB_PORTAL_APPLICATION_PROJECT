package com.merinaukri.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobAddDTO {
	private String title;
	private String description;
	private String minSalary;
	private String maxSalary;
	private String location;
}
