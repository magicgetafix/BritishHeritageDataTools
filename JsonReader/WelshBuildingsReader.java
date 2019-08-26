package JsonReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;

import main.GeoObject;

public class WelshBuildingsReader {
	
	public WelshBuildingsReader() {
		
	}
		
	/**Creates an Array list containing all Welsh listed buildings
	 * 	
	 * @param url requires this url http://lle.gov.wales/catalogue/item/ListedBuildings.json
	 * @return an ArrayList containing WelshListedBuildingsObject
	 */
	public void getWelshListedBuildings(String url, String filename) {
		
		final String TYPE = "LB";
		
		ArrayList<WelshListedBuildingsObject> welshListedBuildings = new ArrayList<>();
		
		try {
		
		URL	address = new URL(url);
		
		InputStreamReader reader = new InputStreamReader(address.openStream());
		
		WelshListedBuildings buildingsHolder = new Gson().fromJson(reader, WelshListedBuildings.class);
		System.out.println("Read listed buildings from JSON API");
		reader.close();
		
		
		
		while (buildingsHolder == null) {
			
			
				wait(500);
			
			System.out.println("Waiting for listed buildings list to load");
			
		
		}
		
		
		if (buildingsHolder!=null) {
			
			
			buildingsHolder.getFeatures().forEach(lb->{
				
				String id = null;
				String name = null;
				String type = TYPE;
				String grade = null;
				Double northing = 0.0;
				Double easting = 0.0;
				
				if (lb.getId()!=null) {
				
				id  = lb.getId();}
				
				if (lb.getProperties()!=null){
					
					if (lb.getProperties().getName()!=null) {
						
						name = lb.getProperties().getName();
				
					}
					
					if (lb.getProperties().getGrade()!=null) {
						
						grade = lb.getProperties().getGrade();
					}
					
				}
				
				
				
				if (lb.getGeometry().getCoordinates()!=null) {
					
					
				if (!lb.getGeometry().getCoordinates().isEmpty()&&lb.getGeometry().getCoordinates().size()>=1) {
				
				northing = lb.getGeometry().getCoordinates().get(0).get(0);
				
				easting = lb.getGeometry().getCoordinates().get(0).get(1);
				
				WelshListedBuildingsObject building = new WelshListedBuildingsObject(id, name, type, grade, northing, easting);
				welshListedBuildings.add(building);
				System.out.println("Added "+name);
				
				}
				
				
				
				}
					
				
				
			});
			
			ArrayList<String[]> data = new ArrayList<>();
			
			File file = new File(filename);
			
			FileWriter outputWriter = new FileWriter(file);
			
			CSVWriter writer = new CSVWriter(outputWriter);
			
			for (WelshListedBuildingsObject obj: welshListedBuildings) {
				
				String[] dataString = {(obj.getId()),obj.getType(), obj.getName(), 
						obj.getGrade(), Double.toString(obj.getNorthing()), Double.toString(obj.getEasting())};
				
				data.add(dataString);
				
				
			}
			
			writer.writeAll(data);
			
			System.out.println("Wrote data to CSV File");
			
			writer.close();
			
			
			
			
		}
		
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	
	
	}
	
	
		

	


}


