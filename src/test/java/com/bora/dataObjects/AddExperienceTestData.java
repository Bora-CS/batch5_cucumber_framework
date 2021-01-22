package com.bora.dataObjects;

public class AddExperienceTestData {

	public boolean isHappy;
	public String jobTitle;
	public String company;
	public String location;
	public String fromDate;
	public boolean currentJob;
	public String toDate;
	public String jobDescription;
	public String[] expectedErrors;

	public AddExperienceTestData(boolean isHappy, String jobTitle, String company, String location, String fromDate,
			boolean currentJob, String toDate, String jobDescription, String[] expectedErrors) {
		this.jobTitle = jobTitle;
		this.company = company;
		this.location = location;
		this.fromDate = fromDate;
		this.currentJob = currentJob;
		this.toDate = toDate;
		this.jobDescription = jobDescription;
		this.isHappy = isHappy;
		this.expectedErrors = expectedErrors;
	}

	public AddExperienceTestData() {
	}

}
