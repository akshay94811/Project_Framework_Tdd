package Practice;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import Config.PropertFiledata;
import genral.Autoconstatnt;

public class Test1 implements Autoconstatnt{

 public static ExtentSparkReporter Sparkreporter;
  public static ExtentReports reports;
  public ExtentTest test;
  static PropertFiledata pf;
  
  @BeforeTest
public static void setup()throws IOException {
	pf = new PropertFiledata();
	
	/*getting properties data*/
	String AllTestReportsPath = pf.GetPropertyString("AllTestReportsPath");
	String FailTestReportsPath = pf.GetPropertyString("FailTestReportsPath");
	String DocumentTitle = pf.GetPropertyString("ReportDocumentTitle");
	String FailDocumentTitle = pf.GetPropertyString("FailDocumentTitle");
	String ReportName = pf.GetPropertyString("ReportName");
	String FailReportName = pf.GetPropertyString("FailReportName");
	boolean TimelineEnabled = pf.GetPropertyboolean("TimelineEnabled");
	String HostName = pf.GetPropertyString("HostName");
	String Environment = pf.GetPropertyString("Environment");
	String SystemUserName = pf.GetPropertyString("SystemUserName");
	Sparkreporter = new ExtentSparkReporter(AllTestReportsPath).viewConfigurer().viewOrder()
			.as(new ViewName[] { ViewName.TEST, ViewName.CATEGORY, ViewName.EXCEPTION, ViewName.DASHBOARD })
			.apply();

	Sparkreporter.config().setDocumentTitle(DocumentTitle);
	Sparkreporter.config().setTheme(Theme.DARK);
	Sparkreporter.config().setReportName(ReportName);
	Sparkreporter.config().setTimelineEnabled(TimelineEnabled);
	// Sparkreporter.loadXMLConfig(new File("./Extent-Config.xml"));

	ExtentSparkReporter FailSparkReport = new ExtentSparkReporter(FailTestReportsPath).filter().statusFilter()
			.as(new Status[] { Status.FAIL }).apply();
	FailSparkReport.config().setDocumentTitle(FailDocumentTitle);
	FailSparkReport.config().setReportName(FailReportName);
	FailSparkReport.config().setTimelineEnabled(TimelineEnabled);
	FailSparkReport.config().setTheme(Theme.DARK);
	reports = new ExtentReports();
	reports.attachReporter(Sparkreporter, FailSparkReport);
	reports.setSystemInfo("Host Name", HostName);
	reports.setSystemInfo("Environment", Environment);
	reports.setSystemInfo("User Name", SystemUserName);
	//reports.flush();
	}

  @Test
  public void Test2() 
  {
	  test = reports.createTest("TC_01:Successfull login");
	  test.log(Status.PASS, "successfuly");
	  System.out.println("Hello");
  }
  
  @AfterTest
  public void  Test3() {
	  reports.flush();
  }
}


