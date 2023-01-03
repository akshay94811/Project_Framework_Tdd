package genral;

public class TestcaseCategories {

	public String CatgClassName(String ClassName) {
		String Class = "Generic Module";
		// **** Catg*****//
		if (ClassName.equals("com.Eyelit.qa.scripts.HomeScript")) {
			System.out.println("In HomeScript");
			Class = "HomeScript Script";
		}
		
		
		
		return Class;
	}
}
