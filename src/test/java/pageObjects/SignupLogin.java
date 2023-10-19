package pageObjects;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupLogin {
	public WebDriver driver;
	WebDriverWait wait;

	public SignupLogin(WebDriver wd)
	{ driver=wd;
	  PageFactory.initElements(driver, this);
	  wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	//Repository Of Home Page WebElements//
	@FindBy(xpath="//ul[@class='nav navbar-nav']/child::li/a")List<WebElement> autoExcercise;
	@FindBy(xpath="//a[normalize-space()='Signup / Login']")WebElement signLog;

	//Repository Of SignUp Page WebElements//
	@FindBy(xpath="//div[@class='signup-form']")WebElement newUserForm;
	@FindBy(xpath="//input[@name='name']")WebElement regName;
	@FindBy(xpath="(//input[@name='email'])[2]")WebElement regEmail;
	@FindBy(xpath="(//button[@type='submit'])[2]")WebElement signupBtn;
	@FindBy(xpath = "//div[@class='col-sm-4']/descendant::p")WebElement signUpErrMsg;

	//Repository Of Account Information Page WebElements//
	@FindBy(xpath = "//div[@class='login-form']")WebElement loginAccForm;
	@FindBy(xpath="//input[@type='text']")List<WebElement> inforList;
	@FindBy(xpath = "//input[@id='id_gender2']")WebElement titleMrs;
	@FindBy(xpath = "//input[@id='name']")WebElement name;
	@FindBy(xpath = "//input[@id='email']")WebElement email;
	@FindBy(xpath = "//input[@id='password']")WebElement password;
	@FindBy(xpath = "//select[@id='days']")WebElement birthDays;
	@FindBy(xpath = "//select[@id='months']")WebElement birthMonths;
	@FindBy(xpath = "//select[@id='years']")WebElement birthYear;
	@FindBy(xpath = "//input[@id='newsletter']")WebElement newsCheckBox;
	@FindBy(xpath = "//input[@id='optin']")WebElement offerCheckBox;
	@FindBy(xpath = "//input[@id='first_name']")WebElement firstName;
	@FindBy(xpath = "//input[@id='last_name']")WebElement lastName;
	@FindBy(xpath = "//input[@id='company']")WebElement company;
	@FindBy(xpath = "//input[@id='address1']")WebElement address1;
	@FindBy(xpath = "//select[@id='country']")WebElement country;
	@FindBy(xpath = "//input[@id='state']")WebElement state;
	@FindBy(xpath = "//input[@id='city']")WebElement city;
	@FindBy(xpath = "//input[@id='zipcode']")WebElement zipCode;
	@FindBy(xpath = "//input[@id='mobile_number']")WebElement mobileNum;
	@FindBy(xpath = "//button[contains(text(),'Create Account')]")WebElement createAccBtn;

	//Home page WebElements
	@FindBy(xpath = "//h2[@data-qa='account-created']")WebElement confirmMsg;
	@FindBy(xpath = "//a[@data-qa='continue-button']")WebElement continueBtn;
	@FindBy(xpath = "//div[@id='dismiss-button']")WebElement dismissBtn;
	@FindBy(linkText = "Delete Account")WebElement delAcc;
	@FindBy(xpath = "//div[@class='container']/descendant::b")WebElement delMsg;

	//Repository Of Login Page WebElements//
	@FindBy(xpath = "//div[@class='login-form']")WebElement loginForm;
	@FindBy(xpath="(//input[@name='email'])[1]")WebElement loginEmail;
	@FindBy(xpath="//input[@type='password']")WebElement passWord;
	@FindBy(xpath="(//button[@type='submit'])[1]")WebElement loginBtn;
	@FindBy(xpath = "//div[@class='col-sm-4 col-sm-offset-1']/descendant::p")WebElement loginErrMsg;
	@FindBy(xpath = "//div[@class='col-sm-8']/descendant::b")WebElement loginIdMsg;
	@FindBy(linkText = "Logout")WebElement logOutBtn;


	public void launchURL()
	{driver.manage().window().maximize();
	 driver.get("https://automationexercise.com");
	 wait.until(ExpectedConditions.visibilityOfAllElements(autoExcercise));
	 //AdBlocker window handling//
	 Set<String>handles=driver.getWindowHandles();
	 Iterator<String> itr=handles.iterator();
	 String parentWindow=itr.next();
	 String window1=itr.next();
	 driver.switchTo().window(parentWindow);
	 System.out.println(" Verified that home page is visible successfully");
	}

	public void click_SignupLogin()
	{Assert.assertTrue(signLog.isDisplayed());
	 wait.until(ExpectedConditions.visibilityOf(signLog));
	 signLog.click();
	}

	public void register_NewUser() throws IOException
	{wait.until(ExpectedConditions.visibilityOf(newUserForm));
	//Verification of 'New User Signup!' is visible
	 Assert.assertTrue(newUserForm.isDisplayed()); 
	 String filePath="C:\\Users\\Smita\\OneDrive\\Desktop\\ExcelR Project-2.xlsx";
	 FileInputStream fis=new FileInputStream(filePath);
	 XSSFWorkbook workBook=new XSSFWorkbook(fis);
	 XSSFSheet sheet=workBook.getSheet("SignUp");
	 regName.sendKeys(sheet.getRow(1).getCell(0).toString());
	 regEmail.sendKeys(sheet.getRow(1).getCell(1).toString());
     System.out.println("Signup New user form Is Filled.");
	 fis.close();
	 workBook.close();
	 signupBtn.click();
	}

	public void enter_AccInfo() throws IOException, InterruptedException
	{wait.until(ExpectedConditions.visibilityOfAllElements(inforList));
	 //Verification 'ENTER ACCOUNT INFORMATION'
	 Assert.assertTrue(loginAccForm.isDisplayed()); 
	 //Fill up Form With required Info to register
	 titleMrs.click();
	 //Opening Excel file
	 String filePath="C:\\Users\\Smita\\OneDrive\\Desktop\\ExcelR Project-2.xlsx";
	 FileInputStream fis=new FileInputStream(filePath);
	 XSSFWorkbook workBook=new XSSFWorkbook(fis);
	 XSSFSheet sheet=workBook.getSheet("AccountInfo");
	 password.sendKeys(sheet.getRow(1).getCell(2).getRawValue());
	 Select day=new Select(birthDays);
	 day.selectByValue(sheet.getRow(1).getCell(3).getRawValue());
	 Select month=new Select(birthMonths);
	 month.selectByVisibleText(sheet.getRow(1).getCell(4).toString());
	 Select year=new Select(birthYear);
	 year.selectByValue(sheet.getRow(1).getCell(5).getRawValue());
	 newsCheckBox.click();
	 offerCheckBox.click(); 
	 firstName.sendKeys(sheet.getRow(1).getCell(6).toString());
	 lastName.sendKeys(sheet.getRow(1).getCell(7).toString());
	 company.sendKeys(sheet.getRow(1).getCell(8).toString());
	 address1.sendKeys(sheet.getRow(1).getCell(9).toString());
	 Select contr=new Select(country);
	 contr.selectByValue(sheet.getRow(1).getCell(10).toString());
	 state.sendKeys(sheet.getRow(1).getCell(11).toString());
	 city.sendKeys(sheet.getRow(1).getCell(12).toString());
	 zipCode.sendKeys(sheet.getRow(1).getCell(13).getRawValue());
	 mobileNum.sendKeys(sheet.getRow(1).getCell(14).getRawValue());
     System.out.println("Account Information Form Is Filled With Required Info");
	 fis.close();
	 workBook.close();
	 createAccBtn.click();
	
	 //'Account Created.' verify confirmation message 
	 wait.until(ExpectedConditions.visibilityOf(confirmMsg));
	 Assert.assertTrue(confirmMsg.isDisplayed());
	 continueBtn.click();
	 //Verifying User Logged in as 'Username'//
	 wait.until(ExpectedConditions.visibilityOf(loginIdMsg));
	 if(loginIdMsg.getText().equals("Radha"))
	  { assertEquals(loginIdMsg.getText(), sheet.getRow(1).getCell(0).toString());
	    System.out.println("Logged in as username: Radha");	
	  }
	}

	public void delete_Acc()
	{wait.until(ExpectedConditions.visibilityOf(delAcc));
	 delAcc.click();
	 wait.until(ExpectedConditions.visibilityOf(delMsg));
	 Assert.assertTrue(delMsg.getText().equals("ACCOUNT DELETED!"));
	 System.out.println("Account is Deleted Successfully!");
	 continueBtn.click();
	}

	public void login_ValidCreds() throws IOException
	{wait.until(ExpectedConditions.visibilityOf(loginForm));
	 // Verify User login Form is visible
	 Assert.assertTrue(loginForm.isDisplayed());
	 String filePath="C:\\Users\\Smita\\OneDrive\\Desktop\\ExcelR Project-2.xlsx";
	 FileInputStream fis=new FileInputStream(filePath);
	 XSSFWorkbook workBook=new XSSFWorkbook(fis);
	 XSSFSheet sheet=workBook.getSheet("ValidLogin");
	 loginEmail.sendKeys(sheet.getRow(1).getCell(0).toString());
	 passWord.sendKeys(sheet.getRow(1).getCell(1).getRawValue());
	 loginBtn.click();

	 wait.until(ExpectedConditions.visibilityOf(loginIdMsg));
	 if(loginIdMsg.getText().equals("Vishaka"))
	  { assertEquals(loginIdMsg.getText(), sheet.getRow(1).getCell(2).toString());
	    System.out.println("Logged in as username: Vishaka");	
	  }
	 //Closing FileInputstream & Workbook
	 fis.close();
	 workBook.close();
	}

	public void login_InvalidCreds() throws IOException
	{wait.until(ExpectedConditions.visibilityOf(loginForm));
	 //Verify User Login Form is visible
	 Assert.assertTrue(loginForm.isDisplayed());
	 String filePath="C:\\Users\\Smita\\OneDrive\\Desktop\\ExcelR Project-2.xlsx";
	 FileInputStream fis=new FileInputStream(filePath);
	 XSSFWorkbook workBook=new XSSFWorkbook(fis);
	 XSSFSheet sheet=workBook.getSheet("InvalidLogin");
	 loginEmail.sendKeys(sheet.getRow(1).getCell(0).toString());
	 passWord.sendKeys(sheet.getRow(1).getCell(1).getRawValue());
	 loginBtn.click();
	 wait.until(ExpectedConditions.visibilityOf(loginErrMsg));
	 if(loginErrMsg.getText().equals("Your email or password is incorrect!"))
	  { assertEquals(loginErrMsg.getText(), sheet.getRow(1).getCell(2).toString());
	    System.out.println("User is getting Error Message");	
	  }
	}

	public void logout_User() throws IOException
	{wait.until(ExpectedConditions.visibilityOf(loginIdMsg));
	 logOutBtn.click();
	 wait.until(ExpectedConditions.visibilityOf(loginForm));
	 Assert.assertTrue(loginForm.isDisplayed());	
	 System.out.println("User is successfully Logged out");
	}

	public void reg_ExistCreds() throws IOException
	{wait.until(ExpectedConditions.visibilityOf(newUserForm));
	 //Verification of 'New User Signup!' is visible 
	 Assert.assertTrue(newUserForm.isDisplayed()); 
	 String filePath="C:\\Users\\Smita\\OneDrive\\Desktop\\ExcelR Project-2.xlsx";
	 FileInputStream fis=new FileInputStream(filePath);
	 XSSFWorkbook workBook=new XSSFWorkbook(fis);
	 XSSFSheet sheet=workBook.getSheet("SignUp");
	 regName.sendKeys(sheet.getRow(3).getCell(0).toString());
	 regEmail.sendKeys(sheet.getRow(3).getCell(1).toString());
	 signupBtn.click();

	 wait.until(ExpectedConditions.visibilityOf(signUpErrMsg));
	 if(signUpErrMsg.getText().equals("Email Address already exist!"))
	  { assertEquals(signUpErrMsg.getText(), sheet.getRow(3).getCell(2).toString());
	    System.out.println("Email Address already exist!");	
	  }
	 fis.close();
	 workBook.close();
	}
}
