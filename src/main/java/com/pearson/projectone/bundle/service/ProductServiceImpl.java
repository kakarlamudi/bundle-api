package com.pearson.projectone.bundle.service;

import com.pearson.assessment.AssessmentComponent;
import com.pearson.assessment.AssessmentComponentFactory;
import com.pearson.assessment.AssessmentException;
import com.pearson.assessment.Form;
import com.pearson.assessment.Report;
import com.pearson.projectone.bundle.dao.ReportOptionsResponseDao;
import com.pearson.projectone.bundle.dao.ResponseDao;
import com.pearson.projectone.bundle.entity.ReportOptionsResponse;
import com.pearson.projectone.bundle.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

	@Value("${bundle.location}")
	private String bundleLocation;

	@Value("${report.output.location}")
	private String reportOutputLocation;

	@Autowired
	ResponseDao responseDao;

	@Autowired
	ReportOptionsResponseDao reportOptionsResponseDao;


	@Autowired
	AssessmentComponent assessmentComponent;

	/**
	 * Create Assessment
	 *
	 * @return
	 */
	@Bean
	public AssessmentComponent createPlatformComponent() {

		final String dateFormat = "MM-dd-yyyy";
		return AssessmentComponentFactory.createAssessmentComponent(bundleLocation, reportOutputLocation, dateFormat);
	}



