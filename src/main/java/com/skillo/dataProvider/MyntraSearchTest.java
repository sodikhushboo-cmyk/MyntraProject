package com.skillo.dataProvider;
import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.skillo.utils.ExcelReader;
public class MyntraSearchTest {
	
	@DataProvider(name = "menSearchData")
	public static Object[][] getMenData() {
	    return new Object[][] {
	        {"t-shirt"},
	        {"shirt"},
	        {"jeans"},
	        {"trousers"},
	        {"jacket"},
	        {"kurta"},
	        {"blazer"},
	        {"shorts"},
	        {"hoodie"},
	        {"sweatshirt"},
	        {"sports shoes"},
	        {"casual shoes"},
	        {"formal shoes"},
	        {"sandals"},
	        {"flip flops"}
	    };
	}
	
	@DataProvider(name = "menColorData")
	public Object[][] menColors() {
	    return new Object[][] {
	        {"Black"},
	        {"White"},
	        {"Blue"},
	        {"Navy Blue"},
	        {"Grey"},
	        {"Green"},
	        {"Olive"},
	        {"Brown"},
	        {"Beige"},
	        {"Maroon"},
	        {"Red"},
	        {"Yellow"},
	        {"Orange"},
	        {"Purple"}
	    };
	}
	
	
	@DataProvider(name = "menCategoryData")
	public Object[][] menCategory() {
	    return new Object[][] {
	        {"T-Shirts"},
	        {"Shirts"},
	        {"Jeans"},
	        {"Trousers"},
	        {"Kurtas"},
	        {"Blazers"},
	        {"Shorts"},
	        {"Track Pants"},
	        {"Sweatshirts"},
	        {"Jackets"}
	    };
	}
	@DataProvider(name = "menBrandData")
	public Object[][] menBrand() {
	    return new Object[][] {
	        {"Nike"},
	        {"Adidas"},
	        {"Puma"},
	        {"Levis"},
	        {"Roadster"},
	        {"HRX"},
	        {"U.S. Polo Assn."},
	        {"H&M"},
	        {"Zara"}
	    };
	}
	
	@DataProvider(name = "menPriceData")
	public Object[][] priceData() {
	    return new Object[][] {
	        {"Rs. 0 to Rs. 500"},
	        {"Rs. 500 to Rs. 1000"},
	        {"Rs. 1000 to Rs. 2000"},
	        {"Rs. 2000 to Rs. 5000"},
	        {"Rs. 5000 and above"}
	    };
	}
}
