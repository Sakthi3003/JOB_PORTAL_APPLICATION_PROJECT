package com.merinaukri.companyms.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisplayCompanyDTO {
	private Long id;
	private String name;
	private String description;
	private Double rating;

}
