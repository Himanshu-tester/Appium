package practice;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="InputData")
	
	public Object[][] getDataforEditField() {
		
		Object[][] obj = new Object[][]
				{
			{"hello"},{"@3fr&$"}
				};
				
				return obj;
	}

}
