package practice.test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Practice.CommonUtility.BasePage;
import Practice.utility.VerifyCartItems;

public class SecondProduct extends BasePage {
//	C:\Users\Avinash\eclipse-workspace-new\PracticeProject\testData\SearchProductTestData.json
	@Test(dataProvider="getData")
	public void secondProductSubmitOrder(HashMap<String,String> input) throws InterruptedException, IOException {

//		String search = "boat headphones";
		String[] names = { input.get("firstProduct"),
				input.get("secondProduct")};
		searchProduct.searchProduct(input.get("search"));
		Thread.sleep(5000);
		searchProduct.getSearchResults(input.get("search"));
		searchProduct.selectAndAddToCart(names);
		VerifyCartItems verifyCartItems = searchProduct.goToCart();
		assertTrue(verifyCartItems.verifyCartItems(names));

	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") + "\\testData\\SearchProductData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}

}
