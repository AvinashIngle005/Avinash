package Practice.utility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.CommonUtility.CommonUtility;

public class VerifyCartItems extends CommonUtility {
	WebDriver driver;

	public VerifyCartItems(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ("//div[@class='sc-list-item-content']//span[@class='a-truncate-cut']"))
	List<WebElement> cartItems;

	By cartItem = By.xpath("//div[@class='sc-list-item-content']//span[@class='a-truncate-cut']");

	public boolean verifyCartItems(String[] allNames) {
		List<String> names = Arrays.asList(allNames);
		waitUntilVisibilityElementLocated(cartItem);
//		cartItems.stream().forEach(s->System.out.println(s.getText()));
		List<String> itemNames = cartItems.stream().map(s -> s.getText()).collect(Collectors.toList());
		return itemNames.containsAll(names);
	}

}
