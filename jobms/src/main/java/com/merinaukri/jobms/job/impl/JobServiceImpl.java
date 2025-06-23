package com.merinaukri.jobms.job.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.merinaukri.jobms.dto.AddJobDTO;
import com.merinaukri.jobms.dto.UpdateJobDTO;
import com.merinaukri.jobms.job.Job;
import com.merinaukri.jobms.job.JobRepository;
import com.merinaukri.jobms.job.JobService;


@Service
public class JobServiceImpl implements JobService {

	private JobRepository jobRepository;
	
	
	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepository =  jobRepository;
	}
	
	@Override
	public List<Job> findAll() {
		return jobRepository.findAll();
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
	public Job getJobById(Long id) {
		System.out.println(jobRepository.findById(id));
		return jobRepository.findById(id).orElse(null);
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

}

