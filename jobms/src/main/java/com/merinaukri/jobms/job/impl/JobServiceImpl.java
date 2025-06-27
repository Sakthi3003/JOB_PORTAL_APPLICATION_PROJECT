package com.merinaukri.jobms.job.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.merinaukri.jobms.dto.AddJobDTO;
import com.merinaukri.jobms.dto.JobDTO;
import com.merinaukri.jobms.dto.UpdateJobDTO;
import com.merinaukri.jobms.external.Company;
import com.merinaukri.jobms.external.Review;
import com.merinaukri.jobms.job.Job;
import com.merinaukri.jobms.job.JobRepository;
import com.merinaukri.jobms.job.JobService;
import com.merinaukri.jobms.mapper.JobMapper;


@Service
public class JobServiceImpl implements JobService {

	private JobRepository jobRepository;
	
	private RestTemplate restTemplate;
	
	public JobServiceImpl(JobRepository jobRepository, RestTemplate restTemplate) {
		this.jobRepository =  jobRepository;
		this.restTemplate = restTemplate;
	}
	
	@Override
	public List<JobDTO> findAll() {
		List<JobDTO> jobs = jobRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
		return jobs;
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
		
		Company company = restTemplate.getForObject(
				"http://COMPANYMS:8897/api/company/"+job.getCompanyId(), 
				Company.class);
		
	    ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
	    		"http://REVIEWMS:8896/api/reviews?companyId="+job.getCompanyId(), 
	    		HttpMethod.GET,
	    		null,
	    		new ParameterizedTypeReference<List<Review>>() {});
	    
	    List<Review> reviews = reviewResponse.getBody();
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
		Company company = restTemplate.getForObject(
				"http://COMPANYMS:8897/api/company/"+job.getCompanyId(), 
				Company.class);
		
	    ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
	    		"http://REVIEWMS:8896/api/reviews?companyId="+job.getCompanyId(), 
	    		HttpMethod.GET,
	    		null,
	    		new ParameterizedTypeReference<List<Review>>() {});
	    
	    JobDTO jobDTO = JobMapper.mapToJobDto(job, company, reviewResponse.getBody());
	    return jobDTO;
	}

}

