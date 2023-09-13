package com.magic.project.models;

import javax.persistence.Embeddable;

@Embeddable
public class PrescribedTest {

	private String testName;
	private String testResult;

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

}