package Practice.CommonUtility;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporting {
	public static ExtentReports getReportObject(){
		File file=new File(System.getProperty("user.dir") + "\\Reports\\index.html");
		ExtentSparkReporter reporter=new ExtentSparkReporter(file);
		reporter.config().setReportName("Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Avinash");
		return extent;
	}
}
