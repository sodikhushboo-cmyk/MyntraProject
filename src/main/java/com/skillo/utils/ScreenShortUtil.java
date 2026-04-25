/*
 * package com.skillo.utils;
 * 
 * import java.io.File; import java.io.IOException; import
 * java.text.SimpleDateFormat; import java.util.Date;
 * 
 * import org.apache.commons.io.FileUtils; import
 * org.openqa.selenium.OutputType; import
 * org.openqa.selenium.remote.RemoteWebDriver;
 * 
 * import com.skillo.base.Keyword;
 * 
 * public class ScreenShortUtil {
 *//**
	 * Captures a screenshot and saves it to the ./reports/ directory with a
	 * filename that includes the test name and current date/time.
	 *
	 * @param testName The name of the test for which the screenshot is being taken.
	 *//*
		 * public static void getScreenShot(String testName) { // Use the thread-safe
		 * Keyword.getDriver() accessor to obtain the RemoteWebDriver File src =
		 * Keyword.getDriver().getScreenshotAs(OutputType.FILE);
		 * 
		 * String DateTime= new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new
		 * Date());
		 * 
		 * File dest=new File("./reports/"+testName+" "+DateTime+".png"); try {
		 * FileUtils.copyFile(src, dest); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * }
		 * 
		 * 
		 * }
		 */