package com.google.gmail.testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gmail.generic.GenericXLLIbrary;
import com.google.gmail.generic.Utillity;
import com.google.gmail.pom.HomePage;
import com.google.gmail.pom.OrderDetailPage;
import com.google.gmail.pom.ProductDetailsPage;
import com.google.gmail.pom.ProductsListPage;
//Taking Data From ExcelSheet and Verifying whether
//the deleted product from ODP is not displayed in the ODP Page
public class TC002 extends BaseTest {
	@Test(description="Test case to verify if the deleted product is not displayed in ODP page")
	public void testToDeletAddedProductFromODP() {
		HomePage hp = new HomePage(driver,webActionUtil);		
		//Getting testdata from XL
		String sheetName = "TC002";
		String menuName = GenericXLLIbrary.getData(XL_PATH, sheetName, 1, 0);
		String productId = GenericXLLIbrary.getData(XL_PATH, sheetName, 1, 1);
		productId = Utillity.split(productId);
		String increaseQuantity = GenericXLLIbrary.getData(XL_PATH, sheetName, 1, 2);
		int inQ=Integer.parseInt(Utillity.split(increaseQuantity));
		String decreaseQuantity = GenericXLLIbrary.getData(XL_PATH, sheetName, 1, 3);
		int deQ=Integer.parseInt(Utillity.split(decreaseQuantity));
		String size = GenericXLLIbrary.getData(XL_PATH, sheetName, 1, 4);
		String color = GenericXLLIbrary.getData(XL_PATH, sheetName, 1, 5);		
		ProductsListPage productListPage = hp.clickOnMenu(menuName);
		ProductDetailsPage pdp = productListPage.selectProduct(productId);
		OrderDetailPage odp = pdp.addSelectedItemToKart(inQ, deQ, size, color);
		Assert.assertEquals(odp.isProductDisplayed(productId), true);		
		odp.deleteProduct(productId);
		Utillity.sleepInSeconds(5);
		Assert.assertEquals(odp.isProductDisplayed(productId), false);
	}
}
