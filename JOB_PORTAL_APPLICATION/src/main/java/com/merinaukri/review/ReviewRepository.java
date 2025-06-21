package com.merinaukri.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merinaukri.company.Company;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByCompany(Company company);
}
