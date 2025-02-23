package utilites;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCase.BaseClass;

public class Extentreport implements ITestListener{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);
		
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="myreport1"+timestamp+".html";
	    sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repName);
	    sparkReporter.config().setDocumentTitle("Automation Report"); //Title of report
	    sparkReporter.config().setReportName("Functional Testing");  //name of the report
	    sparkReporter.config().setTheme(Theme.DARK);                 //theam of the report
	    
	    extent=new ExtentReports();
	    extent.attachReporter(sparkReporter);
	    
	    extent.setSystemInfo("Apllication", "OrangeHRM");
	    extent.setSystemInfo("Login", "Admin");
	    extent.setSystemInfo("Sub Module", "Home Page");
	    extent.setSystemInfo("Kishor", System.getProperty("user.name"));
	    extent.setSystemInfo("Enviroment", "QA");
	    
	    String os=testContext.getCurrentXmlTest().getParameter("os");
	    extent.setSystemInfo("Operating System", os);
	    
	    String browser=testContext.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("browser", browser);
	    
	    List<String> includegroups=testContext.getCurrentXmlTest().getIncludedGroups();
	    if(!includegroups.isEmpty()) {
		    extent.setSystemInfo("Groups", includegroups.toString());
	    }
	 }

	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());           //create a new entry in the report
	    test.log(Status.PASS, "Test case PASSED is:"+result.getName()); //update status p/f/s
	    test.log(Status.PASS, "Test case PASSED is:"+result.getName()); 
	  }
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());           //create a new entry in the report
	    test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+"got failed"); //update status p/f/s
	    test.log(Status.INFO,result.getThrowable().getMessage());
	    try {	
	    	String imgPath=new BaseClass().captureScreen(result.getName());
	    	test.addScreenCaptureFromPath(imgPath);
	    }
	    catch(IOException e1) {
	    	e1.printStackTrace();
	    }
	    
	  }
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());  
		test.assignCategory(result.getMethod().getGroups());//create a new entry in the report
		
	    test.log(Status.SKIP,result.getName()+"got skipped");
	    test.log(Status.INFO,result.getThrowable().getMessage());
	  }
	
	public void onFinish(ITestContext testContext) {
	    extent.flush();
	    
	    String pathOfExtentreoport=System.getProperty("user.dir")+"/screenshorts/ss1.png";
	    File extentReport=new File(pathOfExtentreoport);
	    
	    try {
	    	Desktop.getDesktop().browse(extentReport.toURI());
	    }
	    catch(IOException e) {
	    	e.printStackTrace();
	    }
	  }

}
