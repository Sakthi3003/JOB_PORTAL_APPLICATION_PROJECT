package com.merinaukri.reviewms.review;

import java.util.List;

import com.merinaukri.reviewms.dto.ReviewDTO;
import com.merinaukri.reviewms.dto.UpdateReviewDTO;


public interface ReviewService {
	List<Review> getAllReview(Long companyId);
	Boolean writeReview(Long companyId, ReviewDTO dto);
	Review getReviewById(Long reviewId);
	Boolean updateReviewId(Long reviewId, UpdateReviewDTO dto);
	Boolean deleteReviewId(Long reviewId);
}
