package com.tcs.pdfsearchengine.service;

import com.tcs.pdfsearchengine.domain.Pdf;

public interface PythonService {

	String getUsefulData(Long fileId);
	Pdf parseJson(String parsedData, Long fileId);

}
