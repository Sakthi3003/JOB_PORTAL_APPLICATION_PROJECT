package com.merinaukri.companyms.company.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.merinaukri.companyms.clients.ReviewClient;
import com.merinaukri.companyms.company.Company;
import com.merinaukri.companyms.company.CompanyRepository;
import com.merinaukri.companyms.company.CompanyService;
import com.merinaukri.companyms.dto.CompanyAddDTO;
import com.merinaukri.companyms.dto.CompanyUpdateDTO;
import com.merinaukri.companyms.dto.DisplayCompanyDTO;
import com.merinaukri.companyms.dto.ReviewMessage;

import jakarta.ws.rs.NotFoundException;


@Service
public class CompanyServiceImpl implements CompanyService{
	private CompanyRepository companyRepository;
	
	private ReviewClient reviewClient;
	public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
		this.companyRepository = companyRepository;
		this.reviewClient = reviewClient;
	}

	@Override
	public List<Company> getAllCompany() {
		return companyRepository.findAll();
	}

	@Override
	public Boolean updateCompany(Long id,CompanyUpdateDTO dto) {
		if(companyRepository.existsById(id)) {
			Company company = companyRepository.findById(id).get();
			company.setName(dto.getName());
			company.setDescription(dto.getDescription());
			companyRepository.save(company);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void createCompany(CompanyAddDTO dto) {
	  Company company = new Company();
	  System.out.println(dto.getDescription());
	  company.setDescription(dto.getDescription());
	  company.setName(dto.getName());
	  companyRepository.save(company);

	}

	@Override
	public Company getCompanyId(Long id) {
		Company company = companyRepository.findById(id).orElse(null);
		return company;
	}

	@Override
	public Boolean deleteJobById(Long id) {
		if(companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void updateCompanyRating(ReviewMessage review) {
		// TODO Auto-generated method stub
		System.out.println(review.getDescription());
		Company company = companyRepository.findById(review.getCompanyId())
				.orElseThrow(() -> new NotFoundException("Company not found"));
		Double averageRating = reviewClient.getAverageRatingForCompany(company.getId());
		company.setRating(averageRating);
		companyRepository.save(company);
	}
	
	
	
	

}
