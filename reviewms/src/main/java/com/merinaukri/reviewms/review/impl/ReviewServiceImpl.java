package com.merinaukri.reviewms.review.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.merinaukri.reviewms.dto.ReviewDTO;
import com.merinaukri.reviewms.dto.UpdateReviewDTO;
import com.merinaukri.reviewms.review.Review;
import com.merinaukri.reviewms.review.ReviewRepository;
import com.merinaukri.reviewms.review.ReviewService;



@Service
public class ReviewServiceImpl implements ReviewService{
	private final ReviewRepository reviewRepository;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@Override
	public List<Review> getAllReview(Long companyId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews;
	}

	@Override
	public Boolean writeReview(Long companyId, ReviewDTO dto) {
		if(companyId!=null) {
//			Company company = companyRepository.findById(companyId).get();
			Review review = new Review();
			review.setTitle(dto.getTitle());
			review.setRating(dto.getRating());
			review.setDescription(dto.getDescription());
			review.setCompanyId(companyId);
			reviewRepository.save(review);
			
			return true;
		}else {
			return false;
		}
		
		
	}

	@Override
	public Review getReviewById(Long reviewId) {
		Review review = reviewRepository.findById(reviewId).orElse(null);
		
		return review;
	}

	@Override
	public Boolean updateReviewId(Long reviewId, UpdateReviewDTO dto) {
		Boolean found = false;
		if(reviewId!=null) {
			Optional<Review> review = reviewRepository.findById(reviewId);
			if(review.isPresent()) {
				Review review1 = review.get();
				review1.setDescription(dto.getDescription());
				review1.setRating(dto.getRating());
				review1.setTitle(dto.getTitle());
				reviewRepository.save(review1);
				found = true;
			}
		}
		return found;
	}

	@Override
	public Boolean deleteReviewId(Long reviewId) {
		Boolean deleted = false;
		if(reviewId!=null) {
			Optional<Review> review = reviewRepository.findById(reviewId);
			if(review.isPresent()) {
	  
				reviewRepository.deleteById(reviewId);
				deleted = true;
			}
		}
		return deleted;
	}

}
