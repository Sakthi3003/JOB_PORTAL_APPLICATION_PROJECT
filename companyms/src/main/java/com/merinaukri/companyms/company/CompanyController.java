package com.merinaukri.companyms.company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merinaukri.companyms.dto.CompanyAddDTO;
import com.merinaukri.companyms.dto.CompanyUpdateDTO;
@RestController
@RequestMapping("api/company")
public class CompanyController {
	private CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping
	public ResponseEntity<List<Company>> getAllCompany(){
		return new ResponseEntity<>(companyService.getAllCompany(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
		Company company = companyService.getCompanyId(id);
		return new ResponseEntity<>(company, HttpStatus.OK);
	}
	@PutMapping("{id}")
	public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody CompanyUpdateDTO dto){
		Boolean updated = companyService.updateCompany(id, dto);
		if(updated) {
			return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> addCompany(@RequestBody CompanyAddDTO dto){
		companyService.createCompany(dto);
		return new ResponseEntity<>("Company added successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id){
		Boolean deleted = companyService.deleteJobById(id);
		if(deleted) {
		return new ResponseEntity<>("Company deleted successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}

