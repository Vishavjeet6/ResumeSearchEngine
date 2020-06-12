package com.tcs.pdfsearchengine.controller;

import java.io.IOException;
import java.util.List;

import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.tcs.pdfsearchengine.domain.Pdf;
import com.tcs.pdfsearchengine.model.FileModel;
import com.tcs.pdfsearchengine.model.SearchModel;
import com.tcs.pdfsearchengine.service.FileService;
import com.tcs.pdfsearchengine.service.PdfService;
import com.tcs.pdfsearchengine.service.PythonService;
import com.tcs.pdfsearchengine.utils.Constants;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.ORIGIN, allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
    private PdfService pdfService;
	
	@Autowired 
	private PythonService pythonService;
	
	
	@PostMapping("/pdf/upload")
    public Long createPdf(@RequestParam("file") MultipartFile file) 
			throws IOException, SAXException, TikaException {
		FileModel userFile = new FileModel(file.getOriginalFilename(), 
				file.getContentType(), 
				file.getBytes());
		
//		saving file to MySQL DB
		fileService.savetoDb(userFile);
		

//		run python script
		String jsonString = pythonService.getUsefulData(userFile.getId());
		
//		Convert Parsed String to Pdf Model
		Pdf parsedFile = pythonService.parseJson(jsonString, userFile.getId());
		
//		Pdf parsedFile = pythonService.parsePdf(userFile);		
		
//		indexing file
        return pdfService.savePdf(parsedFile);
    }
    
	@PostMapping("/pdf/description/find")
    public List<Pdf> findByDescription(@RequestBody SearchModel search) {
        return pdfService.findByDescription(search);
    }

}
