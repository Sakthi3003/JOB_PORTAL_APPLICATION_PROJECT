package com.merinaukri.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
	private int id;
	private String title;
	private String description;
	private String minSalary;
	private String maxSalary;
	private String location;
}
