package appiumproject.basePackage;

import java.io.File;
import java.time.Duration;

import appiumproject.utils.AppiumCommonUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.model.Device;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class SuperBaseClass extends AppiumCommonUtils {
	
	public AndroidDriver driver ;
	
	@BeforeClass
	public void Setup() throws Exception {
		
        System.out.println("*********** SuperBaseclass: BeforeTest is Start ******************");

		//call method from AppiumCommonUtils - start server by servicebuilder and return service
		service = startAppiumServer("127.0.0.1","/wd/hub","4723","debug"); //call method in commonUtils

		System.out.println("********************** My Project directory:-" +projectdir);

		//call method from AppiumCommonUtils - Automatically get capabilities
		getDeviceCapabilities();
     			
		//URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver(service,cap); //returned service is used here to pass the driver with 'cap'

		//implicitwait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		System.out.println("******************  SuperBaseClass setup is successfully run ****************");

	}

	
	@AfterClass 
	public void teardown() {
	
		System.out.println("******* Teardown is start ********");
		service.close();
		System.out.println("******* Appium Server Closed *******");
		 
	
	}
	
//	@Test
//	public void test() {
//
//		System.out.println("SuperBaseclass Test is run....");
//
//	}
//
	
}

