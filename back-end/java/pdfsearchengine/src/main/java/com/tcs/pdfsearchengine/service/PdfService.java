package com.tcs.pdfsearchengine.service;

import java.util.List;

import com.tcs.pdfsearchengine.domain.Pdf;
import com.tcs.pdfsearchengine.model.SearchModel;


public interface PdfService {
	Long savePdf(Pdf pdf);
	List<Pdf> findByDescription(SearchModel search);
}
