package com.merinaukri.job.impl;

import java.util.List;

import com.merinaukri.dto.AddJobDTO;
import com.merinaukri.dto.UpdateJobDTO;
import com.merinaukri.job.Job;
import com.merinaukri.job.JobService;

public class JobServiceImpl implements JobService {

	@Override
	public List<Job> findAll() {
		
		return null;
	}

	@Override
	public void createJob(AddJobDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Job getJobById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteJobById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateJob(int id, UpdateJobDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

}
