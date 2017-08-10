package com.pearson.projectone.bundle.service;

import com.pearson.assessment.Assessment;
import com.pearson.assessment.AssessmentComponent;
import com.pearson.assessment.AssessmentComponentFactory;
import com.pearson.assessment.AssessmentException;
import com.pearson.assessment.AssessmentScoringException;
import com.pearson.assessment.Form;
import com.pearson.assessment.Product;
import com.pearson.assessment.Report;
import com.pearson.assessment.ScorabilityCheck;
import com.pearson.bundle.MutableForm;
import com.pearson.bundle.MutableProduct;
import com.pearson.bundle.MutableReport;
import com.pearson.domain.FormImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.pearson.projectone.bundle.util.CommonUtil.toJson;

@Service
public class ItemEntryServiceImpl implements ItemEntryService {

//	@Value("${bundle.location}")
//	private String bundleLocation;
//
//	@Value("${report.output.location}")
//	private String reportOutputLocation;
//
//	private static Map<String, String> fileNameMap;
//
//	@Autowired
//	AssessmentComponent assessmentComponent;
//
//
//	static {
//		initialize();
//	}
//
//	public static void initialize() {
//		fileNameMap = new HashMap<>();
//		fileNameMap.put("dojojs", "/Users/ukakapr/development/qg/qglobal-static-content/qglobal-static/src/main/resources/js/dojotoolkit/dojo/");
//		fileNameMap.put("dojocss", "/Users/ukakapr/development/qg/qglobal-static-content/qglobal-static/src/main/resources/js/dojotoolkit/dojo/resources/");
//		fileNameMap.put("tundracss", "/Users/ukakapr/development/qg/qglobal-static-content/qglobal-static/src/main/resources/js/dojotoolkit/dijit/themes/tundra/");
//
//	}
//
//	@Override
//	public String readBundleDescription(final String productId) {
//
//		Form form = new FormImpl();
//		Product product = null;
//
//		try {
//			product = assessmentComponent.getBundleDescription(productId);
//		} catch (AssessmentException e) {
//			e.printStackTrace();
//		}
//
//		return toJson(product.getScoring());
//	}
//
//	@Override
//	public String retrieveHTML(final String formId) {
//
//		Form form = this.prepareForm();
//		String html = null;
//
//		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
//				assessmentComponent.retrieveItemEntryHTMLFor(form)))) {
//			html = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
//			/**
//			 * Remove this
//			 */
//			//Remove first </tr> in Dyslexia screen
//			html = html.replace("</tr>", "");
//		} catch (AssessmentException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return html;
//	}
//
//	private Form prepareForm() {
//		MutableForm form = new MutableForm("1", "BAI", "BAI-ie");
//		form.setProduct(new MutableProduct("BAI", "BAI", ""));
//		return form;
//	}
//
//	@Override
//	public String[] getAllProductCodes() {
//		List<String> productList = assessmentComponent.getAvailableProductIds();
//		return productList == null ? new String[0] : productList.toArray(new String[productList.size()]);
//	}
//
//	@Override
//	public String getReportsOptions(final String productCode) {
//		Report report = prepareReport();
//		String optionsHTML = "";
//		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
//				assessmentComponent.retrieveReportOptionsHTMLFor(report)))) {
//			optionsHTML = bufferedReader.lines().collect(Collectors.joining());
//
//		} catch (AssessmentException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return optionsHTML;
//	}
//
//	@Override
//	public ScorabilityCheck.ScoringError[] checkDemographics(final String productCode) {
//		MutableForm form = new MutableForm("18", "BASC-3", "basc-3-prs-preschool-ie");
//		form.setProduct(new MutableProduct("BASC-3", "BASC-3", ""));
//		form.setDemographicCheck("check");
//		List<ScorabilityCheck.ScoringError> errors = new ArrayList<>();
//		try {
//			ScorabilityCheck scorabilityCheck = assessmentComponent.checkDemographics(form, this.prepareAssessment());
//			if (!scorabilityCheck.isScorable()) {
//				errors = scorabilityCheck.getScoringErrors();
//			}
//		} catch (AssessmentException e) {
//			e.printStackTrace();
//		} catch (AssessmentScoringException e) {
//			e.printStackTrace();
//		}
//		return errors.toArray(new ScorabilityCheck.ScoringError[errors.size()]);
//	}
//
//	@Override
//	public String retrieveJS(String formId) {
//		final String BEGIN_SCRIPT = "<script ";
//		final String END_SCRIPT = "</script>";
//		return extractCodeSegment(formId, BEGIN_SCRIPT, END_SCRIPT);
//	}
//
//	private String extractCodeSegment(String formId, String BEGIN_SCRIPT, String END_SCRIPT) {
//		String html = this.retrieveHTML(formId);
//		int startIndex = html.indexOf(BEGIN_SCRIPT);
//		startIndex = html.indexOf(">", startIndex) + 1;
//		int endIndex = html.indexOf(END_SCRIPT);
//		if (startIndex >= 0 && endIndex >= 0) {
//			return html.substring(startIndex, endIndex);
//		}
//
//		return "";
//	}
//
//	@Override
//	public String retrieveCSS(String formId) {
//		final String BEGIN_SCRIPT = "<style ";
//		final String END_SCRIPT = "</style>";
//		return extractCodeSegment(formId, BEGIN_SCRIPT, END_SCRIPT);
//	}
//
//	@Override
//	public String getDojoJSLibraryFile(final String jsFileName) {
//		String dojoRequire = "dojo.require('dojo.parser');dojo.require('dijit.Dialog');dojo.require('dijit.form.Form'); " +
//				"dojo.require('dijit.layout.TabContainer');dojo.require('dijit.layout.ContentPane');" +
//				"dojo.require('dijit.form.ValidationTextBox');dojo.require('dijit.form.DateTextBox'); " +
//				"dojo.require('dijit.TitlePane');dojo.require('dijit.Dialog');dojo.require('dijit.form.Textarea');" +
//				"dojo.require('dijit.Tooltip');";
//		return readFile(jsFileName, "js") + System.lineSeparator() + dojoRequire;
//	}
//
//	@Override
//	public String getDojoCSSLibraryFile(final String cssFileName) {
//		return readFile(cssFileName, "css");
//	}
//
//	private String readFile(final String fileName, final String type) {
//		String fileString = "";
//		String directoryPath = fileNameMap.get(fileName + type);
//		if (directoryPath == null) {
//			return null;
//		}
//		String filepath = fileNameMap.get(fileName + type) + fileName + "." + type;
//		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))) {
//			fileString = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return fileString;
//
//	}
//
//
//
//
//	public Report prepareReport() {
//		MutableReport report = new MutableReport();
//		report.setId("BASC3Standard");
//		report.setName("BASC-3 Report");
//		report.setScoringId(1);
//		report.setOptionsHTML("basc-3-default-ro");
//		report.setProduct(new MutableProduct("BASC-3", "BASC-3", ""));
//		return report;
//	}
//
//
//	private Assessment prepareAssessment() {
//		Map<String, String> assessmentInfo = new HashMap<String, String>();
//		assessmentInfo.put("DOA", "01-01-2019");
//		assessmentInfo.put("grade", "1");
//		Assessment assessment = new Assessment(new MutableProduct("BASC-3", "BASC-3", ""), new HashMap<String, String>());
//		return assessment;
//	}


}
