package com.merinaukri.review.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.merinaukri.company.Company;
import com.merinaukri.company.CompanyRepository;
import com.merinaukri.dto.ReviewDTO;
import com.merinaukri.dto.UpdateReviewDTO;
import com.merinaukri.review.Review;
import com.merinaukri.review.ReviewRepository;
import com.merinaukri.review.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService{
	private final ReviewRepository reviewRepository;
	private final CompanyRepository companyRepository;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyRepository companyRepository) {
		this.reviewRepository = reviewRepository;
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Review> getAllReview(Long companyId) {
		Company company = companyRepository.findById(companyId).get();
		List<Review> reviews = reviewRepository.findByCompany(company);
		return reviews;
	}

	@Override
	public Boolean writeReview(Long companyId, ReviewDTO dto) {
		if(companyRepository.existsById(companyId)) {
			Company company = companyRepository.findById(companyId).get();
			Review review = new Review();
			review.setTitle(dto.getTitle());
			review.setRating(dto.getRating());
			review.setDescription(dto.getDescription());
			review.setCompany(company);
			reviewRepository.save(review);
			
			company.getReviews().add(review);
			
			companyRepository.save(company);
			return true;
		}else {
			return false;
		}
		
		
	}

	@Override
	public ReviewDTO getReviewById(Long companyId, Long reviewId) {
		Company company = companyRepository.findById(companyId).get();
		List<Review> reviews = reviewRepository.findByCompany(company);
		Review review = reviews.stream().filter(review1 -> review1.getId().equals(reviewId))
				.findFirst()
				.orElse(null);
		return new ReviewDTO(review);
	}

	@Override
	public Boolean updateReviewId(Long companyId, Long reviewId, UpdateReviewDTO dto) {
		Boolean found = false;
		if(companyRepository.existsById(companyId)) {
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
	public Boolean deleteReviewId(Long companyId, Long reviewId) {
		Boolean deleted = false;
		if(companyRepository.existsById(companyId)) {
			Optional<Review> review = reviewRepository.findById(reviewId);
			if(review.isPresent()) {
	            Company company = review.get().getCompany();
	            company.getReviews().remove(review.get());
				reviewRepository.deleteById(reviewId);
				deleted = true;
			}
		}
		return deleted;
	}

}
