package com.merinaukri.jobms.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.merinaukri.jobms.external.Review;

@FeignClient(name = "REVIEWMS", url= "${review-service.url}")
public interface ReviewClient {
	
	@GetMapping("api/reviews")
	List<Review> getReviews(@RequestParam Long companyId);

}
