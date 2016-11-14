package test.java.com.automation.practice;
import org.testng.Assert;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.StringContains.containsString;
import org.hamcrest.core.Is;
//import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import main.java.com.RESTAPI.JsonBlob;

public class APItests{
	
	private String result = "";
	
	@Test( priority = 1)
	
	public void post() throws Exception{
		
		result = JsonBlob.sendPost();
		//JsonBlob.sendGet(result);
	}
	
	
	@Test( priority = 2)
	
public void get() throws Exception{
		
		JsonBlob.sendGet(result);
	}
	
	@Test(priority = 3)
	
public void delete() throws Exception{
		
		JsonBlob.sendDelete(result);
	}
	
	@Test( priority = 4)
	
public void getEmpty() throws Exception{

		Assert.assertEquals(JsonBlob.sendGet(result), "404");
	}
	
	

}
