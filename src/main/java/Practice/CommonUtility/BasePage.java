package Practice.CommonUtility;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Practice.utility.SearchProduct;

public class BasePage {
	WebDriver driver;
	public SearchProduct searchProduct;

	public WebDriver browserInitialization() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}

	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	
	public String getScreenshot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		File ss = new File(System.getProperty("user.dir") + "\\Results\\" + testCaseName + ".png");
		FileUtils.copyFile(file, ss);
		return System.getProperty("user.dir") + "\\Results\\" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun=true)
	public SearchProduct launchApplication() throws IOException {
		driver = browserInitialization();
		searchProduct = new SearchProduct(driver);
		searchProduct.goToLandingPage();
		return searchProduct;
	}

	@AfterMethod(alwaysRun=true)
	public void closeWindow() {
		driver.quit();
	}

}
