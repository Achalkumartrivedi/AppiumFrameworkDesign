package appiumproject.utils;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AppiumCommonUtils {
	
	     AppiumDriver driver; //This grandparent class so AppiumDriver is also parent driver
		
		public AppiumCommonUtils(AndroidDriver driver) {
			
				this.driver = driver; 
			
		}
		
		
		public Double getStringToDouble(String stringtext) {

			 Double convertedDouble = Double.parseDouble(stringtext.substring(1));
			 System.out.println("******************* AppiumCommonUtils: getStringToDouble() method call for String is into Double *******************");
			 return convertedDouble;
		}
		
		public void waitForElementByAttributeContains(WebElement element ,String attibute,String value) {
			
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			 wait.until(ExpectedConditions.attributeContains(element,attibute,value));
			System.out.println("******************* AppiumCommonUtils: Wait method is call *******************");
		}


}
