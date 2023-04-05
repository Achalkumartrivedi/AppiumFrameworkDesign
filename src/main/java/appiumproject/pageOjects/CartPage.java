package appiumproject.pageOjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.google.common.collect.ImmutableMap;

import appiumproject.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class CartPage extends AndroidActions{
	public CartPage(AndroidDriver driver) {
		    
		    super(driver);
			this.driver = driver;
			PageFactory.initElements(driver,this);
		
	}
	//All Locators
	
	 //List<WebElement> productprice = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	@FindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productprice ;
	
	//driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	@FindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalpurchaseamount;
	
	//driver.findElement(By.className("android.widget.CheckBox")).click();
	@FindBy(className =  "android.widget.CheckBox")
	private WebElement checkmarkedElement ;
	
	//driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Please read our terms of conditions']"));
	@FindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms ;
	
	//driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='CLOSE']"));
	@FindBy(id = "android:id/button1")
	private WebElement close ;
	
	//driver.findElement(By.xpath("//android.widget.Button[@text='Visit to the website to complete purchase']")).click();
	@FindBy(xpath = "//android.widget.Button[@text='Visit to the website to complete purchase']")
	private WebElement submitPurchasButton  ;
	
	//WebElement cartTitlElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Cart']"));
	@FindBy(xpath  = "//android.widget.TextView[@text='Cart']")
	public WebElement cartPageTitle ;
	
	
	
	//All Action Methods
	
	public double getProductSum() {
		     
		   
		    double sumOftwoProduct = 0;
		    for (WebElement singleproduct : productprice) {
		    	          
		    	          //step by step converting string to double
		    	          String singleprice = singleproduct.getText(); //convert web element into string
		    	          Double dpriceDouble = getStringToDouble(singleprice);  //formatted string by function in MobileAction
		    	         // Double dpriceDouble = Double.parseDouble(singleprice.substring(1)); //convert string into double
		    	          sumOftwoProduct = sumOftwoProduct + dpriceDouble;  //sum of products in available cart
		    	          System.out.println(singleprice+"---> After every iteration,total price is:- "+sumOftwoProduct);		
			}
		    return sumOftwoProduct;
	}
	
	public Double getTotalPurchaseAmountDisplayed() {
		     //One line - convert string to double by method and return double as well (beacuse method return double)
		 System.out.println("******************* String is converted into Double ,take substring after first char($ sign removed) *******************");
		    return getStringToDouble(totalpurchaseamount.getText());
		    
	}
	
	public void clickOnCheckmarked() {
		 
		checkmarkedElement.click();
		 System.out.println("******************* click on checkmarked ****************");
	}
	
	
	public void clickOnTermsAnsCondition() throws InterruptedException {
		
		
		longPressAction(terms);
        close.click();
      System.out.println("******************* Closed Terms and condition popup ****************");
	}
	
	public void clickOnVisitPurchaseButton() {
		
		submitPurchasButton.click();
		System.out.println("******************* click on 'visit the website to purchase' button ****************");
	}
	
	public void cartPageTitleWait() {
		//waitByVisibilityOf(cartPageTitle);
		waitForElementByAttributeContains(cartPageTitle,"text","Cart");
		System.out.println("******************* Wait for 'Cart' page load ****************");
		
	}
	
}

//String totalString = totalpurchaseamount.getText();
//Double totalPurchaseAmountDouble = Double.parseDouble(totalString.substring(1));
//System.out.println("******************* Total Purchase Amount is displayed:- " +totalPurchaseAmountDouble);
//return totalPurchaseAmountDouble;