//	@Override
//	public Map<String, String[]> listProducts() {
//		Map<String, String[]> formMap = new HashMap<>();
//		List<String>  productIds = assessmentComponent.getAvailableProductIds();
//		productIds.forEach(p -> {
//			try {
//				List<String> list = new ArrayList<String>();
//				assessmentComponent.getBundleDescription(p).getForms().entrySet().stream()
//						.map(v -> v.getValue().getName()).forEach(v -> list.add(v));
//				formMap.put(p, list.toArray(new String[list.size()]));
//			} catch (AssessmentException e) {
//				e.printStackTrace();
//			}
//		});
//		return formMap;
//	}


	@Override
	public String[] listProducts() {
		List<String> productIds = assessmentComponent.getAvailableProductIds();
		return productIds.toArray(new String[productIds.size()]);
	}

	@Override
	public String[] listForms(final String productId) throws AssessmentException {
		return assessmentComponent.getBundleDescription(productId).getForms().entrySet()
				.stream().map(v -> v.getValue().getName()).toArray(String[]::new);
	}


	@Override
	public String getItemEntryHTML(final String productId, final String formName) {

		String html = null;
		Form form = null;
		form = getAssessmentForm(productId, formName);
		html = readItemEntryHTML(form).replace("</col>", "");
		return html;
	}

	/**
	 * Gets Form object.
	 *
	 * @param productId
	 * @param formName
	 * @return
	 * @throws AssessmentException
	 */
	private Form getAssessmentForm(String productId, String formName) {
		Form form = null;
		try {
			form = assessmentComponent.getBundleDescription(productId).getForms().entrySet()
					.stream().filter(e -> e.getValue().getName().equalsIgnoreCase(formName))
					.findFirst().map(v -> v.getValue()).get();
		} catch (AssessmentException e) {
			e.printStackTrace();
		}
		return form;
	}

	/**
	 * Reads Item Entry HTML from InputStream.
	 *
	 * @param form
	 * @return
	 */
	private String readItemEntryHTML(Form form) {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
				assessmentComponent.retrieveItemEntryHTMLFor(form)))) {
			return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (AssessmentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String retrieveCSS(final String productId, final String formName) {
		final String BEGIN_SCRIPT = "<style ";
		final String END_SCRIPT = "</style>";
		return extractCodeSegment(productId, formName, BEGIN_SCRIPT, END_SCRIPT);
	}


	@Override
	public String retrieveJS(final String productId, final String formName) {
		final String BEGIN_SCRIPT = "<script ";
		final String END_SCRIPT = "</script>";
		return extractCodeSegment(productId, formName, BEGIN_SCRIPT, END_SCRIPT);
	}

	private String extractCodeSegment(final String productId, final String formName, String BEGIN_SCRIPT, String END_SCRIPT) {
		String html = this.getItemEntryHTML(productId, formName);
		return extractCodeSegment(BEGIN_SCRIPT, END_SCRIPT, html);
	}

	/**
	 * Extracts code segments.
	 *
	 * @param BEGIN_SCRIPT
	 * @param END_SCRIPT
	 * @param html
	 * @return
	 */
	private String extractCodeSegment(String BEGIN_SCRIPT, String END_SCRIPT, String html) {
		int startIndex = html.indexOf(BEGIN_SCRIPT);
		startIndex = html.indexOf(">", startIndex) + 1;
		int endIndex = html.indexOf(END_SCRIPT);
		if (startIndex >= 0 && endIndex >= 0) {
			return html.substring(startIndex, endIndex);
		}

		return "";
	}

	@Override
	public Response saveResponse(final Response response) {
		Response existingResponse = this.responseDao.findByAcronymAndExamineeId(response.getAcronym(), response.getExamineeId());
		if (!ObjectUtils.isEmpty(existingResponse)) {
			existingResponse.setResponses(response.getResponses());
		} else {
			existingResponse = response;
		}
		System.out.println(response.getResponses());
		Response savedResponse = this.responseDao.save(existingResponse);
		return savedResponse;
	}

	@Override
	public String fetchResponse(String acronym, String examineeId) {
		Response response = this.responseDao.findByAcronymAndExamineeId(acronym, examineeId);
		if (ObjectUtils.isEmpty(response)) {
			return "";
		}
		System.out.println(response.getResponses());
		return response.getResponses();
	}

	@Override
	public String[] listReports(final String productId, final String formName) {
		Form form = this.getAssessmentForm(productId, formName);
		String[] reportNames = form.getSupportedReports().stream().map(r -> r.getId()).toArray(String[]::new);
		return reportNames;
	}

	@Override
	public String fetchReportOptions(String productId, String formName, final String reportName) {

		Form form = this.getAssessmentForm(productId, formName);
		Optional<Report> optional = form.getSupportedReports().stream().filter(r -> r.getId().equalsIgnoreCase(reportName))
				.findFirst();
		if (!optional.isPresent() || ObjectUtils.isEmpty(optional.get().getOptionsHTML())) {
			return null;
		}

		String reportOptionsHTML = null;
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
				this.assessmentComponent.retrieveReportOptionsHTMLFor(optional.get())))) {
			reportOptionsHTML = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator())).replace("</col>", "");

		} catch (AssessmentException ex) {
			reportOptionsHTML = null;
			ex.printStackTrace();
		} catch (IOException e) {
			reportOptionsHTML = null;
			e.printStackTrace();
		}
		return reportOptionsHTML;
	}


	@Override
	public String fetchReportOptionsCSS(String productId, String formName, final String reportName) {
		String optionsHTML = this.fetchReportOptions(productId, formName, reportName);
		if (!ObjectUtils.isEmpty(optionsHTML)) {
			final String BEGIN_SCRIPT = "<style";
			final String END_SCRIPT = "</style>";
			return this.extractCodeSegment(BEGIN_SCRIPT, END_SCRIPT, optionsHTML);
		}
		return "";
	}


	@Override
	public String fetchReportOptionsJS(String productId, String formName, final String reportName) {
		String optionsHTML = this.fetchReportOptions(productId, formName, reportName);
		if (!ObjectUtils.isEmpty(optionsHTML)) {
			final String BEGIN_SCRIPT = "<script";
			final String END_SCRIPT = "</script>";
			return this.extractCodeSegment(BEGIN_SCRIPT, END_SCRIPT, optionsHTML);
		}
		return "";
	}


	@Override
	public ReportOptionsResponse saveReportOptionsReponses(ReportOptionsResponse reportOptionsResponse) {
		ReportOptionsResponse existingResponse = this.reportOptionsResponseDao.findByExamineeIdAndReportId(
				reportOptionsResponse.getExamineeId(), reportOptionsResponse.getReportId());
		if (!ObjectUtils.isEmpty(existingResponse)) {
			existingResponse.setResponse(reportOptionsResponse.getResponse());
		} else {
			existingResponse = reportOptionsResponse;
		}
		return this.reportOptionsResponseDao.save(existingResponse);

	}

	@Override
	public ReportOptionsResponse fetchReportOptionsResponses(String examineeId, String reportId) {
		ReportOptionsResponse reportOptionsResponse =
				this.reportOptionsResponseDao.findByExamineeIdAndReportId(examineeId, reportId);
		return reportOptionsResponse;
	}
}
