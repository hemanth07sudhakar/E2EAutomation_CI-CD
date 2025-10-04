package e_CommerceApp.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import e_CommerceApp.resources.ExtentHtmlReport;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest extent;
	// access the report class - using object,using direct call class name
	ExtentReports data = ExtentHtmlReport.extentReport();

	// to prevent the concurrency issues use ThreadSafe Machanism
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// Called when a test method starts
		extent = data.createTest(result.getMethod().getMethodName());
		extentTest.set(extent);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Called when a test method passes
		extentTest.get().log(Status.PASS, "Test Success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Called when a test method fails (commonly used for screenshots)
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = null;
		try {
			path = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(path);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Called when a test method is skipped
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Rarely used (handles partial failures)
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// Called when test fails due to timeout
	}

	@Override
	public void onStart(ITestContext context) {
		// Before <test> tag in testng.xml
	}

	@Override
	public void onFinish(ITestContext context) {
		// After <test> tag finishes
		data.flush();// reports ends here
	}
}
