package testApps;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.Homepage;
import pageObjects.Preferences;
import practice.Hybridbase;
import practice.TestData;

public class ApitestDemo extends Hybridbase {
	
	@Test(dataProvider="InputData",dataProviderClass = TestData.class)
	

	public void apiDemo(String input) throws IOException, InterruptedException {
		
		service = startServer();
		
		AndroidDriver<AndroidElement> driver = Capabilities("apiDemoApp");
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);		
		
		Homepage h = new Homepage(driver);
		h.Preference.click();
		
		//driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		
		Preferences p = new Preferences(driver);
		p.Dependency.click();
		//driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementByClassName("android.widget.EditText").sendKeys(input);
		//driver.findElementsByClassName("android.widget.Button").get(1).click();
		p.Buttons.get(1).click();
		service.stop();
	}
	

}






