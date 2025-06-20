package com.merinaukri.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobDTO {
	private Long id;
	private String title;
	private String description;
	private String minSalary;
	private String maxSalary;
	private String location;
}
