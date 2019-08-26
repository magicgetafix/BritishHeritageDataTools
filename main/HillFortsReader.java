package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HillFortsReader {
	
	
	public String csvFile;
	public static int hillfortCount = 0;
	
	public HillFortsReader() {
		
		
		
		
	}
	
	public List<GeoObject> getHillforts(String csvFile){
		
		
		this.csvFile = csvFile;
		
		Pattern innerCommas = Pattern.compile(", ");
		
		Pattern numberCommas = Pattern.compile("\\d\\d\\d,\\d\\d\\d");
		
		String splitter = ",";
		
		String[] internalCommasRemoved;
		
		String[] csvCollection;
		
		String csvLine;
		
		String newCSVLine;
		
		List<GeoObject> hillfortList = new ArrayList<>();
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(this.csvFile));
			
			
			
			
			while ((csvLine = br.readLine())!=null) {
				
				internalCommasRemoved = innerCommas.split(csvLine);
				
				newCSVLine = "";
				
				for (int i = 0; i < internalCommasRemoved.length; i++) {
					
					if (i!= internalCommasRemoved.length-1) {
						
					newCSVLine += internalCommasRemoved[i]+"; ";
					
					}
					
					else {
						
						newCSVLine+= internalCommasRemoved[i];
					}
					
				}
			
				csvCollection = newCSVLine.split(splitter);
				
				if (csvCollection.length == 5) {
					
					
					String id = "HF"+csvCollection[0];
					
					String name = csvCollection[1];
				
				
					
					String longCoord = csvCollection[2];
				
					
					String latCoord = csvCollection[3];
			
					
					String websiteUrl = csvCollection[4];
		
					
					Boolean websiteIsValid = true;
					
					if (!websiteUrl.startsWith("http://")) {
						
						System.out.println("Incorrect website format");
						websiteIsValid = false;
					}
					
					
					
					
					
					if (websiteIsValid) {
						
						Double longitude = Double.parseDouble(longCoord);
						
						Double latitude = Double.parseDouble(latCoord);
						
						String geoHash = GeoHash.GeoHash.encodeHash(latitude, longitude);
						
						
						GeoObject hillfortObject 
						= new GeoObject(id, "HF", name, latitude, longitude, geoHash);
						
						hillfortList.add(hillfortObject);
						hillfortCount++;
						
					}
					
					else {
						System.out.println("Latitude and Longitude aren't in the correct format for "+name+".");
					}
					
					}
					
					
				}
				
			
			br.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Found "+hillfortCount+" Hillforts");
		return hillfortList;
		
	}
	
	
	
	
	
	
	

}
