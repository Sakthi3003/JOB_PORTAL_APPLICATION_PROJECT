package com.merinaukri.review;

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

import com.merinaukri.dto.ReviewDTO;
import com.merinaukri.dto.UpdateReviewDTO;

@RestController
@RequestMapping("/api/companies/{companyId}")
public class ReviewController {
	private ReviewService reviewService;
	
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
		return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
	}
	
	@PostMapping("/reviews")
	public ResponseEntity<String> writeReview(@PathVariable Long companyId,@RequestBody ReviewDTO dto){
		Boolean isReviewSaved = reviewService.writeReview(companyId, dto);
		if(isReviewSaved) {
			return new ResponseEntity<>("Revie added successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
		ReviewDTO review = reviewService.getReviewById(companyId, reviewId);
		if(review!=null) {
			return ResponseEntity.ok(review);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReviewById(@PathVariable Long companyId, @PathVariable Long reviewId, UpdateReviewDTO dto){
		Boolean isReviewSaved = reviewService.updateReviewId(companyId, reviewId,dto);
		if(isReviewSaved) {
			return new ResponseEntity<>("Review Saved Successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
		Boolean isReviewDeleted = reviewService.deleteReviewId(companyId, reviewId);
		if(isReviewDeleted) {
			return new ResponseEntity<>("Review deleted Successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

}
