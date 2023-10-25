package practice.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Practice.CommonUtility.BasePage;
import Practice.utility.VerifyCartItems;

public class ProcedToCheckoutTest extends BasePage {

	@Test(dataProvider="getData", groups= {"regression"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {

		String[] names = { input.get("firstProduct"),
				input.get("secondProduct")};
		searchProduct.searchProduct(input.get("search"));
		searchProduct.getSearchResults(input.get("search"));
		searchProduct.selectAndAddToCart(names);
		VerifyCartItems verifyCartItems = searchProduct.goToCart();
		Assert.assertTrue(verifyCartItems.verifyCartItems(names));

	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") + "\\testData\\SearchProductData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	
	@Test(dataProvider="getData", groups= {"smoke"})
	public void verifyProductDisplayed(HashMap<String,String> input) throws IOException, InterruptedException {
		searchProduct.searchProduct(input.get("search"));
		searchProduct.getSearchResults(input.get("search"));
		Assert.assertTrue(searchProduct.verifyProductIsPresent(input.get("search"),input.get("firstProduct")));
		Assert.assertTrue(searchProduct.verifyProductIsPresent(input.get("search"),input.get("secondProduct")));
	}

}
