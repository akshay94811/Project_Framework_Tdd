package Utils;

	

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Base64;
	import org.apache.commons.io.FileUtils;
	import org.apache.commons.io.IOUtils;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;

import Config.PropertFiledata;

	public class Atachscreebshots {
		
		
		public String getScreenshotAsBase64(WebDriver driver, String Name) throws IOException
	    {
			PropertFiledata pf = new PropertFiledata();
			File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			System.out.println("Successfully Captured Screenshot");
			String DestPath = pf.GetPropertyString("ScreenshotPath")+"/"+Name+"-"+System.currentTimeMillis()+".png";
			FileUtils.copyFile(source, new File(DestPath));
			System.out.println("Successfully Moved the ScreenShot to the Destination Location");
		    byte[] imagebyte = IOUtils.toByteArray(new FileInputStream(DestPath));
		    String base64 = Base64.getEncoder().encodeToString(imagebyte);
		    System.out.println("Successfully Encoded the Screen Shot");
		    return base64;
		}

	}


