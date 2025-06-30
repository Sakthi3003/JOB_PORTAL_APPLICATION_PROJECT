package com.merinaukri.jobms.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.merinaukri.jobms.clients.CompanyClient;
import com.merinaukri.jobms.clients.ReviewClient;
import com.merinaukri.jobms.dto.AddJobDTO;
import com.merinaukri.jobms.dto.JobDTO;
import com.merinaukri.jobms.dto.UpdateJobDTO;
import com.merinaukri.jobms.external.Company;
import com.merinaukri.jobms.external.Review;
import com.merinaukri.jobms.job.Job;
import com.merinaukri.jobms.job.JobRepository;
import com.merinaukri.jobms.job.JobService;
import com.merinaukri.jobms.mapper.JobMapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class JobServiceImpl implements JobService {

	private JobRepository jobRepository;
	
	private RestTemplate restTemplate;
	
	private CompanyClient companyClient;
	
	private ReviewClient reviewClient;
	
	
	public JobServiceImpl(JobRepository jobRepository, RestTemplate restTemplate,
			CompanyClient companyClient,
			ReviewClient reviewClient) {
		this.jobRepository =  jobRepository;
		this.restTemplate = restTemplate;
		this.companyClient = companyClient;
		this.reviewClient = reviewClient;
	}
	
	@Override
	@CircuitBreaker(name="CompanyBreaker", fallbackMethod="fallbackMethod")
	public List<JobDTO> findAll() {
		List<JobDTO> jobs = jobRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
		return jobs;
	}
	
	public List<String> fallbackMethod(Exception e){
		List<String> list = new ArrayList<>();
		list.add("Dummy");
		return list;
	}

	@Override
	public void createJob(AddJobDTO dto) {
		Job job = new Job();
		job.setDescription(dto.getDescription());
		job.setLocation(dto.getDescription());
		job.setMaxSalary(dto.getMaxSalary());
		job.setTitle(dto.getTitle());
		job.setMinSalary(dto.getMinSalary());
		job.setLocation(dto.getLocation());
		job.setCompanyId(dto.getCompanyId());
		
		jobRepository.save(job);
	}

	@Override
	public JobDTO getJobById(Long id) {
		Job job =jobRepository.findById(id).orElse(null);
		
		Company company = companyClient.getCompanyById(job.getCompanyId());
		
	    List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

	    JobDTO jobDto = JobMapper.mapToJobDto(job, company,reviews);
		return jobDto;
	}

	@Override
	public boolean deleteJobById(Long id) {
		if(jobRepository.existsById(id)) {
			jobRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateJob(Long id, UpdateJobDTO dto) {
		if(jobRepository.existsById(id)) {
			Job job = jobRepository.findById(id).get();
			job.setDescription(dto.getDescription());
			job.setLocation(dto.getLocation());
			job.setMaxSalary(dto.getMaxSalary());
			job.setMinSalary(dto.getMinSalary());
			job.setTitle(dto.getTitle());
    		job.setCompanyId(dto.getCompanyId());
			jobRepository.save(job);
			return true;
		}else {
			return false;
		}
	}
	
	private JobDTO convertToDTO(Job job) {
		Company company = companyClient.getCompanyById(job.getCompanyId());
		
	    List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
	    
	    JobDTO jobDTO = JobMapper.mapToJobDto(job, company, reviews);
	    return jobDTO;
	}

}

