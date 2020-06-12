package com.tcs.pdfsearchengine.model;

public class Education {
	
	private String degree;
	private String year;
	private String fullEducation;
	
	
	public Education() {
		super();
	}


	public Education(String degree, String year, String fullEducation) {
		super();
		this.degree = degree;
		this.year = year;
		this.fullEducation = fullEducation;
	}


	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getFullEducation() {
		return fullEducation;
	}


	public void setFullEducation(String fullEducation) {
		this.fullEducation = fullEducation;
	}


	@Override
	public String toString() {
		return "Education [degree=" + degree + ", year=" + year + ", fullEducation=" + fullEducation + "]";
	}
	
	
}
