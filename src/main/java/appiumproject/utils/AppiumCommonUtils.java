package appiumproject.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.exception.DeviceNotFoundException;
import com.testinium.deviceinformation.model.Device;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public abstract class AppiumCommonUtils {

	public AppiumDriverLocalService service ;
	public AppiumServiceBuilder builder = new AppiumServiceBuilder();
	public DeviceInfo deviceInfo ;
	public Device device ;

	public String projectdir = System.getProperty("user.dir");
	public String generalstorejsonPath = "/src/test/java/appiumproject/TestData/generalstore.json";

	public String dataPropertiesPath = "/src/main/java/appiumproject/resources/data.properties";

	//E-COMMERCE APP
	public File app = new File( projectdir +"/src/test/resources/apps/General-Store.apk");

	//API DEMOS APP
	//public File app= new File(projectdir + "/src/test/resources/apps/ApiDemos-debug.apk");


	public DesiredCapabilities cap = new DesiredCapabilities();
	
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

		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+ generalstorejsonPath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String ,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String ,String>>>(){});

		System.out.println("******************* getJson return data ****************");
		return data;

	}

	public AppiumDriverLocalService startAppiumServer(String ipAddress,String basepath, String port,String logLevel) {

		try {
			System.out.println("******************* AppiumCommonUtils: startAppiumServer() ****************");
			//Build the Appium service
			builder.withIPAddress(ipAddress);
			builder.withArgument(() -> "--port", port);
			builder.withArgument(GeneralServerFlag.BASEPATH, basepath);
			builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
			builder.withArgument(GeneralServerFlag.LOG_LEVEL, logLevel);
			builder.build();
			//Start the Appium server with the builder
			service = AppiumDriverLocalService.buildService(builder);
			service.start();
			System.out.println("********************** Appium Server Started via Java **************************");



		} catch (Exception e) {
			System.out.println("********************** Appium server not start -Error is:- " + e.getMessage());
			e.printStackTrace();
		}
		//service is for used to pass driver with 'cap' in baseclass
		return service;
	}

	public void getDeviceCapabilities(){

		try {
			System.out.println("******************* AppiumCommonUtils: getDeviceCapability() ****************");

			deviceInfo = new DeviceInfoImpl(DeviceType.ANDROID);
			deviceInfo.anyDeviceConnected();
			device = deviceInfo.getFirstDevice();

			System.out.println("Platform Name - " + device.getDeviceProductName());
			System.out.println("Device UDID   - " + device.getUniqueDeviceID());
			System.out.println("Product Verison - " + device.getProductVersion());
			System.out.println("Model Number   - " + device.getModelNumber());
			System.out.println("Buld Verison   - " + device.getBuildVersion());



			cap.setCapability(MobileCapabilityType.PLATFORM_NAME,device.getDeviceProductName());
			System.out.println("******************  get Platform name ****************");
			cap.setCapability(MobileCapabilityType.UDID,device.getUniqueDeviceID());
			System.out.println("******************  get UDID ****************");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,device.getProductVersion());
			System.out.println("******************  get Platform Verison ****************");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME,device.getModelNumber());
			System.out.println("******************  get device name (Model Number) ****************");

			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,60);

			cap.setCapability("automationName", "UiAutomator2");
			cap.setCapability(MobileCapabilityType.APP, app.getPath());

			//cap.setCapability("autoGrantPermissions", true);
			//  cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

			System.out.println("****************** AppiumCommonUtils: capabilities are successfully taken ****************");

		}
	    catch (SecurityException | IOException | DeviceNotFoundException e) {

		System.out.println("getcapability error message is:- "+e.getMessage());
		e.printStackTrace();
		System.out.println("******************  Device is not connected or please check your device ****************");

		}
		}
}

//127.0.0.1 is the  localhost normally resolves to the IPv4  127.0.0.1
//builder.usingPort(4723); //Appium default port
//builder.withCapabilities(cap);