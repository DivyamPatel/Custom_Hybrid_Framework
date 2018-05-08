package coreclasses;

import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.cucumber.listener.ExtentProperties;

import enums.ConfigurationFile;
import enums.ConfigurationProperties;
import managers.FileReaderManager;
import runners.TestRunner1;

public class MyTestListener implements ITestListener, IExecutionListener {

	MailSender sendEmail = new MailSender();
	TestRunner1 testRunner1 = new TestRunner1();
	
	public void onExecutionStart() {
		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
		extentProperties.setReportPath(testRunner1.getReportPath() + "/report.html");
	}

	public void onExecutionFinish() {
		sendEmail.sendMail();
		int lastRun = Integer.parseInt(FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).getConfProperty(ConfigurationProperties.LAST_RUN));
		lastRun = lastRun + 1;
		FileReaderManager.getInstance().getConfigReader(ConfigurationFile.GENERAL).setConfProperty(ConfigurationProperties.LAST_RUN, Integer.toString(lastRun));
	}

	public void onTestStart(ITestResult result) {
	
	}

	public void onTestSuccess(ITestResult result) {
		
	}

	public void onTestFailure(ITestResult result) {
	
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		sendEmail.passed = context.getPassedTests().size();
		sendEmail.failed = context.getFailedTests().size();
		sendEmail.skipped = context.getSkippedTests().size();
	}

}
