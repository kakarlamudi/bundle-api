package com.pearson.projectone.bundle.service;

import com.pearson.projectone.bundle.dto.PlatformValues;

public interface AssessmentDataService {

	/**
	 * This methods reads assessment data by given formId and examineeId.
	 * @param formId
	 * @param examineeId
	 * @return
	 */
	public PlatformValues fetchAssessmentData(final String formId, final String examineeId);
}
