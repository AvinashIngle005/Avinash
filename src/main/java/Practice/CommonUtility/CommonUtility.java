package Practice.CommonUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtility {
	WebDriver driver;
	WebDriverWait wait;

	public CommonUtility(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void goToLandingPage() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\GlobalProperties.properties");
		prop.load(fis);
		String url = prop.getProperty("url");
		driver.get(url);

	}

	public void waitUntilVisibilityOf(WebElement webElement) {

		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void waitUntilVisibilityElementLocated(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public Iterator<String> windowHandles() {
		Set<String> window = driver.getWindowHandles();
		Iterator<String> w = window.iterator();
		return w;
	}

	public void moveToElement(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).build().perform();
	}

	public void switchTo(String window) {
		driver.switchTo().window(window);
	}

	public void takeScreenShot() throws IOException {
		Random random = new Random();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		File ss = new File(
				System.getProperty("user.dir") + "\\Results\\Run\\ScreenShot" + random.nextInt(10000) + ".png");
		FileUtils.copyFile(file, ss);

	}
}
