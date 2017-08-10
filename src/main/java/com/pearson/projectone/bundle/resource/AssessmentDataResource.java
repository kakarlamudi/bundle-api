package com.pearson.projectone.bundle.resource;


import com.pearson.projectone.bundle.service.AssessmentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/assessment/data/")
public class AssessmentDataResource {


	@Autowired
	AssessmentDataService assessmentDataService;

	@RequestMapping("/values/{formId}/{examineeId}")
	public ResponseEntity<?> getAssessmentData(@PathVariable("formId") final String formId,
	                                        @PathVariable("examineeId") final String examineeId) {
		return ResponseEntity.ok(this.assessmentDataService.fetchAssessmentData(formId, examineeId));
	}

}
