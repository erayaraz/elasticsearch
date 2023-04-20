package com.elasticsearch.arazelasticsearchtest.repository;

import com.elasticsearch.arazelasticsearchtest.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.data.domain.Pageable;


public interface CompanyRepository extends ElasticsearchRepository<Company, String> {

    Page<Company> findByEmployeesName(String name, Pageable  pageable);

}