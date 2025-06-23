package com.merinaukri.jobms.dto;

import com.merinaukri.jobms.job.Job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisplayJobDTO {
	private Long id;
	private String title;
	private String description;
	private String minSalary;
	private String maxSalary;
	private String location;

	public DisplayJobDTO(Job job) {
		this.id = job.getId();
		this.title = job.getTitle();
		this.description = job.getDescription();
		this.minSalary = job.getMinSalary();
		this.maxSalary = job.getMaxSalary();
		this.location = job.getLocation();
	}

}
