package com.merinaukri.job;

import java.util.List;

import com.merinaukri.dto.AddJobDTO;
import com.merinaukri.dto.UpdateJobDTO;

public interface JobService {
	List<Job> findAll();
	void createJob(AddJobDTO dto);
	Job getJobById(Long id);
	boolean deleteJobById(Long id);
	boolean updateJob(Long id, UpdateJobDTO dto);
}
