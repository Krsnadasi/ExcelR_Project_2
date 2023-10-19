package implimentationOfTestCases;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CategoriesOfAutomation;
import pageObjects.HomePageOfAutomation;
import pageObjects.PaymentGateway;
import pageObjects.ProductsPageOfAutomation;
import pageObjects.SignupLogin;

public class TestCases {
	public WebDriver driver;
	SignupLogin logPG;
	HomePageOfAutomation homePG;
	ProductsPageOfAutomation prodPG;
	PaymentGateway payPG;
	CategoriesOfAutomation catePD;

	@BeforeMethod
	public void setUp_Browser() throws InterruptedException
	{ ChromeOptions opt=new ChromeOptions();
	  opt.addExtensions(new File("./Extentions/Ad Block.crx"));
	  WebDriverManager.chromedriver().setup();
	  driver=new ChromeDriver(opt);
	  logPG=new SignupLogin(driver);
	  homePG=new HomePageOfAutomation(driver);
	  prodPG=new ProductsPageOfAutomation(driver);
	  payPG=new PaymentGateway(driver);
	  catePD=new CategoriesOfAutomation(driver);
	}

	@Test(priority=1)
	public void register_NewUser() throws IOException, InterruptedException 
	{ logPG.launchURL();
	  logPG.click_SignupLogin();
	  logPG.register_NewUser();
	  logPG.enter_AccInfo();
	  logPG.delete_Acc();
	}
	
	@Test(priority=2)
	public void login_ValidCredentials() throws IOException
	{ logPG.launchURL();
	  logPG.click_SignupLogin();
	  logPG.login_ValidCreds();
	}

	@Test(priority=3)
	public void login_InvalidCredentials() throws IOException
	{logPG.launchURL();
	 logPG.click_SignupLogin();
	 logPG.login_InvalidCreds();	
	}
	
	@Test(priority=4)
	public void logoutUser_Verification() throws IOException
	{ logPG.launchURL();
	  logPG.click_SignupLogin();
	  logPG.login_ValidCreds();
	  logPG.logout_User();
	}

	@Test(priority=5)
	public void register_ExistingEmailId() throws IOException, InterruptedException 
	{ logPG.launchURL();
	  logPG.click_SignupLogin();
	  logPG.reg_ExistCreds();
	}

	@Test(priority=6)
	public void contactUs_Functionality() throws IOException
	{ logPG.launchURL();
	  homePG.contactUs_Form();
	}
	
	@Test(priority=7)
	public void testCasesPage_Verification() throws InterruptedException
	{ logPG.launchURL();
	  homePG.testCases_Page();
	}
	
	@Test(priority=8)
	public void prodDetails_Verification() throws InterruptedException
	{ logPG.launchURL();
	  prodPG.click_ProductLink();
	  prodPG.prod_details();
	}
	
	@Test(priority=9)
	public void search_Product_Validation() throws InterruptedException
	{ logPG.launchURL();
	  prodPG.click_ProductLink();
	  prodPG.search_Product();
	}
	
	@Test(priority=10)
	public void subscription_inHomePage() throws InterruptedException
	{ logPG.launchURL();
	  prodPG.subScribe_inHomePG();
	}
	
	@Test(priority=11)
	public void subscription_inCartPage() throws InterruptedException
	{ logPG.launchURL();
	  prodPG.subScribe_inCartPage();
	}
	
	@Test(priority=12)
	public void addProducts_InCart() throws InterruptedException
	{ logPG.launchURL();
	  prodPG.click_ProductLink();
	  prodPG.addProducts();
	}
	
	@Test(priority=13)
	public void productQty_InCart() throws InterruptedException
	{ logPG.launchURL();
	  prodPG.click_ProductLink();  
	  prodPG.prod_details();
	  prodPG.quantityProd();
	}
	
	@Test(priority=14)
	public void placeOrder_Register_WhileCheckOut() throws InterruptedException, IOException
	{ logPG.launchURL();
	  prodPG.click_ProductLink();
	  prodPG.addProducts();
	  payPG.proceed_ToCheckout();
	  payPG.click_registerLoginBtn();
	  logPG.register_NewUser();
	  logPG.enter_AccInfo();
	  payPG.click_CartLink();
	  String[] cartdetails=payPG.review_Order();
	  payPG.click_ProceedToCheckOut();
	  payPG.verify_Address();
	   String[] reviewOrderDetails=payPG.review_Order();
	   for(int i=0; i<cartdetails.length; i++)
	   {assertEquals(cartdetails[i], reviewOrderDetails[i]);}
	  payPG.payment_Method();
	  payPG.click_ContinueButton();
	  logPG.delete_Acc();
	}
	
