package pageObjects;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoriesOfAutomation {
	public WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions act;
	
	public CategoriesOfAutomation(WebDriver wd)
	{ driver=wd;
	  PageFactory.initElements(driver, this);
	  wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	  js = (JavascriptExecutor) driver;
	  act=new Actions(driver);
	}
	
	//Repository Of WebElements for TestCase 18//
	@FindBy(xpath = "//a[normalize-space()='Women']")WebElement categoryWomen;
	@FindBy(xpath = "//a[normalize-space()='Men']")WebElement categoryMen;
	@FindBy(xpath = "(//a[contains(text(),'Dress')])[1]")WebElement womenDress;
	@FindBy(xpath = "//h2[@class='title text-center']")WebElement searchedCategoryTitle;
	@FindBy(xpath = "//h2[normalize-space()='Category']")WebElement sideBarCategory;
	@FindBy(xpath = "//a[normalize-space()='Tshirts']")WebElement MenTshirts;
		
	//Repository Of WebElements for TestCase 19//
	@FindBy(xpath = "//h2[normalize-space()='Brands']")WebElement sideBarBrand;
	@FindBy(xpath = "//a[@href='/brand_products/Biba']")WebElement brandBiba;
	@FindBy(xpath = "//a[@href='/brand_products/Babyhug']")WebElement brandBabyHug;

	
	public void view_Category() throws InterruptedException
	{ wait.until(ExpectedConditions.visibilityOf(sideBarCategory));
	  //verify category is visible & Click 'WOMEN' & 'DRESS' Category 
	  Assert.assertTrue(sideBarCategory.getText().equals("CATEGORY"));
	  js.executeScript("arguments[0].click()", categoryWomen);
	  wait.until(ExpectedConditions.visibilityOf(womenDress));
	  js.executeScript("arguments[0].click()", womenDress);
	  Thread.sleep(3000);
	  wait.until(ExpectedConditions.visibilityOf(searchedCategoryTitle));
	  Assert.assertTrue(searchedCategoryTitle.getText().equals("WOMEN - DRESS PRODUCTS"));
	  System.out.println(" Verified that Category Page is displayed With Title Of 'WOMEN - TOPS PRODUCTS'");
	  
	}
	public void view_MenCategory()
	{ wait.until(ExpectedConditions.visibilityOf(sideBarCategory));
	  //verify 'CATEGORY' is visible & Click 'MEN' & 'TSHIRTS' Category 
	  js.executeScript("arguments[0].click()", categoryMen);	
	  wait.until(ExpectedConditions.visibilityOf(MenTshirts));
	  js.executeScript("arguments[0].click()", MenTshirts);	
	  wait.until(ExpectedConditions.visibilityOf(searchedCategoryTitle));
	  Assert.assertTrue(searchedCategoryTitle.getText().equals("MEN - TSHIRTS PRODUCTS"));
	  System.out.println(" Verified that Category Page is displayed With Title Of 'MEN - TSHIRTS PRODUCTS'");
	}
	
	public void select_BrandBIBA()
	{ wait.until(ExpectedConditions.visibilityOf(sideBarBrand));
	  //verify 'BRAND' is visible & Click 'BIBA' Brand
	  Assert.assertTrue(sideBarBrand.getText().equals("BRANDS"));
	  js.executeScript("arguments[0].click()", brandBiba);
	  wait.until(ExpectedConditions.visibilityOf(searchedCategoryTitle));
	  Assert.assertTrue(searchedCategoryTitle.getText().equals("BRAND - BIBA PRODUCTS"));
	  System.out.println(" Verified that Category Page is displayed With Title Of 'BRAND - BIBA PRODUCTS'"); 
	}
	
	public void select_BrandBabyHug()
	{ wait.until(ExpectedConditions.visibilityOf(sideBarBrand));
	  //verify 'BRAND' is visible & Click 'BABYHUG' Brand
	  js.executeScript("arguments[0].click()", brandBabyHug);
	  wait.until(ExpectedConditions.visibilityOf(searchedCategoryTitle));
	  Assert.assertTrue(searchedCategoryTitle.getText().equals("BRAND - BABYHUG PRODUCTS"));
	  System.out.println(" Verified that Category Page is displayed With Title Of 'BRAND - BABYHUG PRODUCTS'"); 
		
	}
}
