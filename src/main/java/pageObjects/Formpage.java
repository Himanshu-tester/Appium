package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Formpage {
	

	public Formpage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement NameField;
	
	@AndroidFindBy(xpath = "//*[@text = 'Female']")
	public WebElement Gender;
	
	//This is another way of defining the method and calling.
	public WebElement getNameField(){
		
		return NameField;
		
	}

}
