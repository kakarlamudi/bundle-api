package com.pearson.projectone.bundle.service;

import com.pearson.assessment.AssessmentException;
import com.pearson.projectone.bundle.entity.ReportOptionsResponse;
import com.pearson.projectone.bundle.entity.Response;

import java.util.List;

public interface ProductService {

	/**
	 * Lists all bundle products
	 *
	 * @return
	 */
	public String[] listProducts();

	/**
	 * Lists forms for the given productId
	 *
	 * @param productId
	 * @return
	 */
	public String[] listForms(final String productId) throws AssessmentException;

	/**
	 * Get Item entry HTML for the given productId and formName.
	 *
	 * @param productId
	 * @param formName
	 * @return
	 */
	public String getItemEntryHTML(final String productId, final String formName);

	/**
	 * Retrieves JavaScript from the manual entry HTML retrieved by the given productId and formName.
	 *
	 * @param productId
	 * @param formName
	 * @return
	 */
	public String retrieveJS(final String productId, final String formName);

	/**
	 * Retrieves CSS from the manual entry HTML retrieved by the given productId and formName.
	 *
	 * @param productId
	 * @param formName
	 * @return
	 */
	public String retrieveCSS(final String productId, final String formName);

	/**
	 * Save responses.
	 *
	 * @param response
	 */
	public Response saveResponse(final Response response);

	/**
	 * Fetches item responses by acronym.
	 *
	 * @param acronym
	 * @param examineeId
	 * @return
	 */
	public String fetchResponse(String acronym, String examineeId);


	/**
	 * Retrieves report options.
	 * @param productId
	 * @param formName
	 * @param reportName
	 * @return
	 */
	public String fetchReportOptions(String productId, String formName, final String reportName);

	/**
	 * Retrieves report options.
	 * @param productId
	 * @param formName
	 * @param reportName
	 * @return
	 */
	public String fetchReportOptionsJS(String productId, String formName, final String reportName);

	/**
	 * Extracts CSS code from the given html code.
	 * @param productId
	 * @param formName
	 * @param reportName
	 * @return
	 */
	public String fetchReportOptionsCSS(String productId, String formName, final String reportName);


	/**
	 * Lists all reports for given productId.
	 * @param productId
	 * @param formName
	 * @return
	 */
	public String[] listReports(final String productId, final String formName);

	/**
	 * Saves Report options response.
	 * @param response
	 * @return
	 */
	public ReportOptionsResponse saveReportOptionsReponses(ReportOptionsResponse response);

	/**
	 * Fetches Report options reponses.
	 * @param examineeId
	 * @param reportId
	 * @return
	 */
	public ReportOptionsResponse fetchReportOptionsResponses(String examineeId, String reportId);
}
