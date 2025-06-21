package com.merinaukri.review;

import java.util.List;

import com.merinaukri.dto.ReviewDTO;
import com.merinaukri.dto.UpdateReviewDTO;

public interface ReviewService {
	List<Review> getAllReview(Long companyId);
	Boolean writeReview(Long companyId, ReviewDTO dto);
	ReviewDTO getReviewById(Long companyId, Long reviewId);
	Boolean updateReviewId(Long companyId, Long reviewId, UpdateReviewDTO dto);
	Boolean deleteReviewId(Long companyId, Long reviewId);
}
