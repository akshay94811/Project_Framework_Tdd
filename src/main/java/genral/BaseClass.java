package genral;


    import java.awt.Desktop;
	import java.io.File;
	import java.io.IOException;
	import java.net.URL;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.PageLoadStrategy;
	import org.openqa.selenium.Platform;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.openqa.selenium.support.events.EventFiringWebDriver;
    import org.openqa.selenium.support.events.WebDriverEventListener;
    import org.testng.ITestResult;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeTest;
    import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.MediaEntityBuilder;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;
	import com.aventstack.extentreports.reporter.configuration.ViewName;

    import Config.PropertFiledata;
    import Utils.Atachscreebshots;
    import Utils.WebEventListener;
    
    
 public class BaseClass  implements Autoconstatnt {

	  public static WebDriver driver;
	  static PropertFiledata pf;
	  public static ChromeOptions options;
	  public static EventFiringWebDriver e_driver;
	  public static WebDriverEventListener eventListener;
	  public ExtentSparkReporter Sparkreporter;
	  public static ExtentReports reports;
	  public ExtentTest test;

@BeforeTest
public void Setup() throws IOException {
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
}

@BeforeMethod
public void initialization() throws IOException, Exception {
  // public static void main(String[] args) throws IOException, Exception {
	pf = new PropertFiledata();
	String url = pf.GetPropertyString("url");
	final boolean BaseFlag = pf.GetPropertyboolean("BaseFlag");
	System.out.println("DriverBaseFlag " + BaseFlag);
	
	if (BaseFlag) {
		DesiredCapabilities cap = new DesiredCapabilities();
		String browser = pf.GetPropertyString("browser");
		cap.setBrowserName(browser);
		cap.setPlatform(Platform.WINDOWS);
		ChromeOptions Option = new ChromeOptions();
		Option.merge(cap);
		String Huburl = pf.GetPropertyString("Huburl");
		driver = new RemoteWebDriver(new URL(Huburl), Option);
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		driver.manage().timeouts().pageLoadTimeout(PageLoaderTime, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(ImplicitWaitTime, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		System.out.println("Running Script in Remote");
	} else {
	String chrome_key = pf.GetPropertyString("ChromeDriverKey");
	String chrome_value = pf.GetPropertyString("ChromeDriverPath");
	System.setProperty(chrome_key, chrome_value);
	options = new ChromeOptions();
	options.setPageLoadStrategy(PageLoadStrategy.EAGER);
	driver = new ChromeDriver(options);
	/*
	 * if(browserss.equalsIgnoreCase("chrome")) { System.setProperty(chrome_key,
	 * chrome_value); driver=new ChromeDriver(); }
	 * 
	 * if(browserss.equalsIgnoreCase("edge")) { System.setProperty(edge_key,
	 * edge_value); driver=new EdgeDriver(); }
	 */
	driver.manage().timeouts().pageLoadTimeout(PageLoaderTime, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(ImplicitWaitTime, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(url);
		}

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
	}

@AfterMethod
public void Teardown(ITestResult ITresult) throws IOException {
	TestcaseCategories Catg = new TestcaseCategories();
	Atachscreebshots ss = new Atachscreebshots();
	int Tcresult = ITresult.getStatus();
	System.out.println("Test Result Value :" + ITresult);
	String ClassName = ITresult.getClass().getName();
	System.out.println("ClassName: " + ClassName);
	String ObjClassName = Catg.CatgClassName(ClassName);
	System.out.println("ObjClassName: " + ObjClassName);
	test.assignCategory(ObjClassName);
	String name = ITresult.getName();
	String ScreenshotPath = ss.getScreenshotAsBase64(driver, name);
	if (Tcresult == ITestResult.SUCCESS) {
		test.log(Status.PASS, "Successfully Passed the Test case: " + name);
	} else if (Tcresult == ITestResult.FAILURE) {
		test.log(Status.FAIL, ITresult.getThrowable());
		test.log(Status.FAIL, "Fail TC: " + name + " :" + ITresult.getThrowable().getMessage());
		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotPath).build());
	} else if (Tcresult == ITestResult.SKIP) {
		test.log(Status.SKIP, ITresult.getThrowable().getCause());
      }
	        reports.flush();
			driver.quit();
		}
	    
@AfterTest
public void OpenReport() throws IOException {
	String Allreportpath = pf.GetPropertyString("AutoOpenFailedReport");
	String Failreportpath = pf.GetPropertyString("AutoOpenAllTestReport");
	Desktop.getDesktop().browse(new File(Allreportpath).toURI());
	Desktop.getDesktop().browse(new File(Failreportpath).toURI());



}
}


