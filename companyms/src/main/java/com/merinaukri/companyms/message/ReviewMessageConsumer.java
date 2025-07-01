package com.merinaukri.companyms.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.merinaukri.companyms.company.CompanyService;
import com.merinaukri.companyms.dto.ReviewMessage;


@Service
public class ReviewMessageConsumer {
	private final CompanyService companyService;
	
	public ReviewMessageConsumer(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@RabbitListener(queues="companyRatingQueue")
	public void ConsumeMessage(ReviewMessage review) {
		companyService.updateCompanyRating(review);
	}

}
