package appiumproject.testcases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appiumproject.testUtils.SuperBaseClass;
import appiumproject.pageOjects.FormPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class Test02_MultipleTests extends SuperBaseClass {

	//******************* Part- 3 :Test Strategy and TestNGSmoke.xml ****************
	@BeforeMethod(alwaysRun = true)
	public void GoToHome() throws InterruptedException {

		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.SplashActivity");
		driver.startActivity(activity);
		Thread.sleep(3000);
		System.out.println("******************* BeforeMethod Run: SplashActivity ****************");
	}

	@Test(priority = 0 ,groups = { "Regression" })
	public void VerifyNameValidation_Test01() {

		System.out.println("******************* First Test is start ****************");
		System.out.println("******************* VerifyNameValidation_Test01() is start  ****************");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		FormPage frmPage = new FormPage(driver); //create object of formpage
		frmPage.countrySelection("Australia");  //country select from dropdown
		frmPage.setGender("Female"); //select Female gender
		frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions


		String toastMessage = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")).getAttribute("text");
		Assert.assertEquals(toastMessage, "Please enter your name0000000");
		System.out.println("************** Assertion:Passed 'Please enter your name' toast is displayed *********************");


		System.out.println("*******************  VerifyNameValidation_Test01() is finished ****************");


	}


	@Test( priority = 1,groups = { "Smoke" })
	public void FormSubmit_Test02() {

		System.out.println("******************* Second Test is start ****************");
		System.out.println("******************* FormSubmit_Test02() is start  ****************");

		FormPage frmPage = new FormPage(driver); //create object of formpage
		frmPage.countrySelection("Australia");  //country select from dropdown
		frmPage.setNameField("Achal");
		frmPage.setGender("Female"); //select Female gender
		frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions
		System.out.println("******************* FormSubmit_Test02() is finished ****************");

	}
}
