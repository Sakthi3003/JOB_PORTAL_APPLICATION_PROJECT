package com.merinaukri.jobms.job;

import java.util.List;

import com.merinaukri.jobms.dto.AddJobDTO;
import com.merinaukri.jobms.dto.JobDTO;
import com.merinaukri.jobms.dto.UpdateJobDTO;

public interface JobService {
	List<JobDTO> findAll();
	void createJob(AddJobDTO dto);
	JobDTO getJobById(Long id);
	boolean deleteJobById(Long id);
	boolean updateJob(Long id, UpdateJobDTO dto);
}

