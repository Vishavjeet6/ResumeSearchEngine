package com.tcs.pdfsearchengine.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.json.*;
//import com.github.openjson.JSONArray;
//import com.github.openjson.JSONObject;
import com.tcs.pdfsearchengine.domain.Pdf;
import com.tcs.pdfsearchengine.model.Education;
import com.tcs.pdfsearchengine.service.PythonService;

@Service
public class PythonServiceImpl implements PythonService{

	@Override
	public String getUsefulData(Long fileId) {
		String s = null;
		String jsonString="";
		
		try {
        	
        	String [] cmd = {
        			"C:\\Users\\visha\\Desktop\\research\\attempt2\\tcsresume\\Scripts\\python.exe",
        			"C:\\Users\\visha\\Desktop\\research\\attempt2\\testing.py",
        			Long.toString(fileId),
        	};
      
        	Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

        
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
            	jsonString += s;
                System.out.println(s);
            }
            
            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
            	jsonString += s;
                System.out.println(s);
            }

            return jsonString;
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
            return "Error";
        }	
	}

	@Override
	public Pdf parseJson(String jsonString, Long fileId) {
		Pdf pdfData = new Pdf();
		pdfData.setId(fileId);
		System.out.println("************");
		System.out.println("************");
		System.out.println("************");
		System.out.println(jsonString);
		System.out.println("************");
		System.out.println("************");
		System.out.println("************");
		JSONObject obj = new JSONObject(jsonString);

		pdfData.setUserName(obj.getString("name"));
		pdfData.setUserMail(obj.getString("email"));
		pdfData.setPhoneNumber(obj.getString("phoneNumber"));
		JSONArray arr = obj.getJSONArray("skills");
		List<String> skills = new ArrayList<>();
		for (int i = 0; i < arr.length(); i++) {
			skills.add(arr.getString(i));
		}
		pdfData.setSkills(skills);
		
		List<Education> education = new ArrayList<>();
		String completeEducation = "";
		List<String> completeDegree = new ArrayList<>();
		
		JSONArray edu = obj.getJSONArray("education");
		for(int i=0; i<edu.length(); i++) {
			Education e = new Education();
			e.setDegree(edu.getJSONObject(i).getString("degree"));
			completeDegree.add(e.getDegree());
			e.setYear(edu.getJSONObject(i).getString("year"));
			e.setFullEducation(edu.getJSONObject(i).getString("fulleducation"));
			completeEducation = completeEducation + " " +e.getFullEducation();
			education.add(e);
		}
		pdfData.setCompleteDegree(completeDegree);
		pdfData.setEducation(education);
		pdfData.setCompleteEducation(completeEducation);
		System.out.println(pdfData.toString());
		return pdfData;
	}
	
}
