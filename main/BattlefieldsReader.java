package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BattlefieldsReader {
	
	public String csvFile;
	public final String TYPE = "BF";
	public static int englishCount = 0;
	public static int scottishCount = 0;
	public static int welshCount = 0;
	
	public BattlefieldsReader() {
		
		
	}
	
	
	public List<OrdnanceSurveyObject> getEnglishBattlefields(String csvFile){
		
		this.csvFile = csvFile;
		
		String csvLine = "";
		
		String[] csvCollection;
		
		String splitter = ",";
		
		List<OrdnanceSurveyObject> battleList = new ArrayList<>();
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.csvFile));
			
			while ((csvLine = br.readLine())!=null) {
				
				csvCollection = csvLine.split(splitter);
				
				if (csvCollection.length == 10) {
					
					
					String id = csvCollection[0];
					
					String name = csvCollection[1];
					
					String nationalGridCoord = csvCollection[5];
					
					String type = TYPE;
					
					String eastingLine = csvCollection[7];
					
					String northingLine = csvCollection[8];
					
					Pattern doublePattern = Pattern.compile("^[0-9]+\\.[0-9]*$");
					
					Matcher northingMatcher = doublePattern.matcher(northingLine);
					
					Matcher eastingMatcher = doublePattern.matcher(eastingLine);
					
					if (eastingLine!=null && northingLine!=null) {
					
					if (northingMatcher.matches()&& eastingMatcher.matches()) {
						
						Double northing = Double.parseDouble(northingLine);
						
						Double easting = Double.parseDouble(eastingLine);
						
						
						OrdnanceSurveyObject battlefieldObj 
						= new OrdnanceSurveyObject(id, TYPE, name, nationalGridCoord, easting, northing);
						
						battleList.add(battlefieldObj);
						englishCount++;
						
					}
					
					else {
						
						System.out.println("Incorrect northing and easting format for "+name+".");
					}
					
				}
				
			}
			
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Found "+englishCount+" English battlefields");
		return battleList;
		
		}
	
	
		
	
		public List<OrdnanceSurveyObject> getScottishBattlefields(String csvFile){
		
		
		//TODO 
		List<OrdnanceSurveyObject> scottishBattleList = new ArrayList<>();
		
		this.csvFile = csvFile;
		
		String csvLine = "";
		
		String[] csvCollections;
		
		String splitter = ",";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.csvFile));
			
			
		while ((csvLine = br.readLine())!=null) {
			
			
			csvCollections = csvLine.split(splitter);
			
			if (csvCollections.length == 20) {
				
			
				String id = csvCollections[8];
				
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
					
					scottishBattleList.add(obj);
					scottishCount++;
				
					
				}
				
				else {
					
					System.out.println("Failed to parse northings and eastings for "+name+".");
				}
				
				
			}
			
		
		}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Found "+scottishCount+" Scottish battlefieds");
		return scottishBattleList;
		
	}
	
	

}
