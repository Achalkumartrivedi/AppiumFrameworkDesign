package appiumproject.testcases;

import appiumproject.testUtils.SuperBaseClass;
import appiumproject.pageOjects.FormPage;
import appiumproject.pageOjects.ProductCatalogue;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.NotFoundException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test03_GStoreByDataProvider extends SuperBaseClass {

	@BeforeMethod(alwaysRun = true)
	public void GoToHome() {

		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.SplashActivity");
		driver.startActivity(activity);
		System.out.println("******************* BeforeMethod Run: SplashActivity ****************");
	}
	
	@Test(groups = {"Smoke"},dataProvider = "getData")
	public void GStoreDataProviderTest03(String country,String name,String gender) {
		
		System.out.println("******************* Part 4:Data Driven Test by @DataProvider: General Store App  ****************");
		System.out.println("******************* GStoreDataProviderTest03() is start  ****************");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		FormPage frmPage = new FormPage(driver); //create object of formpage
		frmPage.countrySelection(country);  //country pass from argument
		frmPage.setNameField(name); //name pass from argument
		frmPage.setGender(gender); //gender pass from argument

		ProductCatalogue prdCatalogue = frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions

		System.out.println("*******************  GStoreDataProviderTest03() is finished ****************");

	}

	@DataProvider
	public Object[][] getData(){

		return new Object[][] { {"Bahamas","Achal","Female"},{"Algeria","Trivedi","Male"} };
	}

}
