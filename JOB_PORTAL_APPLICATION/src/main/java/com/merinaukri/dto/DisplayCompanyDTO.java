package com.merinaukri.dto;

import com.merinaukri.company.Company;

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

public DisplayCompanyDTO(Company company) {
	this.id = company.getId();
	this.name = company.getName();
	this.description = company.getDescription();
	}
}
