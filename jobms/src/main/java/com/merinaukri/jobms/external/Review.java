package com.merinaukri.jobms.external;


import lombok.Data;

@Data
public class Review {
	private Long id;
	private String description;
	private String title;
	private Double rating;
}
