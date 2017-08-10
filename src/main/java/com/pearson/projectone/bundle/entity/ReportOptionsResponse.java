package com.pearson.projectone.bundle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;
@Entity
public class ReportOptionsResponse implements Serializable {

	public ReportOptionsResponse(String reportId, String examineeId, String response) {
		this.reportId = reportId;
		this.examineeId = examineeId;
		this.response = response;
	}

	public ReportOptionsResponse() {
	}

	@Id
	@GeneratedValue
	private String id;

	@Column
	private String reportId;

	@Column
	private String examineeId;

	@Lob
	@Column
	private String response;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
