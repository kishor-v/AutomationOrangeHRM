package testCase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public static WebDriver driver;  
    public Logger logger;
    public Properties p;
    
    
    @BeforeClass(groups= {"sanity","regression","master"}) //add this() for group testing for both setup and teardown()
    
    @Parameters({"os","browser"})
    void Setup(String os,String br) throws IOException {
    	
    	//file transfer from config.properties to my " test case " and  " setup() "
    	
    	FileReader file=new FileReader("./src/test/resources/config.properties");
		p=new Properties();
		p.load(file);

		//setting the log file 
        logger = LogManager.getLogger(this.getClass());
        
        // Log a test message to verify logging
        logger.debug("Starting test execution...");
        
        //chose the browser to execute
        switch(br.toLowerCase()) 
        {
        case "chrome" : driver=new ChromeDriver();
        break; 
        case "firefox" : driver=new FirefoxDriver();
        break;
        case "edge" : driver=new EdgeDriver();
        break;
        default :System.out.println("Invaild Browsers!!!");
        return;
        }
        
        
//        if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
//			DesiredCapabilities cap=new DesiredCapabilities();
//			
//			//os
//			if(os.equalsIgnoreCase("windows")) {
//				cap.setPlatform(Platform.WIN10);
//			}
//			else {
//				System.out.println("No matching os");
//				return;
//			}
//			
//			//browser
//			
//			switch(br.toLowerCase()) {
//			case "chrome" : cap.setBrowserName("chrome"); break;
//			case "edge" : cap.setBrowserName("edge"); break;
//			case "firefox" : cap.setBrowserName("firefox"); break;
//			default : System.out.println("No Matching browser"); return;
//			}
//			driver=new RemoteWebDriver(new URL("http://192.168.56.1:4444"),cap);
//		}
//		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
//			switch(br.toLowerCase()) {
//			case "chrome" : driver=new ChromeDriver(); break;
//			case "edge" : driver=new EdgeDriver(); break;
//			case "firefox" : driver=new FirefoxDriver(); break;
//			default : System.out.println("Invaild br");
//			}
//		}
//		


        driver.get(p.getProperty("appURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Log URL and title to verify logging in the setup
        logger.info("Navigated to the application: " + driver.getCurrentUrl());
    }
    
    @AfterClass(groups= {"sanity","regression","master"})//add this() for group testing for both setup and teardown
    void TearDown() {
        // Log the teardown phase
        logger.debug("Test execution completed. Closing the browser.");
        driver.quit();
    }
    
    // Random String Generator Methods (same as your original)
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomNumeric(3);
    }

    // Capture screenshot method (same as your original)
    public String captureScreen(String tname) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
        File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir")+"/screenshorts/ss1.png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
