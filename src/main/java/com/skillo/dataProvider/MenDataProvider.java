package com.skillo.dataProvider;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import com.skillo.utils.ExcelReader;
/// WITH EXCELSHEET
public class MenDataProvider {


	@DataProvider(name="brandDataTshirt")
	public static Object[][] readExcelOfTshirt_Brands() throws IOException {
		return ExcelReader.readExcel(0);
	
	}
	
	@DataProvider(name="pincodeDataTshirt")
	public static Object[][] readExcelOfPincodes() throws IOException {
		return ExcelReader.readExcel(1);
	}

}


