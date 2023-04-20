package appiumproject.testcases;

import appiumproject.pageOjects.FormPage;
import appiumproject.pageOjects.ProductCatalogue;
import appiumproject.testUtils.SuperBaseClass;
import io.appium.java_client.android.Activity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test05_MulLevelInheritance extends SuperBaseClass {

	@BeforeMethod
	public void GoToHome() {
    try {
		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.SplashActivity");
		driver.startActivity(activity);
		System.out.println("******************* BeforeMethod Run: SplashActivity ****************");
	}
	catch (Exception e){
		System.out.println("******************* @BeforeMethod Error:- " +e.getMessage());
		e.printStackTrace();
	}
	}
	
	@Test(dataProvider = "getData")
	public void MultilevelTest05(String country,String name,String gender) {
		
		System.out.println("******************* Multilevel Inheritance Demo (Test05class -> BaseClass -> AppiumUtils) ****************");
		System.out.println("******************* MultilevelTest05() ****************");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		try {
			System.out.println("******************* Testcase start ****************");

			FormPage frmPage = new FormPage(driver); //create object of formpage 
			frmPage.countrySelection(country);  //country pass from argument
			frmPage.setNameField(name); //name pass from argument
			frmPage.setGender(gender); //gender pass from argument
			
			ProductCatalogue prdCatalogue = frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions

			System.out.println("*******************  MultilevelTest05() is finished ****************");
			
			
		}catch (AssertionError a) {
			// TODO: handle exception
			System.out.println("Assertion error message ...."+a.getMessage());
			a.printStackTrace();
			System.out.println("******************* MultilevelTest05() is not run ****************");
		}
		catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Error message ...."+ex.getMessage());
			ex.printStackTrace();
			System.out.println("******************* MultilevelTest05() is not run ****************");
		}
     
	}

	@DataProvider
	public Object[][] getData(){

		return new Object[][] { {"Bahamas","Achal","Female"},{"Australia","Trivedi","Male"} };
	}

}
