package POM_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Login_page {

	WebDriver driver;

	public Login_page(WebDriver driver) {
		this.driver=driver;
		
		//PageFactory.initElements(driver, this);
	}
	    @FindBy(how=How.XPATH,using="")
	     private WebElement ele;

	     public WebElement ele() {
		return ele;
	}
		@FindBy(how=How.XPATH,using="//div[@class='IiD88i _351hSN']//input[@ type='text']")
		private WebElement User_name;
		
		public WebElement User_name() {
			return User_name;
		}
		
		@FindBy(how=How.XPATH,using="//div[@class='IiD88i _351hSN']//input[@ type='password']")
		private WebElement Password;
		
		public WebElement Password() {
			return Password;
			
		}
		
		@FindBy(how=How.XPATH,using="//div[@ class='_1D1L_j']//button[@type=\"submit\"]")
		private WebElement Login;
		
		public WebElement Login() {
			return Login;
		}
}
