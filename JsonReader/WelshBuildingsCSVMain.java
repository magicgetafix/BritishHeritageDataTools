package JsonReader;

import java.io.IOException;

public class WelshBuildingsCSVMain {
	
	
public static void main(String[] args) {
		
		WelshBuildingsReader reader = new WelshBuildingsReader();
		
		reader.getWelshListedBuildings
		("http://lle.gov.wales/catalogue/item/ListedBuildings.json", "C:\\EnglishHeritageData\\ListedBuildings\\WelshListedBuildings.csv");
		
	} 

}
