package Scripts;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.Status;

import POM_Pages.Home_page;
import POM_Pages.Login_page;
import genral.BaseClass;



@Test
public class Homepage_Scripts extends BaseClass {

   Logger log = Logger.getLogger(Home_page.class) ;
			  
@Test(priority = 1)
public void TC_01() throws Exception {
	test = reports.createTest("TC_01:Successfull login");
	Home_page home = PageFactory.initElements(driver, Home_page.class);
	Login_page login=PageFactory.initElements(driver, Login_page.class);
	login.User_name().sendKeys("8152838696");
	Assert.assertEquals(login.User_name().getAttribute("value"), "8152838696");
	test.log(Status.PASS, "Successfully enter the user name :"+login.User_name().getAttribute("value"));
	login.Password().sendKeys("Akshay@94811");
	Assert.assertEquals(login.Password().getAttribute("value"), "Akshay@94811");
	test.log(Status.PASS, "Successfully enter the password :"+login.Password().getAttribute("value"));
	login.Login().click();
	Assert.assertEquals(driver.getTitle(), "");
	test.log(Status.PASS, "Successfully click on login :"+driver.getTitle());
}

@Test(priority = 2)
public void TC_02() throws Exception {
	test = reports.createTest("TC_01:Successfull login");
	Home_page home = PageFactory.initElements(driver, Home_page.class);
	Login_page login=PageFactory.initElements(driver, Login_page.class);
	login.User_name().sendKeys("8152838696");
	Assert.assertEquals(login.User_name().getAttribute("value"), "8152838696");
	test.log(Status.PASS, "Successfully enter the user name :"+login.User_name().getAttribute("value"));
	login.Password().sendKeys("Akshay@94811");
	Assert.assertEquals(login.Password().getAttribute("value"), "Akshay@94811");
	test.log(Status.PASS, "Successfully enter the password :"+login.Password().getAttribute("value"));
	login.Login().click();
	Assert.assertEquals(driver.getTitle(), "");
	test.log(Status.PASS, "Successfully click on login :"+driver.getTitle());
}
}