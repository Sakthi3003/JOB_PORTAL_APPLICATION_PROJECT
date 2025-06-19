package com.merinaukri.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.merinaukri.dto.JobAddDTO;
import com.merinaukri.dto.JobDTO;
import com.merinaukri.service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
	
	private JobService jobService;
	
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<JobDTO> getAllJobs(){
		return jobService.getAllJobs();
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public String addJob(@Valid @RequestBody JobAddDTO dto) {
		return jobService.addJob(dto);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String updateJob(@Valid @PathVariable Integer id) {
		return jobService.updateJob(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteJob(@PathVariable Integer id) {
		return jobService.deleteJob(id);
	}
	
 
}
