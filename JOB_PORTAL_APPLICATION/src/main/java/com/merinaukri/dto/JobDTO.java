package com.merinaukri.dto;

import com.merinaukri.entity.Job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
	private int id;
	private String title;
	private String description;
	private String minSalary;
	private String maxSalary;
	private String location;
	
	public JobDTO(Job job) {
		this.id = job.getId();
		this.title = job.getTitle();
		this.description = job.getDescription();
		this.maxSalary = job.getMaxSalary();
		this.minSalary = job.getMinSalary();
		this.location = job.getLocation();
	}
}
