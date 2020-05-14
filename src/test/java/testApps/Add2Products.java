package testApps;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CheckoutPage;
import pageObjects.Formpage;
import pageObjects.Utilities;
import practice.Hybridbase;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;


public class Add2Products extends Hybridbase {
	
	@Test

	public void totalValidation() throws InterruptedException, IOException {
		
		service = startServer();
		
		AndroidDriver<AndroidElement> driver = Capabilities("AppName");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);	
		Formpage f = new Formpage(driver);
		
		//As we have made the Namefield private on Formpage, we cannot access outside the Formpage class.Now use the other method.
		//f.NameField.sendKeys("Himanshu");
		//This is another way of defining the method and calling.
		System.out.println("The Namefield: ");
		f.getNameField().sendKeys("Himanshu");
		
		//driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Himanshu");
		driver.hideKeyboard();
		System.out.println("The Gender: ");
		f.Gender.click();
		//driver.findElementByXPath("//*[@text = 'Female']").click();
		
		driver.findElementById("android:id/text1").click();
		Utilities u = new Utilities(driver);
		u.scrollTotext("Argentina");
	    //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
	    driver.findElementByXPath("//*[@text = 'Argentina']").click();  
		
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))");
		
		driver.findElementsByXPath("//*[@text = 'ADD TO CART']").get(0).click();
		driver.findElementsByXPath("//*[@text = 'ADD TO CART']").get(0).click();
		
		driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
		Thread.sleep(4000);
		
		CheckoutPage cp = new CheckoutPage(driver);
		int count = cp.ProductList.size();
		
		//int count = driver.findElementsById("com.androidsample.generalstore:id/productPrice").size();
		
		double sum = 0;
		
		for (int i=0;i<count;i++) {
			
			String amount1 = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(i).getText();
			double amount = getAmount(amount1);
			sum = sum+amount;	
			
		}
		
				
		System.out.println("Sum of products: " + sum);
		
		String total = cp.TotalAmount.getText();
		
	//	String total = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
		total = total.substring(1);
		double totalvalue = Double.parseDouble(total);
		System.out.println("Total value: "+ totalvalue);
		
		Assert.assertEquals(sum, totalvalue);
		
		WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));

		TouchAction t=new TouchAction(driver);

		t.tap(tapOptions().withElement(element(checkbox))).perform();
		
		//WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
	//	t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
		
		//driver.findElementById("android:id/button1").click();
		driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();
		service.stop();
		
		

	}
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	
	public static double getAmount(String value) {
		value = value.substring(1);
		double amount2value = Double.parseDouble(value);
		return amount2value;
	}

}






