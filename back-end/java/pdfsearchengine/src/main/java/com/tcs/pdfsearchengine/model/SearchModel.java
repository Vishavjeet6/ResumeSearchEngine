package com.tcs.pdfsearchengine.model;

public class SearchModel {
	
	private String userName;
	private String skills;
	private String completeEducation;
	private String completeDegree;
	
	public SearchModel() {
		super();
	}

	public SearchModel(String userName, String skills, String completeEducation, String completeDegree) {
		super();
		this.userName = userName;
		this.skills = skills;
		this.completeEducation = completeEducation;
		this.completeDegree = completeDegree;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getCompleteEducation() {
		return completeEducation;
	}

	public void setCompleteEducation(String completeEducation) {
		this.completeEducation = completeEducation;
	}

	public String getCompleteDegree() {
		return completeDegree;
	}

	public void setCompleteDegree(String completeDegree) {
		this.completeDegree = completeDegree;
	}
}
