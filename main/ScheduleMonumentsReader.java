package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Matthew Baker
 * 
 * This is a class to extract the place name and location data from the Scheduled Monument dbf files. 
 * The data for Scheduled monuments of England can be downloaded from 
 * https://historicengland.org.uk/listing/the-list/data-downloads/
 * 
 * The data for Scheduled monuments of Scotland can be downloaded from
 * https://portal.historicenvironment.scot/spatialdownloads. I manually modified the CSV files to 
 * just include the data I needed as they included a lot more details than the data supplied
 * by Historic England
 * 
 * The dbf file first needs to be converted to a csv to be read by this class
 * 
 * CSV file address is currently "C:\\EnglishHeritageData\\ScheduledMonuments\\ScheduledMonuments.csv"
 * 
 * 
 * 
 *
 */
public class ScheduleMonumentsReader {
	
	public String csvFile;
	public final String TYPE = "SM";
	public static int englishCount = 0;
	public static int scottishCount = 0;
	
	public ScheduleMonumentsReader() {
		
		
		
	}
	

	/**This method creates the list of Scheduled Monuments from the English Scheduled Monuments CSV File data
	 * 
	 *  
	 * @return It returns an array list of type OrdnanceSurveyMonument
	 */
	public ArrayList<OrdnanceSurveyObject> createMonumentList(String csvFile){
		
		this.csvFile = csvFile;
		
		//pattern to check whether string contains a number
		Pattern numberPattern = Pattern.compile("[0-9]");
		
		ArrayList<OrdnanceSurveyObject> monumentList = new ArrayList<>();
		
		//to split the CSV file
		String splitter = ",";
		
		//holds a line of the CSV file 
		String csvLine;
		//holds the data parsed from each CSV line
		String[] csvCollection;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(this.csvFile));
			
			while ((csvLine = br.readLine())!=null) {
				
				csvCollection = csvLine.split(splitter);
				
				if (csvCollection.length >= 10) {
					
				String test = Character.toString(csvCollection[0].charAt(0));
				
				Matcher digitMatcher = numberPattern.matcher(test);
				
				if (digitMatcher.matches()) {
					
					if (csvCollection.length == 10) {
					
					String id = csvCollection[0];
				
					//for monument info
					String name = csvCollection[1];
				
					//for national grid coordinates
					String ngCo = csvCollection[5];
				
					double easting = Double.parseDouble(csvCollection[7]);
				
					double northing = Double.parseDouble(csvCollection[8]);
					
					
					OrdnanceSurveyObject mon = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
					
					monumentList.add(mon);
					englishCount++;
					
					}
					
					if (csvCollection.length == 11) {
						
						String id = csvCollection[0];
					
						//for monument info
						String name = csvCollection[1]+";"+csvCollection[2];
					
						//for national grid coordinates
						String ngCo = csvCollection[6];
					
						double easting = Double.parseDouble(csvCollection[8]);
					
						double northing = Double.parseDouble(csvCollection[9]);
						
						
						OrdnanceSurveyObject mon = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
						
						monumentList.add(mon);
						englishCount++;
						
						}
					
					if (csvCollection.length == 12) {
						
						String id = csvCollection[0];
					
						//for monument info
						String name = csvCollection[1]+";"+csvCollection[2]+";"+csvCollection[3];
					
						//for national grid coordinates
						String ngCo = csvCollection[7];
					
						double easting = Double.parseDouble(csvCollection[9]);
					
						double northing = Double.parseDouble(csvCollection[10]);
						
						
						OrdnanceSurveyObject mon = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
						
						monumentList.add(mon);
						englishCount++;
						
						}
					
					if (csvCollection.length == 13) {
						
						String id = csvCollection[0];
					
						//for monument info
						String name = csvCollection[1]+";"+csvCollection[2]+";"+csvCollection[3]+";"+csvCollection[4];
					
						//for national grid coordinates
						String ngCo = csvCollection[8];
					
						double easting = Double.parseDouble(csvCollection[10]);
					
						double northing = Double.parseDouble(csvCollection[11]);
						
						
						OrdnanceSurveyObject mon = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
						
						monumentList.add(mon);
						englishCount++;
						
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

		System.out.println("Found "+englishCount+" sites in England");
		return monumentList;
		
	}
	

	
	/**This method creates the list of Scheduled Monuments from the my modified Scottish Scheduled Monuments CSV File data
	 * 
	 *  
	 * @return It returns an array list of type OrdnanceSurveyMonument
	 */	
	
public ArrayList<OrdnanceSurveyObject> createScottishMonumentList(String csvFile){
		
		this.csvFile = csvFile;
		
		//pattern to check whether string contains a number
		Pattern numberPattern = Pattern.compile("[0-9]");
		
		ArrayList<OrdnanceSurveyObject> monumentList = new ArrayList<>();
		
		//to split the CSV file
		String splitter = ",";
		
		//holds a line of the CSV file 
		String csvLine;
		//holds the data parsed from each CSV line
		String[] csvCollection;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(this.csvFile));
			
			while ((csvLine = br.readLine())!=null) {
				
				csvCollection = csvLine.split(splitter);
				
				if (csvCollection.length >= 5) {
					
				String test = Character.toString(csvCollection[0].charAt(0));
				
				
				if (!test.equals("D")) {
					
					if (csvCollection.length == 5) {
					
					String id = csvCollection[0];
				
					//for monument info
					String name = csvCollection[1];
				
					String ngCo = null;
					
					double easting = Double.parseDouble(csvCollection[2]);
				
					double northing = Double.parseDouble(csvCollection[3]);
					
					
					OrdnanceSurveyObject mon = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
					
					monumentList.add(mon);
					scottishCount++;
					
					}
					
					if (csvCollection.length == 6) {
						
						String id = csvCollection[0];
						
						String endWord = csvCollection[2];
						
						if (endWord.length()>2) {
						
						endWord = endWord.substring(endWord.length()-2);
						}
						
						String name = null;
						
						if (!endWord.equals("of")) {
						name = csvCollection[1]+"; "+csvCollection[2];}
						
						else {
						
						name =  csvCollection[2]+" "+csvCollection[1];	
						}
					
						String ngCo = null;
						
						double easting = Double.parseDouble(csvCollection[3]);
					
						double northing = Double.parseDouble(csvCollection[4]);
						
						
						OrdnanceSurveyObject mon = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
						
						monumentList.add(mon);
						scottishCount++;
						
						}
					
						if (csvCollection.length == 7) {
						
						String id = csvCollection[0];
						
						String name = csvCollection[1]+"; "+csvCollection[2]+"; "+csvCollection[3];
					
						String ngCo = null;
						
						double easting = Double.parseDouble(csvCollection[4]);
					
						double northing = Double.parseDouble(csvCollection[5]);
						
						
						OrdnanceSurveyObject mon = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
						
						monumentList.add(mon);
						scottishCount++;
						
						}
						
						if (csvCollection.length == 8) {
							
							String id = csvCollection[0];
						
							//for monument info
							String name = csvCollection[1]+"; "+csvCollection[2]+"; "+csvCollection[3]+";"+csvCollection[4];
						
							String ngCo = null;
							
							double easting = Double.parseDouble(csvCollection[5]);
						
							double northing = Double.parseDouble(csvCollection[6]);
							
							
							OrdnanceSurveyObject mon = new OrdnanceSurveyObject(id, TYPE, name,ngCo, easting, northing);
							
							monumentList.add(mon);
							scottishCount++;
							
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
		
		
		System.out.println("Found "+scottishCount+" sites in Scotland");
		return monumentList;
		
	}



}
