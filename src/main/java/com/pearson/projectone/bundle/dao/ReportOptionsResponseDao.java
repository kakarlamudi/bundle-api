package com.pearson.projectone.bundle.dao;

import com.pearson.projectone.bundle.entity.ReportOptionsResponse;
import com.pearson.projectone.bundle.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportOptionsResponseDao extends JpaRepository<ReportOptionsResponse, String> {

	public ReportOptionsResponse findByExamineeIdAndReportId(final String reportId, final String examineeId);
}
