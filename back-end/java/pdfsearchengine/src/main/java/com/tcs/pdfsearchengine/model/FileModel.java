package com.tcs.pdfsearchengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class FileModel {
	
	@Id
	@Column(name = "fileId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Lob
	@Column(name = "fileByte")
	private byte[] fileByte;
	

	public FileModel() {
		super();
	}

	public FileModel(String name, String type, byte[] fileByte) {
		super();
		this.name = name;
		this.type = type;
		this.fileByte = fileByte;
	}

	public FileModel(Long id, String name, String type, byte[] fileByte) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.fileByte = fileByte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getFileByte() {
		return fileByte;
	}

	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}

}