package com.tcs.pdfsearchengine.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.tcs.pdfsearchengine.model.Education;

@Document(indexName = "province", type = "pdf")
public class Pdf implements Serializable{
	
	private static final long serialVersionUID = -1L;

	private Long id;
	private String userName;
	private String phoneNumber;
	private String userMail;
	private List<String> skills;
	private String completeEducation;	
	private List<String> completeDegree;
	
	@Field(type = FieldType.Nested)
	private List<Education> education;
	
	public Pdf() {
		super();
	}



	public Pdf(Long id, String userName, String phoneNumber, String userMail, List<String> skills,
			String completeEducation, List<Education> education, List<String> completeDegree) {
		super();
		this.id = id;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.userMail = userMail;
		this.skills = skills;
		this.completeEducation = completeEducation;
		this.education = education;
		this.completeDegree = completeDegree;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}
	
	public String getCompleteEducation() {
		return completeEducation;
	}

	public void setCompleteEducation(String completeEducation) {
		this.completeEducation = completeEducation;
	}
	
	public List<String> getCompleteDegree() {
		return completeDegree;
	}

	public void setCompleteDegree(List<String> completeDegree) {
		this.completeDegree = completeDegree;
	}

	@Override
	public String toString() {
		return "Pdf [id=" + id + ", userName=" + userName + ", phoneNumber=" + phoneNumber + ", userMail=" + userMail
				+ ", skills=" + skills + ", completeEducation=" + completeEducation + ", completeDegree="
				+ completeDegree + ", education=" + education + "]";
	}
}
