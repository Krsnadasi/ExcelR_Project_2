package pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPageOfAutomation {
	public WebDriver driver;
	WebDriverWait wait;
	Actions act;
	JavascriptExecutor js;
	public ProductsPageOfAutomation(WebDriver wd)
	{	driver=wd;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		js = (JavascriptExecutor) driver;
		act=new Actions(driver);
	}
	
	//Repository Of WebElements for the product page//
	// Repository Of WebElements for Test Case-8 "view Product Details"//
	@FindBy(xpath = "//a[@href='/products']")WebElement productLink;
	@FindBy(xpath = "//h2[normalize-space()='All Products']")WebElement allProducts;
	@FindBy(xpath = "(//ul[@class='nav nav-pills nav-justified'])[1]")WebElement viewFirstProd;
	@FindBy(xpath = "//div[@class='product-information']")WebElement prodDetails;
		
	// Repository Of WebElements for Test Case-9 "Search Product"//
	@FindBy(xpath = "//input[@id='search_product']")WebElement searchBox;
	@FindBy(xpath = "//button[@id='submit_search']")WebElement searchBtn;
	@FindBy(xpath = "//h2[@class='title text-center']")WebElement searchedCategoryTitle;
	@FindBy(xpath = "//div[@class='productinfo text-center']/child::p")List<WebElement> searchProdList;
	// Repository Of WebElements for Test Case-10 & 11 "Subscription In Home Page" & "Cart Page" //
	@FindBy(xpath = "//input[@id='susbscribe_email']")WebElement subscribeField;
	@FindBy(xpath = "//button[@id='subscribe']")WebElement subscribeBtn;
	@FindBy(xpath = "//div[@class='single-widget']/child::h2")WebElement subscribeTitle;
	@FindBy(xpath = "//div[@class='alert-success alert']")WebElement successMsg;
	@FindBy(linkText = "Cart")WebElement cartLink;
	@FindBy(xpath = "//li[@class='active']")WebElement cartTitle;
	// Repository Of WebElements for Test Case-12 "Product in cart"//
	@FindBy(xpath = "(//div[@class='productinfo text-center'])[1]")WebElement product1;
	@FindBy(xpath = "(//a[@class='btn btn-default add-to-cart'])[1]")WebElement addToCart1;
	@FindBy(xpath = "(//div[@class='productinfo text-center'])[2]")WebElement product2;
	@FindBy(xpath = "(//a[@class='btn btn-default add-to-cart'])[3]")WebElement addToCart2;
	@FindBy(xpath = "(//div[@class='productinfo text-center'])[3]")WebElement product3;
	@FindBy(xpath = "(//a[@class='btn btn-default add-to-cart'])[5]")WebElement addToCart3;
	@FindBy(xpath = "//div[@class='modal-content']")WebElement confirmAlert;
	@FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")WebElement contShop;
	@FindBy(xpath = "//u[normalize-space()='View Cart']")WebElement viewCartLink;
	@FindBy(xpath = "//table[@id='cart_info_table']/child::tbody[1]/child::tr")List<WebElement>tableRows;
	// Repository Of WebElements for Test Case-13 "Product quantity In Cart"//
	@FindBy(xpath = "//input[@id='quantity']")WebElement quantity;
	@FindBy(xpath = "//button[normalize-space()='Add to cart']")WebElement cartBtn;
	@FindBy(xpath = "//table[@id='cart_info_table']/child::tbody[1]/child::tr[1]/child::td[4]")WebElement dataQty;
	// Repository Of WebElements for Test Case-21 "Write Product Review"//
	@FindBy(xpath = "//input[@id='name']")WebElement reviewName;
	@FindBy(xpath = "//input[@id='email']")WebElement reviewEmail;
	@FindBy(xpath = "//textarea[@id='review']")WebElement reviewMsg;
	@FindBy(xpath = "//textarea[@id='review']")WebElement reviewSubmitBtn;
	@FindBy(xpath = "//div[@id='review-section']//child::div[1]//child::div")WebElement reviewConfirmAlert;
	
	
	public void click_ProductLink() throws InterruptedException
	{ wait.until(ExpectedConditions.visibilityOf(productLink));
	  productLink.click();
	  Thread.sleep(3000);
	}
	
	public void prod_details() throws InterruptedException
	{ // Verifying User is on All Products Page
	  wait.until(ExpectedConditions.visibilityOf(allProducts));
	  Assert.assertTrue(allProducts.getText().equals("ALL PRODUCTS"));
	  viewFirstProd.click();
	  // Verifying Products Details Page
	  wait.until(ExpectedConditions.visibilityOf(prodDetails));
	  Assert.assertTrue(prodDetails.isDisplayed());
	  System.out.println("Verified! product Details are Visible.");
	}
	
	public void search_Product() throws InterruptedException
	{ //Verifying User is on All Products Page
	  wait.until(ExpectedConditions.visibilityOf(allProducts));
	  Assert.assertTrue(allProducts.getText().equals("ALL PRODUCTS"));
	  searchBox.sendKeys("Saree");
	  searchBtn.click();
	  wait.until(ExpectedConditions.visibilityOf(searchedCategoryTitle));
	  Assert.assertTrue(searchedCategoryTitle.getText().equals("SEARCHED PRODUCTS"));
      //To Get name of Searched products names
	  System.out.println("Total No. Of Items In Searched Products: "+searchProdList.size());
	  //To Printing all the searched Products Names
	  for(WebElement menu:searchProdList)
		{String MenuText=menu.getText();
		 System.out.println(MenuText);
		}
	}
	
	public void subScribe_inHomePG()
	{ wait.until(ExpectedConditions.visibilityOf(productLink));
	  act.scrollToElement(subscribeTitle).build().perform();
	  //Verifying 'SUBSCRIPTION' is visible
	  Assert.assertTrue(subscribeTitle.getText().equals("SUBSCRIPTION"));
	  wait.until(ExpectedConditions.visibilityOf(subscribeField));
	  subscribeField.sendKeys("radharaman@gmail.com");
	  subscribeBtn.click();
	  wait.until(ExpectedConditions.visibilityOf(successMsg));
	  //Verifying Success Message Alert
	  Assert.assertEquals(successMsg.getText(), "You have been successfully subscribed!");
	  System.out.println("You have been successfully subscribed in Home Page!!!!!");
	}
	
	public void subScribe_inCartPage()
	{ wait.until(ExpectedConditions.visibilityOf(cartLink));
	  cartLink.click();
	  wait.until(ExpectedConditions.visibilityOf(cartTitle));
	  Actions act=new Actions(driver);
	  act.scrollToElement(subscribeTitle).build().perform();
	  //Verifying 'SUBSCRIPTION' is visible
	  Assert.assertTrue(subscribeTitle.getText().equals("SUBSCRIPTION"));
	  wait.until(ExpectedConditions.visibilityOf(subscribeField));
	  subscribeField.sendKeys("radharaman@gmail.com");
	  subscribeBtn.click();
 	  wait.until(ExpectedConditions.visibilityOf(successMsg));
 	  //Verifying Success Message Alert
 	  Assert.assertEquals(successMsg.getText(), "You have been successfully subscribed!");
	  System.out.println("You have been successfully subscribed!");
	}
	
	public void addProducts() throws InterruptedException
	{ Thread.sleep(2000);
	  //Hover over products and click 'Add to cart', 'Continue shopping',& 'View Cart'
      act.moveToElement(product1).perform();
      js.executeScript("arguments[0].click()",addToCart1);
	  wait.until(ExpectedConditions.visibilityOf(confirmAlert));
	  contShop.click();
	  wait.until(ExpectedConditions.visibilityOf(product2));
	  act.moveToElement(product2).perform();
	  js.executeScript("arguments[0].click()",addToCart2);
	  wait.until(ExpectedConditions.visibilityOf(confirmAlert));
	  contShop.click();
	  wait.until(ExpectedConditions.visibilityOf(product3));
	  act.moveToElement(product3).perform();
      js.executeScript("arguments[0].click()",addToCart3);
	  wait.until(ExpectedConditions.visibilityOf(confirmAlert));
	  viewCartLink.click();
	  System.out.println("No. Of Products Added in the Cart Are: "+tableRows.size());
	}
	
	public void quantityProd() throws InterruptedException
	{ //first Clear The Quantity Of the Product & Then Increase It To '4' & AddToCart
	  quantity.clear();
	  quantity.sendKeys("4");
	  cartBtn.click();
	  wait.until(ExpectedConditions.visibilityOf(confirmAlert));
	  viewCartLink.click();
	  //Verify that product is displayed in cart page with exact quantity 
	  Assert.assertEquals(dataQty.getText(),"4");
	  System.out.println("Product displayed in cart page with quantity is: "+dataQty.getText());
	}
	
	public void writeReview() throws IOException
	{ wait.until(ExpectedConditions.visibilityOf(reviewName));
	  //Write review for The Product By Datadriven By Excel File
	  String filePath="C:\\Users\\Smita\\OneDrive\\Desktop\\ExcelR Project-2.xlsx";
	  FileInputStream fis=new FileInputStream(filePath);
	  XSSFWorkbook workBook=new XSSFWorkbook(fis);
	  XSSFSheet sheet=workBook.getSheet("ProductReview");
	  reviewName.sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
	  reviewEmail.sendKeys(sheet.getRow(1).getCell(1).getStringCellValue());
	  reviewMsg.sendKeys(sheet.getRow(1).getCell(2).getStringCellValue());
	  reviewSubmitBtn.click();
      System.out.println("Thank you for the Review!!!!!");
	}
	

}
