package e_CommerceApp.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentHtmlReport {

	public static ExtentReports extentReport() {

		// add ExtentSparkReporter dependency
		// ExtentSparkReporter - Basic config like htlm file, title name, document name
		String filePath = System.getProperty("user.dir") + "\\reports\\E2ETest.html";
		ExtentSparkReporter report = new ExtentSparkReporter(filePath);
		report.config().setDocumentTitle("Test Result");
		report.config().setReportName("Automation Test Reports");

		// ExtentReports - main class for drive all your reporting exections
		ExtentReports context = new ExtentReports();
		context.attachReporter(report);
		context.setSystemInfo("Tester", "Hemanth S");
		return context;
	}
}
