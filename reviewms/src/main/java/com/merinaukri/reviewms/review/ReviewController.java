package com.merinaukri.reviewms.review;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.merinaukri.reviewms.dto.ReviewDTO;
import com.merinaukri.reviewms.dto.UpdateReviewDTO;
import com.merinaukri.reviewms.review.messaging.ReviewMessageProducer;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	private ReviewService reviewService;
	
	private ReviewMessageProducer reviewMessageProducer;
	
	public ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
		this.reviewService = reviewService;
		this.reviewMessageProducer = reviewMessageProducer;
	}
	
	@GetMapping
	public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
		return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> writeReview(@RequestParam Long companyId,@RequestBody ReviewDTO dto){
		Review review = reviewService.writeReview(companyId, dto);
		
		if(review != null) {
			reviewMessageProducer.sendMessage(review);
			return new ResponseEntity<>("Revie added successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{reviewId}")
	public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId){
		Review review = reviewService.getReviewById(reviewId);
		if(review!=null) {
			return ResponseEntity.ok(review);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{reviewId}")
	public ResponseEntity<String> updateReviewById(@PathVariable Long reviewId, UpdateReviewDTO dto){
		Boolean isReviewSaved = reviewService.updateReviewId( reviewId,dto);
		if(isReviewSaved) {
			return new ResponseEntity<>("Review Saved Successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReviewById( @PathVariable Long reviewId){
		Boolean isReviewDeleted = reviewService.deleteReviewId( reviewId);
		if(isReviewDeleted) {
			return new ResponseEntity<>("Review deleted Successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/averageRating")
	public Double getAverageReview(@RequestParam Long companyId) {
		List<Review> reviewList=reviewService.getAllReview(companyId);
		return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
	}
	
	

}
