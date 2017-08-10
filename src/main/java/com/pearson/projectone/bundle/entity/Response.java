package com.pearson.projectone.bundle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;

@Entity
public class Response implements Serializable {

	public Response() {
	}

	public Response(String examineeId, String acronym, String responses) {
		this.examineeId = examineeId;
		this.acronym = acronym;
		this.responses = responses;
	}

	@Id()
	@GeneratedValue()
	private String id;

	@Column
	private String examineeId;

	@Column
	private String acronym;


	@Column
	@Lob
	private String responses;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getResponses() {
		return responses;
	}

	public void setResponses(String responses) {
		this.responses = responses;
	}
}
