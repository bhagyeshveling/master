package com.google.gmail.testscripts;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gmail.generic.GenericXLLIbrary;
import com.google.gmail.generic.Utillity;
import com.google.gmail.pom.HomePage;
import com.google.gmail.pom.OrderDetailPage;
import com.google.gmail.pom.ProductDetailsPage;
import com.google.gmail.pom.ProductsListPage;

//To Take Multiple from the XL Sheet and Use DataProvider
public class TC003 extends BaseTest {
	
	@DataProvider
	public String[][] getData() {
		return GenericXLLIbrary.getMultipleData(XL_PATH, "TC003");
	}
	
	
	@Test(dataProvider="getData",
			description="Test case to verify if the Added product via "
					+ "Data Provider is displayed in ODP page")
	public void testAddingMultipleProductsToKart(String menuName,
												 String productId,
												 String increaseQuantity,
												 String decreaseQuantity,
												 String size,
												 String colorName) {
		productId = Utillity.split(productId);
		int inQ=Integer.parseInt(Utillity.split(increaseQuantity));
		int dcQ=Integer.parseInt(Utillity.split(decreaseQuantity));
		
		
		HomePage hp = new HomePage(driver,webActionUtil);
		ProductsListPage prodListPage = hp.clickOnMenu(menuName);
		ProductDetailsPage pdp = prodListPage.selectProduct(productId);

		OrderDetailPage odp = pdp.addSelectedItemToKart(inQ, dcQ, size, colorName);
		Assert.assertEquals(odp.isProductDisplayed(productId),true);
	}
}
