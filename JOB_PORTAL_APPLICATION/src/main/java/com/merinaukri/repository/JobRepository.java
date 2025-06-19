package com.merinaukri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merinaukri.entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

}
