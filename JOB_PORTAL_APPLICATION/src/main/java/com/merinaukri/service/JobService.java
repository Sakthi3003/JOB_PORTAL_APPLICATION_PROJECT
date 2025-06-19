package com.merinaukri.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.merinaukri.dto.JobAddDTO;
import com.merinaukri.dto.JobDTO;
import com.merinaukri.entity.Job;
import com.merinaukri.repository.JobRepository;

public interface JobService {
//	
//	private static final Logger logger = LoggerFactory.getLogger(JobService.class);
//	
//	private JobRepository jobRepository;
//	
//	public JobService(JobRepository jobRepository) {
//		this.jobRepository = jobRepository;
//	}
//	
//	// Get all jobs
//	public List<JobDTO> getAllJobs() {
//		logger.info("Fetching jobs from jobs repository");
//		
//		List<Job> jobs = jobRepository.findAll();
//		
//		if(Objects.isNull(jobs) || jobs.isEmpty()) {
//			logger.warn("No jobs found");
//			return Collections.emptyList();
//		}
//		
//		List<JobDTO> jobList = jobs
//				               .stream()
//				               .filter(Objects::nonNull)
//				               .map(JobDTO::new)
//				               .collect(Collectors.toList());
//		
//		logger.info("Fetched {} jobs Successfully", jobList.size());
//				
//	    return jobList;	
//	}
//
//	// Add job
//	public String addJob(JobAddDTO dto	q3) {
//		return null;
//	}
//
//	public String updateJob(Integer id ) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public String deleteJob(Integer id) {
//		Boolean exist = jobRepository.existsById(id);
//		
//		if(exist) {
//			jobRepository.deleteById(id);
//			return "Job deleted successfully";
//		}else {
//			return "Id not found";
//		}
//	}

}
