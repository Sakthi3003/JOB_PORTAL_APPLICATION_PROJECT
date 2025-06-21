package com.merinaukri.job;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.merinaukri.dto.AddJobDTO;
import com.merinaukri.dto.DisplayJobDTO;
import com.merinaukri.dto.UpdateJobDTO;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
	
	private JobService jobService;
	
	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}

	@GetMapping()
	public List<Job> getAllJobs(){
		return jobService.findAll();
	}
	
	@PostMapping()
	public ResponseEntity<String> createJob(@RequestBody AddJobDTO dto) {
		jobService.createJob(dto);
		return new ResponseEntity<>("Job Added Successfully", HttpStatus.OK);
	}
	
	@GetMapping("{id}")  // common api design accross application even it is not found
	public ResponseEntity<DisplayJobDTO> getJobById(@PathVariable Long id) {
		Job job = jobService.getJobById(id);
		if(job != null) {
			return new ResponseEntity<>(new DisplayJobDTO(job), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteJobById(@PathVariable Long id){
		boolean deleted = jobService.deleteJobById(id);
		
		if(deleted) {	
			return new ResponseEntity<>("Job Deleted successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody UpdateJobDTO dto){
		boolean updated = jobService.updateJob(id,dto );
		if(updated) {
			return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	

}
