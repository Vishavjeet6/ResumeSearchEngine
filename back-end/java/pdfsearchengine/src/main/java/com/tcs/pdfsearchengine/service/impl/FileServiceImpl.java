package com.tcs.pdfsearchengine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.pdfsearchengine.model.FileModel;
import com.tcs.pdfsearchengine.repository.FileRepository;
import com.tcs.pdfsearchengine.service.FileService;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	private FileRepository fileRepository;

	@Override
	public void savetoDb(FileModel userFile) {
		fileRepository.save(userFile);		
		fileRepository.flush();
	}

}
