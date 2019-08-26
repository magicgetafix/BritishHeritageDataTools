package main;
import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import com.opencsv.CSVWriter;

/**
 * 
 * @author Matthew
 * 
 * This class holds the methods which help transform the data obtained from the historic england's 
 * datasets, already converted into CSV files, into data which can be plotted on GoogleMaps. 
 *
 */

public class OStoGPSDataTransformer {
	
	
	public static HashMap<String, ArrayList> heritageHashMap = new HashMap<>();
	public static ArrayList<Landmark> heritageList = new ArrayList<>();
	public static int idCounter = 0;
	public static int listCounter = 0;
	
	public OStoGPSDataTransformer() {
		
		
	}
	
	/**This method takes a list of OrdnanceSurveyObjects and converts their National Grid 
	 * coordinates to GPS coordinates and a GeoHash
	 * 
	 * @param an ArrayList of OrdnanceSurveyObject
	 * @return an ArrayList containing GeoObjects
	 */
	public ArrayList<GeoObject> convertList(Collection<? extends OrdnanceSurveyObject> list){
		
		
		ArrayList<GeoObject> objectList = new ArrayList<>();
		
		for (OrdnanceSurveyObject oS: list) {
			
			
			String id = oS.getId();
			String info = oS.getName();
			String type = oS.getType();
			
			LatLon latLon = OSCoordinatesConverter.convertToLatLon(oS.getEastingCoord(), oS.getNorthingCoord());
			
			String geoHash = GeoHash.GeoHash.encodeHash(latLon.Latitude, latLon.Longitude);
			
			GeoObject monument = new GeoObject(id, type, info, latLon.Latitude, latLon.Longitude, geoHash);
			
			objectList.add(monument);
			
			
		}
		
		return objectList;
		
	}
	
	/**This method takes the data from a list of GeoObjects and writes it to a CSV file separated by
	 * one comma
	 * 
	 * @param filename The file you wish to save the CSV file in
	 * @param objectList a list of GeoObject
	 * @throws IOException
	 */
	public void convertListToCSV(String filename, ArrayList<GeoObject> objectList) throws IOException {
		
		listCounter++;
		String type = "";
		ArrayList<String[]> data = new ArrayList<>();
		
		File file = new File(filename);
		
		FileWriter outputWriter = new FileWriter(file);
		
		CSVWriter writer = new CSVWriter(outputWriter);
		
		ArrayList<Landmark> landmarkList = new ArrayList<>();
		
		for (GeoObject obj: objectList) {
			
			
			String[] dataString = {obj.getId(), obj.getInfo(),
					Double.toString(obj.getLatitude()), Double.toString(obj.getLongitude()),
					obj.getGeoHash(), obj.getType()};
			
			data.add(dataString);
			
			Landmark landmark = new Landmark();
			landmark.id = ""+idCounter;
			landmark.name = obj.getInfo();
			landmark.type = obj.getType();
			type = obj.getType();
			landmark.latitude = obj.getLatitude();
			landmark.longitude = obj.getLongitude();
			landmarkList.add(landmark);
			idCounter++;
			
		}
		
		//String[] nextLine = {"_ID", "NAME", "LATITUDE", "LONGITUDE", "GEOHASH", "TYPE"};
		
		//writer.writeNext(nextLine);
		
		//writer.writeAll(data);
		
		//System.out.println("Wrote data to CSV File");
		
		//writer.close();
		
		Gson landmarkGson = new Gson();
		heritageList.addAll(landmarkList);
		if (listCounter == 5) {
			HashMap<String, ArrayList<Landmark>> dataHashMap = new HashMap<>();
			dataHashMap.put("landmarks", heritageList);
			file = new File("C:\\EnglishHeritageData\\heritage_data.json");
			outputWriter = new FileWriter(file);
			String landmarkListJson = landmarkGson.toJson(dataHashMap);
			BufferedWriter bufferedWriter = new BufferedWriter(outputWriter);
			bufferedWriter.write(landmarkListJson);
			bufferedWriter.flush();
			bufferedWriter.close();
			System.out.println("Wrote data to Json File");
		}
		
		
		
		
	}
	
	

}
