package com.merinaukri.reviewms.review.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.merinaukri.reviewms.dto.ReviewDTO;
import com.merinaukri.reviewms.dto.ReviewMessage;
import com.merinaukri.reviewms.review.Review;

@Service
public class ReviewMessageProducer {
	private final RabbitTemplate rabbitTemplate;
	
	public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(Review review) {
		ReviewMessage reviewMessage = new ReviewMessage();
		reviewMessage.setId(review.getId());
		reviewMessage.setTitle(review.getTitle());
		reviewMessage.setDescription(review.getDescription());
		reviewMessage.setCompanyId(review.getCompanyId());
		reviewMessage.setRating(review.getRating());
		rabbitTemplate.convertAndSend("companyRatingQueue",reviewMessage);
		
	}

}
