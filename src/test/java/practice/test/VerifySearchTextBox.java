package practice.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Practice.CommonUtility.BasePage;

public class VerifySearchTextBox extends BasePage {
	
	@Test(groups= {"regression"})
	public void textBox() throws InterruptedException {
		
		Assert.assertTrue(searchProduct.verifySearchTextField());
		
	}
	
	@Test(groups= {"smoke"})
	public void verifyTextBoxIsEnabled() throws InterruptedException {
		Assert.assertTrue(searchProduct.verifySearchAcceptsText("Headphones"));
	}

}
