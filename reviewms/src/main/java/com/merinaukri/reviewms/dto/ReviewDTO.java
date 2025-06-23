package com.merinaukri.reviewms.dto;



import com.merinaukri.reviewms.review.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	private String description;
	private String title;
	private Double rating;
	public ReviewDTO(Review review) {
		// TODO Auto-generated constructor stub
	this.description = review.getDescription();
	this.title = review.getTitle();
	this.rating = review.getRating();
	}
}
