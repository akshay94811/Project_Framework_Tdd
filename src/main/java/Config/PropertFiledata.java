package Config;



	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;

	public class PropertFiledata {
		Properties prop;
		String PropertyFilePath = "./src/main/java/Config/config.Properties";
		
		public PropertFiledata() throws IOException {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(PropertyFilePath);
			prop.load(ip);
		}
		
		public String GetPropertyString(String Key) {
			String property = prop.getProperty(Key);
			return property;
		}
		
		public boolean GetPropertyboolean(String Key) {
			String property = prop.getProperty(Key);
			boolean PropertyBoolean = Boolean.valueOf(property);
			return PropertyBoolean;
		}
	}


