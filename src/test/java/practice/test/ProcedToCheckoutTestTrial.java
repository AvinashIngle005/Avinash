package practice.test;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Practice.utility.SearchProduct;

public class ProcedToCheckoutTestTrial {

	@Test
	public void submitOrder() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Avinash\\eclipse-workspace-new\\PracticeProject\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://www.amazon.in");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
		String search="boat headphones";
		String firstProductName = "boAt Bassheads 162 in Ear Wired Earphones with Mic(Jazzy Blue)";
		String secondProductName = "boAt BassHeads 225 Special Edition in-Ear Wired Headphones with Mic (Blue)";
		
		WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.sendKeys("boat headphones");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='nav-flyout-searchAjax']//div[@class='s-suggestion-container']")));
		List<WebElement> suggestion = driver
				.findElements(By.xpath("//div[@id='nav-flyout-searchAjax']//div[@class='s-suggestion-container']"));
		WebElement searchResult = suggestion.stream().filter(s -> s.getText().equalsIgnoreCase(search))
				.findFirst().get();
		searchResult.click();
		
//		wait.until(
//				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'s-card-container')]")));
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'s-card-container')]")));
		List<WebElement> products = driver.findElements(
				By.xpath("//div[contains(@class,'s-card-container')]//span[contains(@class,'a-size-medium')]"));
		System.out.println(products.size());
		

	products.stream().filter(s->s.getText().equalsIgnoreCase(secondProductName)).forEach(s->System.out.println(s));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView();", firstProd);
//		Thread.sleep(5000);
//		firstProd.click();

		Set<String> window = driver.getWindowHandles();
		Iterator<String> w = window.iterator();
		String parentWindow = w.next();
		String firstChildWindow = w.next();

//		driver.switchTo().window(firstChildWindow).getTitle();
		driver.switchTo().window(parentWindow);
		driver.switchTo().window(firstChildWindow);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#add-to-cart-button")));
		driver.findElement(By.xpath("//span[@id='submit.add-to-cart']")).click();
//		Thread.sleep(5000);
//		driver.close();

		driver.switchTo().window(parentWindow);
		WebElement secondProd = products.stream()
				.filter(product -> product.getText().equalsIgnoreCase(secondProductName)).findFirst().get();
//		Thread.sleep(5000);
		secondProd.click();
		Set<String> wa=driver.getWindowHandles();
		Iterator<String> b = wa.iterator();
		String parentid=b.next();
		String firstCWindow=b.next();
		String secondCWindow=b.next();
		driver.switchTo().window(parentid);
		driver.switchTo().window(secondCWindow).getTitle();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#add-to-cart-button")));
//		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[@id='submit.add-to-cart']")));
		driver.findElement(By.xpath("//span[@id='submit.add-to-cart']")).click();
//		Thread.sleep(5000);
		driver.switchTo().window(parentid);
//		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector("#nav-cart")));
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#nav-cart")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sc-list-item-content']//span[@class='a-truncate-cut']")));
		List<WebElement> cartItems=driver.findElements(By.xpath("//div[@class='sc-list-item-content']//span[@class='a-truncate-cut']"));
		boolean a=cartItems.stream().allMatch(s->s.getText().equalsIgnoreCase(firstProductName)||s.getText().equalsIgnoreCase(secondProductName));
		System.out.println(a);
	}

	public void getProductText() {
		
	}
}



//WebElement signIn = driver.findElement(By.cssSelector("#nav-link-accountList"));
//Actions a = new Actions(driver);
//a.moveToElement(signIn).build().perform();
//driver.findElement(By.cssSelector(".nav-action-inner")).click();
//WebElement email = driver.findElement(By.cssSelector("#ap_email"));
//wait.until(ExpectedConditions.visibilityOf(email));
//email.sendKeys("7770087927");
//driver.findElement(By.cssSelector("#continue")).click();
//WebElement password = driver.findElement(By.cssSelector("#ap_password"));
//wait.until(ExpectedConditions.visibilityOf(password));
//password.sendKeys("Archana@135");
//driver.findElement(By.cssSelector("#signInSubmit")).click();
