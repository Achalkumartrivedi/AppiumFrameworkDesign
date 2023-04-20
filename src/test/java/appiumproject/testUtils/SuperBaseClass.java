package appiumproject.testUtils;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import appiumproject.utils.AppiumCommonUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;

public class SuperBaseClass extends AppiumCommonUtils {
	
	public AndroidDriver driver ;
	
	@BeforeClass
	public void Setup() throws Exception {
		
        System.out.println("*********** SuperBaseclass: BeforeTest is Start ******************");

		prop = propertiesLoad();
		String ipaddress = prop.getProperty("ipaddress");
		String Basepath = prop.getProperty("basepath");
		String port = prop.getProperty("port");
		String debugloglevel = prop.getProperty("debugloglevel");


		//call method from AppiumCommonUtils - start server by servicebuilder and return service
		service = startAppiumServer(ipaddress,Basepath,port,debugloglevel); //call method in commonUtils

		System.out.println("********************** My Project directory:-" +projectdir);

		setAPKName("General-Store"); //ApiDemos-debug ,General-Store
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

