package com.tcs.pdfsearchengine.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.pdfsearchengine.model.FileModel;
import com.tcs.pdfsearchengine.repository.FileRepository;
import com.tcs.pdfsearchengine.utils.Constants;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.ORIGIN, allowedHeaders = "*")
public class FileController {
	
	@Autowired
	private FileRepository fileRepository;
	
	@GetMapping(path = { "/getfile/{fileId}" })
	public Optional<FileModel> getFile(
			@PathVariable("fileId") Long fileId) throws IOException {
		return fileRepository.findById(fileId);	
	}

}
