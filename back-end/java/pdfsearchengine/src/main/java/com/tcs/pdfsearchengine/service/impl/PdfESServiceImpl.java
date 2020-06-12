package com.tcs.pdfsearchengine.service.impl;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tcs.pdfsearchengine.domain.Pdf;
import com.tcs.pdfsearchengine.model.SearchModel;
import com.tcs.pdfsearchengine.repository.PdfRepository;
import com.tcs.pdfsearchengine.service.PdfService;

@Service
public class PdfESServiceImpl implements PdfService{
	
	private static final Integer pageNumber = 0;
    private static final Integer pageSize = 10;
    
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    
    @Autowired
    PdfRepository pdfRepository;

	public Long savePdf(Pdf pdf) {
		Pdf pdfResult = pdfRepository.save(pdf);
        return pdfResult.getId();
	}

	@Override
	public List<Pdf> findByDescription(SearchModel search) {
		return pdfRepository.findBySkillsAndCompleteDegreeAndCompleteEducation(search.getSkills(), search.getCompleteDegree(), search.getCompleteEducation(), pageable).getContent();
	}
}
