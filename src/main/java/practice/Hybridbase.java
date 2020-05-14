package practice;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Hybridbase {
	
	 public static AppiumDriverLocalService service;	
	 public static AndroidDriver<AndroidElement> driver;
	 
	public AppiumDriverLocalService startServer() 
	{
		boolean flag = checkIfServerIsRunnning(4723);
		if(!flag)
		{
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}		
		return service;
	}
	
public static boolean checkIfServerIsRunnning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}


	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("C:\\Users\\Himanshu\\AppiumFramework\\src\\main\\java\\resources\\startemulator.bat");
		//Runtime.getRuntime().exec(System.getProperty("user.dir")+"src\\main\\java\\resources\\startemulator.bat");
		Thread.sleep(6000);
	}

	public static AndroidDriver<AndroidElement> Capabilities(String appName) throws IOException, InterruptedException {
		

		
		FileInputStream fis = new FileInputStream("C:\\Users\\Himanshu\\AppiumFramework\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		
//This file path is to open the file.		
		File f = new File("src");
		File fs = new File(f,(String) prop.get(appName));
//This is to set the capabilities.
		
		DesiredCapabilities cap = new DesiredCapabilities();
		String device = (String) prop.get("device");
		if (device.contains("emulator")){		
			startEmulator();
		}
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,14);
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
		

	}
	
 public static void getScreenshot(String s) throws IOException {
	 
	  
    	
    	File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(scr,new File(System.getProperty("user.dir")+"\\"+s+".png"));
    	
    }
	
/*	public static void getScreenshot(String s) throws IOException {
		 
		try{
		// To create reference of TakesScreenshot
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		// Call method to capture screenshot
		String src=screenshot.getScreenshotAs(OutputType.BASE64);
		// Copy files to specific location
		// result.getName() will return name of test case so that screenshot name will be same as test case name
		FileHandler.copy(src, "");
		//FileUtils.copyToFile(src, new File(s+".png"));
		System.out.println("Successfully captured a screenshot");
		}catch (Exception e){
		System.out.println("Exception while taking screenshot "+e.getMessage());
		}
		 
		}*/

}
