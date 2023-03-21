package appiumproject.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumCommonUtils{
	AndroidDriver driver ;
	
	public AndroidActions(AndroidDriver driver) {
		   
		   super(driver);
			this.driver = driver;
		
	}
	
	public void scrollToCountry(String text) {
		
		 driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))")).click();
		  System.out.println("****** AndroidActions: List is scrolled Australia is selected from list ********");
	
	}
  
//	public Double getStringToDouble(String stringtext) {
//
//		 Double convertedDouble = Double.parseDouble(stringtext.substring(1));
//		 System.out.println("****** String is converted into Double ,take substring after first char($ sign removed) ********");
//		 return convertedDouble;
//	}
	
	public void longPressAction(WebElement element) throws InterruptedException {
		
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",ImmutableMap.of("elementId",((WebElement) element),"duration",2000));
		 System.out.println("******************* AndroidActions: longpress run ****************");
		 Thread.sleep(3000);
	}
	
}
