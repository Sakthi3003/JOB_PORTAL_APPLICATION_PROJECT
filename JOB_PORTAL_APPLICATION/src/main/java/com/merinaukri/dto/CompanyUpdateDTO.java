package com.merinaukri.dto;

import java.util.List;

import com.merinaukri.job.Job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUpdateDTO {
	private Long id;
	private String name;
	private String description;
	private List<Job> jobs;
}
