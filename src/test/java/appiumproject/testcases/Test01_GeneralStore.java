package appiumproject.testcases;

import java.time.Duration;

import io.appium.java_client.android.Activity;
import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appiumproject.testUtils.SuperBaseClass;
import appiumproject.pageOjects.CartPage;
import appiumproject.pageOjects.FormPage;
import appiumproject.pageOjects.ProductCatalogue;

public class Test01_GeneralStore extends SuperBaseClass {

	@BeforeMethod(alwaysRun = true)
	public void GoToHome() {

		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.SplashActivity");
		driver.startActivity(activity);
		System.out.println("******************* BeforeMethod Run: SplashActivity ****************");
	}
	
	@Test(groups = {"regression"})
	public void GenralStoreTest01() {
		
		System.out.println("******************* Part 1-2: General Store App Form submit by Page Factory Design Pattern ****************");
		System.out.println("******************* Test01_GeneralStore() is start  ****************");
		   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		try {
			
			FormPage frmPage = new FormPage(driver); //create object of formpage 
			frmPage.countrySelection("Australia");  //country select from dropdown
			frmPage.setNameField("Achal"); //Enter name in field
			frmPage.setGender("Female"); //select Female gender
			
			ProductCatalogue prdCatalogue = frmPage.letsShopButtonclick(); //Implement Page object file for Product Catalogue page with actions
			
			//ProductCatalogue prdCatalogue = new ProductCatalogue(driver); //create object seperatly of product catologue page
			prdCatalogue.clickOnAddToCartByIndex(0); //click on first product
			prdCatalogue.clickOnAddToCartByIndex(0); //click on second product
			
			CartPage cartPage = prdCatalogue.clickOnCartIcon(); //Implement Page object file for cart page with actions
			// CartPage cartPage = new CartPage(driver); //create object seperatly of cart page page
			
			
			cartPage.cartPageTitleWait(); //Cart page Title Wait by action method getting error (Input method set)

//			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
//			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.TextView[@text='Cart']"))));
//			System.out.println("******************* Wait for 'Cart' page load ****************");


			double ProductSum = cartPage.getProductSum(); //two product sum and store into double
			double ProductPurchaseAmount = cartPage.getTotalPurchaseAmountDisplayed(); //get purchase amount and store in double
			 
			 Assert.assertEquals(ProductSum,ProductPurchaseAmount);
			 System.out.println("******************* Assertion:Passed ****************");
			 
			 
			 cartPage.clickOnCheckmarked();
			 Thread.sleep(3000);
			 cartPage.clickOnTermsAnsCondition();
			 cartPage.clickOnVisitPurchaseButton();
			 
    
			 

			   System.out.println("*******************  Test01_GeneralStore() is finished ****************");
			
			
		}catch (AssertionError a) {
			// TODO: handle exception
			System.out.println("Assertion error message ...."+a.getMessage());
			a.printStackTrace();
			System.out.println("*******************  Test01_GeneralStore() is not run ****************");
		} 
		catch (NotFoundException e) {
			// TODO: handle exception
			System.out.println("Not found error message ...."+e.getMessage());
			e.printStackTrace();
			System.out.println("*******************  Test01_GeneralStore() is not run ****************");
		}
		catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Error message ...."+ex.getMessage());
			ex.printStackTrace();
			System.out.println("*******************  Test01_GeneralStore() is not run ****************");
		}
     
	}

}
