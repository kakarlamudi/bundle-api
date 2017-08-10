package com.pearson.projectone.bundle.resource;

import com.pearson.assessment.AssessmentException;
import com.pearson.projectone.bundle.entity.ReportOptionsResponse;
import com.pearson.projectone.bundle.entity.Response;
import com.pearson.projectone.bundle.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductResource {
	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	public ResponseEntity<?> listProducts() {

		return ResponseEntity.ok(this.productService.listProducts());

	}


	@GetMapping("/forms/{productId}")
	public ResponseEntity<?> listForms(@PathVariable("productId") final String productId) {
		String[] forms = null;
		try {
			forms = this.productService.listForms(productId);
		} catch (AssessmentException e) {
			e.printStackTrace();
			forms = new String[0];
		}
		return ResponseEntity.ok(forms);
	}

	@GetMapping("/manualentry/html/{productId}/{formName}")
	public ResponseEntity<?> getManualEntryHTML(@PathVariable("productId") final String productId,
	                                            @PathVariable("formName") final String formName) {
		return ResponseEntity.ok(this.productService.getItemEntryHTML(productId, formName));
	}

	@GetMapping("/manualentry/js/{productId}/{formName}")
	public ResponseEntity<?> getManualEntryJS(@PathVariable("productId") final String productId,
	                                          @PathVariable("formName") final String formName) {
		return ResponseEntity.ok(this.productService.retrieveJS(productId, formName));
	}


	@GetMapping("/manualentry/css/{productId}/{formName}")
	public ResponseEntity<?> getManualEntryCSS(@PathVariable("productId") final String productId,
	                                           @PathVariable("formName") final String formName) {
		return ResponseEntity.ok(this.productService.retrieveCSS(productId, formName));
	}

	@PutMapping("/item-responses/save")
	public ResponseEntity<?> saveItemResponses(@RequestBody final Response response) {
		return ResponseEntity.ok(this.productService.saveResponse(response).getId());
	}

	@GetMapping("/item-responses/{acronym}/{examineeId}")
	public ResponseEntity<?> getItemResponses(@PathVariable("acronym") final String acronym,
	                                          @PathVariable("examineeId") final String examineeId) {
		String response = this.productService.fetchResponse(acronym, examineeId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/reports/list/{productId}/{formName}")
	public ResponseEntity<?> getFromReports(@PathVariable("productId") final String productId,
	                                        @PathVariable("formName") final String formName) {
		return ResponseEntity.ok(this.productService.listReports(productId, formName));
	}

	@GetMapping("/options/html/{productId}/{formName}/{reportName}")
	public ResponseEntity<?> getReportOptions(@PathVariable("productId") final String productId,
	                                          @PathVariable("formName") final String formName,
	                                          @PathVariable("reportName") final String reportName) {
		final String optionsHTML = this.productService.fetchReportOptions(productId, formName, reportName);
		if (ObjectUtils.isEmpty(optionsHTML)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optionsHTML);
	}

	@GetMapping("/options/js/{productId}/{formName}/{reportName}")
	public ResponseEntity<?> getReportOptionsJS(@PathVariable("productId") final String productId,
	                                          @PathVariable("formName") final String formName,
	                                          @PathVariable("reportName") final String reportName) {

		return ResponseEntity.ok(this.productService.fetchReportOptionsJS(productId, formName, reportName));
	}

	@GetMapping("/options/css/{productId}/{formName}/{reportName}")
	public ResponseEntity<?> getReportOptionsCSS(@PathVariable("productId") final String productId,
	                                                    @PathVariable("formName") final String formName,
	                                                    @PathVariable("reportName") final String reportName) {
		return ResponseEntity.ok(this.productService.fetchReportOptionsCSS(productId, formName, reportName));
	}


	@PutMapping("/options/responses/save")
	public ResponseEntity<?> saveOptionsReponses(@RequestBody ReportOptionsResponse reportOptionsResponse) {
		return ResponseEntity.ok(this.productService.saveReportOptionsReponses(reportOptionsResponse));
	}

	@GetMapping("/options/responses/{examineeId}/{reportId}")
	public ResponseEntity<?> retrieveResponses(@PathVariable("examineeId") final String examineeId,
	                                           @PathVariable("reportId") final String reportId) {
		return ResponseEntity.ok(this.productService.fetchReportOptionsResponses(examineeId, reportId));

	}

}
