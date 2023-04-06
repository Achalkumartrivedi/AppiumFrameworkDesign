package appiumproject.testcases;

import appiumproject.testUtils.SuperBaseClass;
import appiumproject.pageOjects.FormPage;
import appiumproject.pageOjects.ProductCatalogue;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.NotFoundException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class Test04_GStoreByJson extends SuperBaseClass {

	@BeforeMethod
	public void GoToHome() {

		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.SplashActivity");
		driver.startActivity(activity);
		System.out.println("******************* BeforeMethod Run: SplashActivity ****************");
	}

	@Test(dataProvider = "getData")
	public void GstoreByJsonTest04(HashMap<String, String> input) {

		System.out.println("******************* Part 4:Data Driven Test by Json file: General Store App  ****************");
		System.out.println("******************* GstoreByJsonTest04() is start  ****************");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


		try {

			FormPage frmPage = new FormPage(driver); //create object of formpage 
			frmPage.countrySelection(input.get("country"));  //country pass from argument
			frmPage.setNameField(input.get("name")); //name pass from argument
			frmPage.setGender(input.get("gender")); //gender pass from argument

			ProductCatalogue prdCatalogue = frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions

			System.out.println("******************* GstoreByJsonTest04() is finished ****************");


		} catch (AssertionError a) {
			// TODO: handle exception
			System.out.println("Assertion error message ...." + a.getMessage());
			a.printStackTrace();
			System.out.println("******************* GstoreByJsonTest04() is not run ****************");
		} catch (NotFoundException e) {
			// TODO: handle exception
			System.out.println("Not found error message ...." + e.getMessage());
			e.printStackTrace();
			System.out.println("******************* GstoreByJsonTest04() is not run ****************");
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Error message ...." + ex.getMessage());
			ex.printStackTrace();
			System.out.println("******************* GstoreByJsonTest04() is not run ****************");
		}

	}

	//Data provide to converter
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + generalstorejsonPath);
		return new Object[][]{{data.get(0)}, {data.get(1)}}; //hashmap index in json file

		// { {Hash} {Hash} }  data
	}
}

 ///path of jsonfile - /src/test/java/appiumproject/utils/generalstore.json
