package Practice.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.CommonUtility.CommonUtility;

public class SearchProduct extends CommonUtility {

	WebDriver driver;
	String browserName;

	public SearchProduct(WebDriver driver, String browserName) {
		super(driver);
		this.driver = driver;
		this.browserName = browserName;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	WebElement searchBox;

	@FindBy(xpath = "//div[@id='nav-flyout-searchAjax']//div[@class='s-suggestion-container']")
	List<WebElement> suggestion;

	@FindBy(xpath = ("//div[contains(@class,'s-card-container')]//span[contains(@class,'a-size-medium')]"))
	List<WebElement> products;

	@FindBy(xpath = ("//span[@id='submit.add-to-cart']"))
	WebElement addToCartButton;

	@FindBy(css = ("#nav-cart"))
	WebElement cartIcon;
	
	By product = By.xpath("//div[contains(@class,'s-card-container')]");

	By suggestions = By.xpath("//div[@id='nav-flyout-searchAjax']//div[@class='s-suggestion-container']");
	
	public void searchProduct(String product) throws IOException {
		waitUntilVisibilityOf(searchBox);
		searchBox.sendKeys(product);
		takeScreenShot();
	}
	
	public boolean verifySearchTextField() {
		return searchBox.isDisplayed();
	}
	
	public boolean verifySearchAcceptsText(String search) throws InterruptedException {
		searchBox.sendKeys(search);
		return searchBox.isEnabled();
	}

	public void getSearchResults(String search) {
		waitUntilVisibilityElementLocated(suggestions);
		WebElement searchResult = suggestion.stream().filter(s -> s.getText().equalsIgnoreCase(search)).findFirst()
				.get();
		searchResult.click();
	}
	
	public boolean verifyProductIsPresent(String search, String productMatch) throws InterruptedException {
		List<WebElement> allProducts = getProducts();
//		WebElement Product = allProducts.stream().filter(product -> product.getText().equalsIgnoreCase(productMatch))
//				.findFirst().get();
		return allProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productMatch));
		
	}

	public List<WebElement> getProducts() throws InterruptedException {
		waitUntilVisibilityElementLocated(product);
		return products;
	}

	public void getProduct(String ProductName) throws InterruptedException {
		List<WebElement> allProducts = getProducts();
		WebElement Product = allProducts.stream().filter(product -> product.getText().equalsIgnoreCase(ProductName))
				.findFirst().get();
		scrollIntoView(Product);
		Product.click();
	}

	public void selectAndAddToCart(String[] allNames) throws InterruptedException {
		List<String> names = Arrays.asList(allNames);
		int count = 0;
		Iterator<String> w = null;
		String parentWindow = null;
		Set<String> windowHandles;
		List<String> windowStrings;
		String reqWindow;
		String firstWindow;
		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {
			for (int i = 0; i < names.size(); i++) {
				getProduct(names.get(i));
				if (count < 1) {
					count++;
					w = windowHandles();
					parentWindow = w.next();
					String firstChildWindow = w.next();
					switchTo(parentWindow);
					switchTo(firstChildWindow);
					waitUntilVisibilityOf(addToCartButton);
					scrollIntoView(addToCartButton);
					addToCartButton.click();
					driver.switchTo().window(parentWindow);
				} else if (count > 0) {
					count++;
					w = windowHandles();
					parentWindow = w.next();
					String firstChildWindow = w.next();
					String secondChildWindow = w.next();
					switchTo(parentWindow);
					switchTo(secondChildWindow);
					waitUntilVisibilityOf(addToCartButton);
					scrollIntoView(addToCartButton);
					addToCartButton.click();
					switchTo(parentWindow);
				}
			}
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			for (int i = 0; i < names.size(); i++) {
				getProduct(names.get(i));
				if (count < 1) {
					count++;
					windowHandles = driver.getWindowHandles();
				    windowStrings = new ArrayList<>(windowHandles);
				    firstWindow = windowStrings.get(0);
				    reqWindow = windowStrings.get(1);
				    switchTo(firstWindow);
				    switchTo(reqWindow);
					waitUntilVisibilityOf(addToCartButton);
					scrollIntoView(addToCartButton);
					addToCartButton.click();
					switchTo(firstWindow);
				} else if (count > 0) {
					count++;
					windowHandles = driver.getWindowHandles();
				    windowStrings = new ArrayList<>(windowHandles);
				    reqWindow = windowStrings.get(1);
				    firstWindow = windowStrings.get(0);
					switchTo(firstWindow);
					switchTo(reqWindow);
					waitUntilVisibilityOf(addToCartButton);
					scrollIntoView(addToCartButton);
					addToCartButton.click();
					switchTo(firstWindow);
				}
			}
		}
	}

	public VerifyCartItems goToCart() {
		driver.navigate().refresh();
		waitUntilVisibilityOf(cartIcon);
		scrollIntoView(cartIcon);
		cartIcon.click();
		VerifyCartItems verifyCartItems = new VerifyCartItems(driver);
		return verifyCartItems;
	}

}