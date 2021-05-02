package bora.emmaObjects;

public class LogInTestData {

	public String testCaseId;
	public String email;
	public String password;
	public String scenarioType;

	public LogInTestData(String testCaseId, String email, String password, String scenarioType) {

		this.testCaseId=testCaseId;
		this.email=email;
		this.password=password;
		this.scenarioType=scenarioType;
	}
	public LogInTestData() {
		
	}
}
