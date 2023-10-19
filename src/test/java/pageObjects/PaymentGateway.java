package pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentGateway {
	public WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions act;
	
	public PaymentGateway(WebDriver wd)
	{  driver=wd;
	   PageFactory.initElements(driver, this);
	   wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	   js = (JavascriptExecutor) driver;
	   act=new Actions(driver);
	}
	
	//Repository Of WebElements for the product page//
	@FindBy(linkText = "Proceed To Checkout")WebElement checkOutLink;
	@FindBy(xpath = "//u[normalize-space()='Register / Login']")WebElement regLogLink;
	@FindBy(xpath = "//div[@class='modal-content']")WebElement confirmAlert;
	@FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")WebElement contShop;
	@FindBy(xpath = "//u[normalize-space()='View Cart']")WebElement viewCartLink;
	@FindBy(linkText = "Cart")WebElement cartLink;
	
	//Repository Of WebElements for Products details In The Cart
	@FindBy(xpath = "(//td[@class='cart_description'])[1]")WebElement productName1;
	@FindBy(xpath = "(//td[@class='cart_price'])[1]")WebElement productPrice1;
	@FindBy(xpath = "(//td[@class='cart_quantity'])[1]")WebElement productQty1;
	@FindBy(xpath = "(//td[@class='cart_total'])[1]")WebElement carttotal1;
	
	@FindBy(xpath = "(//td[@class='cart_description'])[2]")WebElement productName2;
	@FindBy(xpath = "(//td[@class='cart_price'])[2]")WebElement productPrice2;
	@FindBy(xpath = "(//td[@class='cart_quantity'])[2]")WebElement productQty2;
	@FindBy(xpath = "(//td[@class='cart_total'])[2]")WebElement carttotal2;
	
	@FindBy(xpath = "(//td[@class='cart_description'])[3]")WebElement productName3;
	@FindBy(xpath = "(//td[@class='cart_price'])[3]")WebElement productPrice3;
	@FindBy(xpath = "(//td[@class='cart_quantity'])[3]")WebElement productQty3;
	@FindBy(xpath = "(//td[@class='cart_total'])[3]")WebElement carttotal3;
	
	//Repository Of WebElements for Delivery Address & Billing Address details In Review Your Order Page
	@FindBy(xpath = "//ul[@id='address_delivery']")WebElement deliveryAdd;
	@FindBy(xpath = "//ul[@class='address item box']/child::li[1]")WebElement deliveryAddressTitle;
	@FindBy(xpath = "//ul[@class='address item box']/child::li[2]")WebElement deliveryName;
	@FindBy(xpath = "//ul[@class='address item box']/child::li[3]")WebElement deliveryCompanyName;
	@FindBy(xpath = "//ul[@class='address item box']/child::li[4]")WebElement deliveryAddress1;
	@FindBy(xpath = "//ul[@class='address item box']/child::li[6]")WebElement deliveryCitystateZip;
	@FindBy(xpath = "//ul[@class='address item box']/child::li[7]")WebElement deliveryCountry;
	@FindBy(xpath = "//ul[@class='address item box']/child::li[8]")WebElement deliveryMobile;
	
	@FindBy(xpath = "//ul[@class='address alternate_item box']/child::li[1]")WebElement billingAddressTitle;
	@FindBy(xpath = "//ul[@class='address alternate_item box']/child::li[2]")WebElement billingName;
	@FindBy(xpath = "//ul[@class='address alternate_item box']/child::li[3]")WebElement billingCompanyName;
	@FindBy(xpath = "//ul[@class='address alternate_item box']/child::li[4]")WebElement billingAddress1;
	@FindBy(xpath = "//ul[@class='address alternate_item box']/child::li[6]")WebElement billingCitystateZip;
	@FindBy(xpath = "//ul[@class='address alternate_item box']/child::li[7]")WebElement billingCountry;
	@FindBy(xpath = "//ul[@class='address alternate_item box']/child::li[8]")WebElement billingMobile;
	
	@FindBy(xpath = "(//h2[@class='heading'])[2]")WebElement reviewOrderTitle;
	@FindBy(xpath = "(//h2[@class='heading'])[1]")WebElement addressTitle;
	@FindBy(xpath = "//textarea[@name='message']")WebElement reviewcomment;
	@FindBy(xpath = "//a[@class='btn btn-default check_out']")WebElement placeOrder;
	@FindBy(xpath = "//h2[@class='heading']")WebElement paymentTitle;
	@FindBy(xpath = "//input[@name='name_on_card']")WebElement nameOnCard;
	@FindBy(xpath = "//input[@name='card_number']")WebElement cardNumber;
	@FindBy(xpath = "//input[@name='cvc']")WebElement cvc;
	@FindBy(xpath = "//input[@name='expiry_month']")WebElement expiryMonth;
	@FindBy(xpath = "//input[@name='expiry_year']")WebElement expiryYear;
	@FindBy(xpath = "//button[@id='submit']")WebElement payConfirmBtn;
	@FindBy(xpath = "//p[text()='Congratulations! Your order has been confirmed!']")private WebElement ConfirmationMsg;
	@FindBy(xpath = "//h2[@data-qa='order-placed']/child::b")WebElement orderPlaced;
	@FindBy(xpath = "//a[@data-qa='continue-button']")WebElement continueBtn;
	@FindBy(xpath = "(//a[@class='cart_quantity_delete'])[1]")WebElement qtyDel1;
	@FindBy(xpath = "(//a[@class='cart_quantity_delete'])[1]")WebElement qtyDel2;
	@FindBy(xpath = "(//div[@class='productinfo text-center'])[1]")WebElement product1;
	@FindBy(xpath = "(//div[@class='productinfo text-center'])[2]")WebElement product2;
	@FindBy(xpath = "(//a[@class='btn btn-default add-to-cart'])[1]")WebElement addToCart1;
	@FindBy(xpath = "(//a[@class='btn btn-default add-to-cart'])[3]")WebElement addToCart2;
	@FindBy(xpath = "//table[@id='cart_info_table']/child::tbody[1]/child::tr")List<WebElement>tableRows;
	@FindBy(xpath = "//li[@class='active']")WebElement shoppingCart;
	@FindBy(xpath = "p[class='text-center'] b")WebElement emptyCart;
	@FindBy(xpath = "//a[normalize-space()='Download Invoice']")WebElement downloadInvoice;
	
	public void proceed_ToCheckout()
	{ wait.until(ExpectedConditions.visibilityOf(checkOutLink));
	  checkOutLink.click();
	}
	public void click_registerLoginBtn()
	{ wait.until(ExpectedConditions.visibilityOf(confirmAlert));
	  regLogLink.click();
	}
	public void click_CartLink()
	{wait.until(ExpectedConditions.visibilityOf(cartLink));
	 cartLink.click();
	}
	public void click_ProceedToCheckOut()
	{wait.until(ExpectedConditions.visibilityOf(checkOutLink));
	checkOutLink.click();
	}
	public String[] review_Order()
	{ String[] Array= {productName1.getText(),productQty1.getText(),productPrice1.getText(),carttotal1.getText(),
			        productName2.getText(), productQty2.getText(), productPrice2.getText(), carttotal2.getText(),
			        productName3.getText(), productQty3.getText(), productPrice3.getText(), carttotal3.getText()};
	 return Array;
	}
	 
	public void verify_Address()
	{wait.until(ExpectedConditions.visibilityOf(addressTitle));
	 Assert.assertNotEquals(deliveryAddressTitle.getText(), billingAddressTitle.getText());
	 Assert.assertEquals(deliveryName.getText(), billingName.getText());
	 Assert.assertEquals(deliveryCompanyName.getText(), billingCompanyName.getText());
	 Assert.assertEquals(deliveryAddress1.getText(), billingAddress1.getText());
	 Assert.assertEquals(deliveryCitystateZip.getText(), billingCitystateZip.getText());
	 Assert.assertEquals(deliveryCountry.getText(), billingCountry.getText());
	 Assert.assertEquals(deliveryMobile.getText(), billingMobile.getText());
	 System.out.println("*****Address Details is Verified Successfully!***** ");
	 
	}
	
	 public void payment_Method() throws IOException
	 {reviewcomment.sendKeys("Hare Krishna Hare Krishna Krishna Krishna Hare Hare! Hare Rama Hare Rama Rama Rama Hare Hare!!");
	  placeOrder.click();
	  wait.until(ExpectedConditions.visibilityOf(paymentTitle));
 	  String filePath="C:\\Users\\Smita\\OneDrive\\Desktop\\ExcelR Project-2.xlsx";
      FileInputStream fis=new FileInputStream(filePath);
	  XSSFWorkbook workBook=new XSSFWorkbook(fis);
	  XSSFSheet sheet=workBook.getSheet("Payment");
	  nameOnCard.sendKeys(sheet.getRow(1).getCell(0).toString());
	  cardNumber.sendKeys(sheet.getRow(1).getCell(1).getRawValue());
	  cvc.sendKeys(sheet.getRow(1).getCell(2).getRawValue());
	  expiryMonth.sendKeys(sheet.getRow(1).getCell(3).getRawValue());
	  expiryYear.sendKeys(sheet.getRow(1).getCell(4).getRawValue());
	  payConfirmBtn.click();
	 
	  wait.until(ExpectedConditions.visibilityOf(ConfirmationMsg));
	  Assert.assertTrue(ConfirmationMsg.getText().equals("Congratulations! Your order has been confirmed!"));
	  System.out.println("Congratulations! Your order has been confirmed!!!!!!!!!!");
	}
	public void click_ContinueButton()
	{wait.until(ExpectedConditions.visibilityOf(continueBtn));
	 continueBtn.click();	
	}
	
	public void download_Invoice() throws InterruptedException
	{ wait.until(ExpectedConditions.visibilityOf(downloadInvoice));
	  //Download Order Invoice
	  downloadInvoice.click();
	  driver.navigate().refresh();
	  //verify invoice is downloaded successfully. 
	  String downloadPath = "C:\\Users\\smita\\Downloads";
      File dir = new File(downloadPath);
      File[] dirContents = dir.listFiles();
      for (int i = 0; i < 3; i++) 
       { if(dirContents[i].getName().equalsIgnoreCase("invoice")) 
          { System.out.println("verified!! invoice is downloaded successfully.");
        	break;
          }
       }
	 }
	
	public void remove_Products()
	{ //Add 2 products To The Cart
	  act.moveToElement(product1).perform();;
	  js.executeScript("arguments[0].click()",addToCart1);
	  wait.until(ExpectedConditions.visibilityOf(confirmAlert));
	  contShop.click();
	  act.moveToElement(product2).perform();;
	  js.executeScript("arguments[0].click()",addToCart2);
	  wait.until(ExpectedConditions.visibilityOf(confirmAlert));
	  contShop.click();
	  wait.until(ExpectedConditions.visibilityOf(cartLink));
	  cartLink.click();
	  System.out.println("Products are added to the Cart successfully!!");
	  wait.until(ExpectedConditions.visibilityOf(shoppingCart));
	  int beforeQty=tableRows.size();
	  System.out.println("No. Of Products displayed in the Cart: "+beforeQty);
	  //Remove one product from the cart
	  js.executeScript("arguments[0].click()",qtyDel1);
	  driver.navigate().refresh();
	  wait.until(ExpectedConditions.visibilityOf(shoppingCart));
	  int afterQty=tableRows.size();
	  System.out.println("No. Of Products displayed in the Cart: "+afterQty);
	  //Verify that perticular Product is removed from Cart Successfully
	  Assert.assertNotEquals(beforeQty, afterQty);
	  System.out.println("Verified that perticular Product is removed from Cart Successfully!!");
	}
	
	public void verify_DeliveryAddress() throws IOException
	{ wait.until(ExpectedConditions.visibilityOf(addressTitle));
	  // opening Excel file
	  String filePath="C:\\Users\\Smita\\OneDrive\\Desktop\\ExcelR Project-2.xlsx";
      FileInputStream fis=new FileInputStream(filePath);
	  XSSFWorkbook workBook=new XSSFWorkbook(fis);
	  XSSFSheet sheet=workBook.getSheet("AccountInfo");
	  // Inetialing the variable with excel file data
	  XSSFCell firstName=sheet.getRow(1).getCell(6);
	  XSSFCell lastName=sheet.getRow(1).getCell(7);
	  XSSFCell company=sheet.getRow(1).getCell(8);
	  XSSFCell street=sheet.getRow(1).getCell(9);
	  XSSFCell countr=sheet.getRow(1).getCell(10);
	  XSSFCell state=sheet.getRow(1).getCell(11);
	  XSSFCell city=sheet.getRow(1).getCell(12);
	  XSSFCell zipCode=sheet.getRow(1).getCell(13);
	  XSSFCell mobileNum=sheet.getRow(1).getCell(14);
      //Concatinating Full name & city,state,&zipcode
	  String delAddrName=("Mrs. "+firstName+" "+lastName);
	  String delAddrCity=(city+" "+state+" "+zipCode.getRawValue());
	  //Verify delivery Address details with data given while Registration which has done by datadriven
	  Assert.assertEquals(delAddrName, deliveryName.getText());
	  Assert.assertEquals(company.toString(), deliveryCompanyName.getText());
	  Assert.assertEquals(street.toString(), deliveryAddress1.getText());
	  Assert.assertEquals(delAddrCity, deliveryCitystateZip.getText());
	  Assert.assertEquals(countr.toString(), deliveryCountry.getText());
	  Assert.assertEquals(mobileNum.getRawValue(), deliveryMobile.getText());
	  System.out.println("Verified!! the Delivery address is same address filled at the time registration of account---------");
	 
	  //Verify Billing Address details with data given while Registration which has done by datadriven
	  Assert.assertEquals(delAddrName, billingName.getText());
	  Assert.assertEquals(company.toString(), billingCompanyName.getText());
	  Assert.assertEquals(street.toString(), billingAddress1.getText());
	  Assert.assertEquals(delAddrCity, billingCitystateZip.getText());
	  Assert.assertEquals(countr.toString(), billingCountry.getText());
	  Assert.assertEquals(mobileNum.getRawValue(), billingMobile.getText());
	  System.out.println("Verified!! the Billing address is same address filled at the time registration of account---------");
	} 
	
}
