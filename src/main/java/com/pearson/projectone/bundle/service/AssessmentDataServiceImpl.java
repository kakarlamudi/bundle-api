package com.pearson.projectone.bundle.service;


import com.pearson.projectone.bundle.dto.PlatformValues;
import org.springframework.stereotype.Service;

@Service
public class AssessmentDataServiceImpl implements AssessmentDataService {

	public PlatformValues fetchAssessmentData(final String formId, final String examineeId) {
		PlatformValues platformValues = new PlatformValues();
		return platformValues;
	}
}
