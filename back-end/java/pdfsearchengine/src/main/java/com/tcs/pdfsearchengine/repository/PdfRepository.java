package com.tcs.pdfsearchengine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.tcs.pdfsearchengine.domain.Pdf;



@Repository
public interface PdfRepository extends ElasticsearchRepository<Pdf, Long>{
	Page<Pdf> findBySkillsAndCompleteDegreeAndCompleteEducation(String skills, 
			String completeDegree, String completeEducation, Pageable page);
}
