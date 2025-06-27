package com.merinaukri.jobms.mapper;

import java.util.List;

import com.merinaukri.jobms.dto.JobDTO;
import com.merinaukri.jobms.external.Company;
import com.merinaukri.jobms.external.Review;
import com.merinaukri.jobms.job.Job;

public class JobMapper {
	public static JobDTO mapToJobDto(
			Job job,
			Company company,
			List<Review> reviews
			) {
		JobDTO dto = new JobDTO();
		dto.setId(job.getId());
		dto.setTitle(job.getTitle());
		dto.setLocation(job.getLocation());
		dto.setMaxSalary(job.getMaxSalary());
		dto.setMinSalary(job.getMinSalary());
		dto.setDescription(job.getDescription());
		dto.setCompany(company);
		dto.setReview(reviews);
		return dto;
	}

}
