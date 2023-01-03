package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TestUtil {


	WebDriver driver;

	public TestUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void JsExecutorClick(WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ele);
	}

	public void ScrollToElement(WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ele);
	}
    
	public void DragDrop(WebElement Source, WebElement Destination) {
		Actions act = new Actions(driver);
		act.dragAndDrop(Source, Destination).build().perform();
	}
	
	public void DragDropBy(WebElement Source, int x, int y) {
		Actions act = new Actions(driver);
		act.dragAndDropBy(Source, x, y).build().perform();
	}
	
	public void SelectByIndex(WebElement ele,int index) {
		Select s=new Select(ele);
		s.selectByIndex(index);
	}
	public void SelectByValue(WebElement ele,String Value) {
		Select s=new Select(ele);
		s.selectByValue(Value);
}
}