	@Test(priority=15)
	public void placeOrder_Register_BeforeCheckOut() throws InterruptedException, IOException
	{ logPG.launchURL();
	  logPG.click_SignupLogin();
	  logPG.register_NewUser();
	  logPG.enter_AccInfo();
	  prodPG.click_ProductLink();
	  prodPG.addProducts();
	   String[] cartdetails=payPG.review_Order();
	   System.out.println("----------------Product details Of Cart Page-------------------");
	   for(int a=1;a<cartdetails.length;a++)
	    {System.out.print(cartdetails[a]+",  ");}
	     System.out.println();
	  payPG.click_ProceedToCheckOut();
	  payPG.verify_Address();
	   String[] reviewOrderDetails=payPG.review_Order();
	   System.out.println("----------------Product details Of Review Your Order Page-------------------");
	   for(int i=0; i<cartdetails.length; i++)
	    {assertEquals(cartdetails[i], reviewOrderDetails[i]);
	     System.out.print(reviewOrderDetails[i]+",  ");
	    }
	    System.out.println();
	  payPG.payment_Method();
	  payPG.click_ContinueButton();
	  logPG.delete_Acc();
	}
	
	@Test(priority=16)
	public void placeOrder_Login_BeforeCheckOut() throws InterruptedException, IOException
	{ logPG.launchURL();
	  logPG.click_SignupLogin();
	  logPG.login_ValidCreds();
	  prodPG.click_ProductLink();
	  prodPG.addProducts();
	  String[] cartdetails=payPG.review_Order();
	  payPG.click_ProceedToCheckOut();
	  payPG.verify_Address();
	   String[] reviewOrderDetails=payPG.review_Order();
	   for(int i=0; i<cartdetails.length; i++)
	    {assertEquals(cartdetails[i], reviewOrderDetails[i]);}
	  payPG.payment_Method();
	  payPG.click_ContinueButton();
	  logPG.logout_User();
	}
	
	@Test(priority=17)
	public void remove_ProdFromCart() throws InterruptedException
	{ logPG.launchURL();
	  payPG.remove_Products();
	}
	
	@Test(priority=18)
	public void verify_CatogoryOfProducts() throws InterruptedException
	{ logPG.launchURL();
	  catePD.view_Category();
	  catePD.view_MenCategory();
	}
	
	@Test(priority=19)
	public void verify_BrandOfProducts() throws InterruptedException
	{ logPG.launchURL();
	  prodPG.click_ProductLink();
	  catePD.select_BrandBIBA();
	  catePD.select_BrandBabyHug();
	}
	
	@Test(priority=20)
	public void searchProducts_VerifyCartAfterLogin() throws InterruptedException, IOException
	{ logPG.launchURL();
	  prodPG.click_ProductLink();
	  prodPG.search_Product();
	  prodPG.addProducts();
	   String[] beforeLoginCartItems=payPG.review_Order();
	   System.out.println("----------------Product details In Cart Before Login-------------------");
	   for(int a=1;a<beforeLoginCartItems.length;a++)
	    { System.out.print(beforeLoginCartItems[a]+",  ");}
	      System.out.println();
	  logPG.click_SignupLogin();
	  logPG.login_ValidCreds();
	  payPG.click_CartLink();
	   String[] afterLoginCartItems=payPG.review_Order();
	   System.out.println("----------------Product details In Cart After Login-------------------");
	   for(int i=0; i<afterLoginCartItems.length; i++)
	    { assertEquals(beforeLoginCartItems[i], afterLoginCartItems[i]);
	      System.out.print(afterLoginCartItems[i]+",  ");
	    }
	      System.out.println();
	  logPG.logout_User();
	}
	
	@Test(priority=21)
	public void addReview_OnProdudct() throws InterruptedException, IOException
	{ logPG.launchURL();
	  prodPG.click_ProductLink();
	  prodPG.prod_details();
	  prodPG.writeReview();
	}
	
	@Test(priority=22)
	public void addToCart_FromRecommendedItems() throws InterruptedException, IOException
	{ logPG.launchURL();
	  homePG.recommended_Products();
	}
	
	@Test(priority=23)
	public void addressDetails_verification() throws InterruptedException, IOException
	{ logPG.launchURL();
	  logPG.click_SignupLogin();
	  logPG.register_NewUser();
	  logPG.enter_AccInfo();
	  prodPG.click_ProductLink();
	  prodPG.addProducts();
	  payPG.click_ProceedToCheckOut();
	  payPG.verify_DeliveryAddress();
	  logPG.delete_Acc();
	}
	
	@Test(priority=24)
	public void downloadInvoice_AfterOrder() throws InterruptedException, IOException
	{ logPG.launchURL();
	  prodPG.click_ProductLink();
	  prodPG.addProducts();
	  payPG.proceed_ToCheckout();
	  payPG.click_registerLoginBtn();
	  logPG.register_NewUser();
	  logPG.enter_AccInfo();
	  payPG.click_CartLink();
	   String[] cartdetails=payPG.review_Order();
	  payPG.click_ProceedToCheckOut();
	  payPG.verify_Address();
	   String[] reviewOrderDetails=payPG.review_Order();
	   for(int i=0; i<cartdetails.length; i++)
	    { assertEquals(cartdetails[i], reviewOrderDetails[i]);}
	  payPG.payment_Method();
	  payPG.download_Invoice();
	  payPG.click_ContinueButton();
	  logPG.delete_Acc();
	}
	
	@Test(priority=25)
	public void scrollUpArrowButton_Verification() throws InterruptedException
	{ logPG.launchURL();
	  homePG.scrollUpArrow();
	}
	
	@Test(priority=26)
	public void scrollUpAndScrolldown_Functionality() throws InterruptedException
	{ logPG.launchURL();
	  homePG.scrollUpScrollDown();
	}
}
