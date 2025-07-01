package com.merinaukri.companyms.company;

import java.util.List;

import com.merinaukri.companyms.company.Company;
import com.merinaukri.companyms.dto.CompanyAddDTO;
import com.merinaukri.companyms.dto.CompanyUpdateDTO;
import com.merinaukri.companyms.dto.ReviewMessage;

public interface CompanyService {
	List<Company> getAllCompany();
	Boolean updateCompany(Long id, CompanyUpdateDTO dto);
	void createCompany(CompanyAddDTO dto);
	
	Company getCompanyId(Long id);
	Boolean deleteJobById(Long id);
	// company service 
	public void updateCompanyRating(ReviewMessage review);
}