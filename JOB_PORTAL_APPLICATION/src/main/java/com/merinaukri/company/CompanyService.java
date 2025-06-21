package com.merinaukri.company;

import java.util.List;

import com.merinaukri.dto.CompanyAddDTO;
import com.merinaukri.dto.CompanyUpdateDTO;
import com.merinaukri.dto.DisplayCompanyDTO;

public interface CompanyService {
	List<Company> getAllCompany();
	Boolean updateCompany(Long id, CompanyUpdateDTO dto);
	void createCompany(CompanyAddDTO dto);
	DisplayCompanyDTO getCompanyId(Long id);
	Boolean deleteJobById(Long id);
}
