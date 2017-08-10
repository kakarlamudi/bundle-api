package com.pearson.projectone.bundle.dto;

import java.io.Serializable;

public class PlatformValues implements Serializable {

	private Integer age;
	private String administrationDate;
	private String birthDate;
	private String autoAdvance = "yes";
	private String deliveryType = "MANUAL";
	private String status = "";
	private String examinee = "Examinee";
	private String noOfRuns = "1";
	private String noOfExportRuns = "1";
	private String gender = "Male";
	private String userDateFormat = "yyyy/MM/dd";
	private String dataCollection_UserOpt = "N";
	private String scoringEngine;
	private String flxFormItems;
	private String batterySelected;

	private String firstName;
	private String lastName;
	private String middleName;

	public PlatformValues() {
		//default constructor
	}

	public PlatformValues(Integer age, String administrationDate, String birthDate, String status, String gender,
	                      String firstName, String lastName, String middleName) {
		this.age = age;
		this.administrationDate = administrationDate;
		this.birthDate = birthDate;
		this.status = status;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
	}

	public PlatformValues(Integer age, String administrationDate, String birthDate, String autoAdvance,
	                      String deliveryType, String status, String examinee, String noOfRuns, String noOfExportRuns,
	                      String gender, String userDateFormat, String dataCollection_UserOpt, String scoringEngine,
	                      String flxFormItems, String batterySelected, String firstName, String lastName,
	                      String middleName) {
		this.age = age;
		this.administrationDate = administrationDate;
		this.birthDate = birthDate;
		this.autoAdvance = autoAdvance;
		this.deliveryType = deliveryType;
		this.status = status;
		this.examinee = examinee;
		this.noOfRuns = noOfRuns;
		this.noOfExportRuns = noOfExportRuns;
		this.gender = gender;
		this.userDateFormat = userDateFormat;
		this.dataCollection_UserOpt = dataCollection_UserOpt;
		this.scoringEngine = scoringEngine;
		this.flxFormItems = flxFormItems;
		this.batterySelected = batterySelected;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAdministrationDate() {
		return administrationDate;
	}

	public void setAdministrationDate(String administrationDate) {
		this.administrationDate = administrationDate;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAutoAdvance() {
		return autoAdvance;
	}

	public void setAutoAdvance(String autoAdvance) {
		this.autoAdvance = autoAdvance;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExaminee() {
		return examinee;
	}

	public void setExaminee(String examinee) {
		this.examinee = examinee;
	}

	public String getNoOfRuns() {
		return noOfRuns;
	}

	public void setNoOfRuns(String noOfRuns) {
		this.noOfRuns = noOfRuns;
	}

	public String getNoOfExportRuns() {
		return noOfExportRuns;
	}

	public void setNoOfExportRuns(String noOfExportRuns) {
		this.noOfExportRuns = noOfExportRuns;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserDateFormat() {
		return userDateFormat;
	}

	public void setUserDateFormat(String userDateFormat) {
		this.userDateFormat = userDateFormat;
	}

	public String getDataCollection_UserOpt() {
		return dataCollection_UserOpt;
	}

	public void setDataCollection_UserOpt(String dataCollection_UserOpt) {
		this.dataCollection_UserOpt = dataCollection_UserOpt;
	}

	public String getScoringEngine() {
		return scoringEngine;
	}

	public void setScoringEngine(String scoringEngine) {
		this.scoringEngine = scoringEngine;
	}

	public String getFlxFormItems() {
		return flxFormItems;
	}

	public void setFlxFormItems(String flxFormItems) {
		this.flxFormItems = flxFormItems;
	}

	public String getBatterySelected() {
		return batterySelected;
	}

	public void setBatterySelected(String batterySelected) {
		this.batterySelected = batterySelected;
	}
}
