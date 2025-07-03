package com.merinaukri.jobms.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.merinaukri.jobms.external.Company;

@FeignClient(name = "COMPANYMS", url= "${company-service.url}")
public interface CompanyClient {

	@GetMapping("/api/company/{id}")
	Company getCompanyById(@PathVariable Long id);
}
