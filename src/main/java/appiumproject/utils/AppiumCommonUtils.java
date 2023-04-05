package appiumproject.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public abstract class AppiumCommonUtils {
	
	    public AppiumDriver driver; //This grandparent class so AppiumDriver is also parent driver

		public Double getStringToDouble(String stringtext) {

			 Double convertedDouble = Double.parseDouble(stringtext.substring(1));
			 System.out.println("******************* AppiumCommonUtils: getStringToDouble() method call for String is into Double *******************");
			 return convertedDouble;
		}
		
		public void waitForElementByAttributeContains(WebElement element,String attibute,String value) {
			
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			 wait.until(ExpectedConditions.attributeContains(element,attibute,value));
			System.out.println("******************* AppiumCommonUtils: Wait method is call *******************");
		}

		public void waitByVisibilityOf(WebElement element){
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(element));
			System.out.println("******************* AppiumCommonUtils: Wait method is call *******************");

		}

	//converter = data convert json file into hashmap one by one
	public List<HashMap<String ,String>> getJsonData(String jsonFilePath) throws IOException {
		System.out.println("******************* getJsonData is start ****************");

		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+ "/src/test/java/appiumproject/testUtils/generalstore.json"), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String ,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String ,String>>>(){});

		System.out.println("******************* getJson return data ****************");
		return data;

	}

}

