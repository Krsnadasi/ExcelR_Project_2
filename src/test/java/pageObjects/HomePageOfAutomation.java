package pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageOfAutomation {
	public WebDriver driver;
	WebDriverWait wait,watch;
	Actions act;
	JavascriptExecutor js;
	
	public HomePageOfAutomation(WebDriver wd)
	{ driver =wd;
	  PageFactory.initElements(driver, this);
	  wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	  act=new Actions(driver);
	  js = (JavascriptExecutor) driver;
	}
	
	//Repository Of Home Page/ Contact Us Form WebElements//
	@FindBy(xpath="//ul[@class='nav navbar-nav']/child::li/a")List<WebElement> autoExcercise;
	@FindBy(linkText = "Contact us")WebElement contactUsLink;
	@FindBy(xpath = "//div[@class='contact-form']")WebElement contactForm;
	@FindBy(xpath = "//input[@name='name']")WebElement contName;
	@FindBy(xpath = "//input[@name='email']")WebElement contEmail;
	@FindBy(xpath = "//input[@name='subject']")WebElement contSub;
	@FindBy(xpath = "//textarea[@id='message']")WebElement contMsg;
	@FindBy(xpath = "//input[@name='upload_file']")WebElement contUpload;
	@FindBy(xpath = "//input[@name='submit']")WebElement contSubmit;
	@FindBy(xpath = "//div[@class='status alert alert-success']")WebElement successMsg;
	@FindBy(linkText = "Home")WebElement homeBtn;
	//Repository Of Home Page/ TestCases Page WebElements//
	@FindBy(linkText = "Test Cases")WebElement testCasesLink;
	@FindBy(xpath = "//div[@class='container']/descendant::b")WebElement testCases;
	//Repository Of Home Page/ Recommended Items (TestCases 22) WebElements//
	@FindBy(xpath = "//div[@class='recommended_items']")WebElement recommndedItems;
	@FindBy(xpath = "//h2[text()='recommended items']//following::a[text()='Add to cart'][3]")WebElement recDressCartBtn1;
	@FindBy(xpath = "//u[normalize-space()='View Cart']")WebElement viewCartLink;
	@FindBy(xpath = "//div[@class='modal-content']")WebElement confirmAlert;
	@FindBy(xpath = "//table[@id='cart_info_table']/child::tbody[1]/child::tr")List<WebElement>tableRows;
	@FindBy(xpath = "//li[@class='active']")WebElement shoppingCart;
	@FindBy(xpath = "//table[@id='cart_info_table']/child::tbody[1]/child::tr[1]/child::td[2]")WebElement prodDescription;
    //Repository of Scroll Up & Scroll Down (TestCases25-26)//
	@FindBy(xpath = "//div[@class='single-widget']/child::h2")WebElement subscribeTitle;
	@FindBy(xpath = "//i[@class='fa fa-angle-up']")WebElement scrollUpArrowBtn;
	@FindBy(xpath = "(//h2[text()='Full-Fledged practice website for Automation Engineers'])[1]")WebElement dynamicBanner;
	
	public void contactUs_Form() throws IOException
	{wait.until(ExpectedConditions.visibilityOf(contactUsLink));
	 // verifying ContactUs element is visible
     Assert.assertTrue(contactUsLink.isDisplayed());  
	 contactUsLink.click();
	 // verifying Get In Touch Form element is visible
	 wait.until(ExpectedConditions.visibilityOf(contactForm)); 
	 Assert.assertTrue(contactForm.isDisplayed());
		
	 String filePath="C:\\Users\\Smita\\OneDrive\\Desktop\\ExcelR Project-2.xlsx";
	 FileInputStream fis=new FileInputStream(filePath);
	 XSSFWorkbook workBook=new XSSFWorkbook(fis);
	 XSSFSheet sheet=workBook.getSheet("ContactUs");
	 contName.sendKeys(sheet.getRow(1).getCell(0).toString());
	 contEmail.sendKeys(sheet.getRow(1).getCell(1).toString());
	 contSub.sendKeys(sheet.getRow(1).getCell(2).toString());
	 contMsg.sendKeys(sheet.getRow(1).getCell(3).toString());
	 contUpload.sendKeys("C:\\Users\\Smita\\OneDrive\\Desktop\\image.jpg");
	 contSubmit.click();
	 Alert alt=driver.switchTo().alert();
	 alt.accept();
	 // verifying Success! Your details have been submitted successfully.' element is visible
	 wait.until(ExpectedConditions.visibilityOf(successMsg));
	 Assert.assertTrue(successMsg.isDisplayed());  
	 System.out.println("Success! Your details have been submitted successfully!");
     homeBtn.click();
	}
	
	public void testCases_Page() throws InterruptedException
	{ wait.until(ExpectedConditions.visibilityOf(testCasesLink));
	  // verifying TestCases element is visible
	  Assert.assertTrue(testCasesLink.isDisplayed());  
	  testCasesLink.click();	
	  Thread.sleep(5000);
      // verifying TestCases page is visible
	  Assert.assertTrue(testCases.getText().equals("TEST CASES"));  
	  System.out.println("Verified! User is navigated to test cases page successfully");
	}
	
	public void recommended_Products()
	{ wait.until(ExpectedConditions.visibilityOf(contactUsLink));
	  // Scrolling Down the page upto recommended items 
	  act.scrollToElement(recommndedItems).build().perform();
	  Assert.assertTrue(recommndedItems.isDisplayed());
	  System.out.println("Recommended Items are visible");
	  //Select the Item & add to cart
	  wait.until(ExpectedConditions.visibilityOf(recDressCartBtn1));
	  js.executeScript("arguments[0].click()",recDressCartBtn1);
	  wait.until(ExpectedConditions.visibilityOf(confirmAlert));
	  viewCartLink.click();
	  wait.until(ExpectedConditions.visibilityOf(shoppingCart));
	  //Verify Selected item is in the Cart 
	  System.out.println("No. Of Products in the Cart: "+tableRows.size());
	  System.out.println("No. Of Products in the Cart: "+prodDescription.getText());
	  System.out.println("The Product from Recommended Items is added to Cart Successfully!!!");
	}
	
	public void scrollUpArrow() throws InterruptedException
	{wait.until(ExpectedConditions.visibilityOfAllElements(autoExcercise));
	 // Scrolling Down the page upto 'SUBSCRIPTION' 
	 act.scrollToElement(subscribeTitle).build().perform();
	 Assert.assertTrue(subscribeTitle.isDisplayed());
	 System.out.println("Verified that 'SUBSCRIPTION' is visible");
	 wait.until(ExpectedConditions.visibilityOf(scrollUpArrowBtn));
	 Thread.sleep(3000); // To minimize the Ad
	 scrollUpArrowBtn.click();
	 wait.until(ExpectedConditions.visibilityOf(dynamicBanner));
	 Assert.assertTrue(dynamicBanner.getText().equals("Full-Fledged practice website for Automation Engineers"));
	 System.out.println("'Full-Fledged practice website for Automation Engineers' text is visible");
	}
	
	public void scrollUpScrollDown() throws InterruptedException
	{wait.until(ExpectedConditions.visibilityOfAllElements(autoExcercise));
	 // Scrolling Down the page upto 'SUBSCRIPTION' 
	 act.scrollToElement(subscribeTitle).build().perform();
	 Assert.assertTrue(subscribeTitle.isDisplayed());
	 System.out.println("Verified that 'SUBSCRIPTION' is visible");
	 wait.until(ExpectedConditions.visibilityOf(subscribeTitle));
	 // Scrolling Up the page upto Active Banner 
	 act.scrollToElement(dynamicBanner).build().perform();
	 wait.until(ExpectedConditions.visibilityOf(dynamicBanner));
	 Assert.assertTrue(dynamicBanner.getText().equals("Full-Fledged practice website for Automation Engineers"));
	 System.out.println("'Full-Fledged practice website for Automation Engineers' text is visible");
	}
}
