package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParksAndGardensReader {
	
	public String csvFile;
	public final String TYPE = "PG";
	public static int englishCount = 0;
	public static int scottishCount = 0;
	public static int welshCount = 0;
	
	public ParksAndGardensReader() {
		
		
		
		
	}
	
	public List<OrdnanceSurveyObject> getEnglishParksAndGardens(String csvFile){
		
		
		this.csvFile = csvFile;
		
		Pattern innerCommas = Pattern.compile(", ");
		
		String splitter = ",";
		
		String[] internalCommasRemoved;
		
		String[] csvCollection;
		
		String csvLine;
		
		String newCSVLine;
		
		List<OrdnanceSurveyObject> gardenList = new ArrayList<>();
		
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
				
				if (csvCollection.length == 11) {
					
					
					String id = csvCollection[0];
					
					String name = csvCollection[1];
					
					String nationalGridCoord = csvCollection[6];
					
					String eastingLine = csvCollection[8];
					
					String northingLine = csvCollection[9];
					
					
					Pattern doublePattern = Pattern.compile("^[0-9]+\\.[0-9]*$");
					
					Matcher northingMatcher = doublePattern.matcher(northingLine);
					
					Matcher eastingMatcher = doublePattern.matcher(eastingLine);
					
					if (eastingLine!=null && northingLine!=null) {
					
					if (northingMatcher.matches()&& eastingMatcher.matches()) {
						
						Double northing = Double.parseDouble(northingLine);
						
						Double easting = Double.parseDouble(eastingLine);
						
						
						OrdnanceSurveyObject parkObject 
						= new OrdnanceSurveyObject(id, TYPE, name, nationalGridCoord, easting, northing);
						
						gardenList.add(parkObject);
						englishCount++;
						
					}
					
					else {
						System.out.println("Northings and Eastings aren't in the correct format for "+name+".");
					}
					
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
		
		
		System.out.println("Found "+englishCount+" Parks and Gardens in England");
		return gardenList;
		
	}
	
	
	public List<OrdnanceSurveyObject> getScottishGardens(String csvFile){
		
		
		List<OrdnanceSurveyObject> scottishGardenList = new ArrayList<>();
		
		this.csvFile = csvFile;
		
		String csvLine = "";
		
		String[] csvCollections;
		
		String splitter = ",";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.csvFile));
			
			
		while ((csvLine = br.readLine())!=null) {
			
			
			csvCollections = csvLine.split(splitter);
			
			if (csvCollections.length == 20) {
				
			
				String id = "SG"+scottishCount;
				
				String name = csvCollections[9];
				
				String eastingLine = csvCollections[6];
				
				String northingLine = csvCollections[7];
				
				Pattern numberPattern = Pattern.compile("^[0-9]+$");
				
				Matcher eastMatcher = numberPattern.matcher(eastingLine);
				
				Matcher northMatcher = numberPattern.matcher(northingLine);
				
				if (eastMatcher.matches()&& northMatcher.matches()) {
					
					Double easting = Double.parseDouble(eastingLine);
					
					Double northing = Double.parseDouble(northingLine);
					
					OrdnanceSurveyObject obj = new OrdnanceSurveyObject(id, TYPE, name, null, easting, northing);
					
					scottishGardenList.add(obj);
					scottishCount++;
				
					
				}
				
				else {
					
					System.out.println("Failed to parse northings and eastings for "+name+".");
				}
				
				
			}
			
		
			
		
		}
		
		br.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Found "+scottishCount+" Scottish Parks and Gardens");
		return scottishGardenList;
		
	}
	
	

}
