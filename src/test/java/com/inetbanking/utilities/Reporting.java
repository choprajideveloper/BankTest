package com.inetbanking.utilities;
// Listener class used to generate extent reports
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;


	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-" + timeStamp + ".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);
		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "deepak");

		htmlReporter.config().setDocumentTitle("InetBanking Test Project"); // title
		htmlReporter.config().setReportName("Functional Test Report"); // name of report
		htmlReporter.config().setTheme(Theme.DARK);

	}

	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName()); // create new Entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

	}

	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName()); // create new Entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		String screenshotPath = System.getProperty("user.dir") + "\\Screenshot" + tr.getName() + ".png";

		File f = new File(screenshotPath);

		if (f.exists()) {

			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		}
	}

	public void onTestSkipper(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP,
				com.aventstack.extentreports.markuputils.MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));

	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
