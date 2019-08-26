package main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import JsonReader.WelshMonumentReader;

public class CSVMain {

	public static void main(String[] args) {
		
		System.out.println("Started main");
		
		ScheduleMonumentsReader monReader = new ScheduleMonumentsReader();
		
		System.out.println("Created monReader");
		
		ArrayList<OrdnanceSurveyObject> OSObjectList = monReader.createMonumentList("C:\\EnglishHeritageData\\ScheduledMonuments\\ScheduledMonuments.csv");
		System.out.println("Scanned English monuments");
		
		ArrayList<OrdnanceSurveyObject> scotOSObjectList = monReader.createScottishMonumentList("C:\\EnglishHeritageData\\ScheduledMonuments\\ScottishMonumentsCoreData.csv");
		System.out.println("Scanned Scottish monuments");
		
		OSObjectList.addAll(scotOSObjectList);
		
		WelshMonumentReader welshMonReader = new WelshMonumentReader();
		
		try {
			
			ArrayList<OrdnanceSurveyObject> welshOSObjectList = welshMonReader.getWelshMonuments("http://lle.gov.wales/catalogue/item/ScheduledMonuments.json");
			System.out.println("Scanned Welsh monuments");
			OSObjectList.addAll(welshOSObjectList);
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Unable to get data from Welsh JSON address");
			e1.printStackTrace();
		}
		
		System.out.println("Created OSObjectList");
		
		OStoGPSDataTransformer listTransformer = new OStoGPSDataTransformer();
		
		System.out.println("Created transformer");
		
		ArrayList<GeoObject> geoObjectList = listTransformer.convertList(OSObjectList);
				
		
		try {
			
			listTransformer.convertListToCSV("C:\\EnglishHeritageData\\ScheduledMonuments\\scheduled_monuments_data.csv", geoObjectList);
			System.out.println("Converted Data to Scheduled Monuments CSV file");
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO Error");
			e.printStackTrace();
		}
		
		
		ListedBuildingsReader listedBuildingsReader = new ListedBuildingsReader();
		
		System.out.println("Created listed buildings reader");
		
		ArrayList<OrdnanceSurveyObject> listedBuildingsList = listedBuildingsReader.getEnglishBuildingList("C:\\EnglishHeritageData\\ListedBuildings\\ListedBuildings.csv");
		
		System.out.println("Scanned English Listed Buildings");

		
		ArrayList<OrdnanceSurveyObject> scotLBList = listedBuildingsReader.createScottishBuildingList("C:\\EnglishHeritageData\\ListedBuildings\\SimpleScottishListedBuildings.csv");
		
		System.out.println("Scanned Scottish Listed Buildings");
		
		ArrayList<OrdnanceSurveyObject> welshLBList = listedBuildingsReader.createWelshBuildingList("C:\\EnglishHeritageData\\ListedBuildings\\WelshListedBuildings.csv");

		System.out.println("Scanned Welsh Listed Buildings");
		
		listedBuildingsList.addAll(scotLBList);
		
		listedBuildingsList.addAll(welshLBList);
		
		
		ArrayList<GeoObject> buildingObjectList = new ArrayList<>();
		
		if (!listedBuildingsList.isEmpty()) {
		
		buildingObjectList = listTransformer.convertList(listedBuildingsList);
		
		}
		
		try {
			listTransformer.convertListToCSV("C:\\EnglishHeritageData\\ListedBuildings\\listed_buildings_data.csv", buildingObjectList);
			System.out.println("Converted Data to Parks And Gardens CSV file");
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Writing parks and gardens list failed");
			e.printStackTrace();
		}
		
		
		//For Parks and Gardens
		
		ParksAndGardensReader pgReader = new ParksAndGardensReader();
		
		System.out.println("Created parks and gardens reader");
		
		List<OrdnanceSurveyObject> gardensList = pgReader.getEnglishParksAndGardens("C:\\EnglishHeritageData\\ParksAndGardens\\ParksAndGardens.csv");
		
		System.out.println("Scanned English Parks And Gardens");

		
		List<OrdnanceSurveyObject> scotPGList = pgReader.getScottishGardens("C:\\EnglishHeritageData\\ParksAndGardens\\ScottishGardensAndLandscapes.csv");
		
		System.out.println("Scanned Scottish Parks And Gardens");

		
		gardensList.addAll(scotPGList);
		
		System.out.println("Added all buildings to list");
		
		listTransformer = new OStoGPSDataTransformer();
		
		ArrayList<GeoObject> parkGeoObjectList = new ArrayList<>();
		
		if (!gardensList.isEmpty()) {
		
		parkGeoObjectList = listTransformer.convertList(gardensList);
		
		}
		
		try {
			listTransformer.convertListToCSV("C:\\EnglishHeritageData\\ParksAndGardens\\parks_and_gardens_data.csv", parkGeoObjectList);
			System.out.println("Converted Data to Parks And Gardens CSV file");
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Writing parks and gardens list failed");
			e.printStackTrace();
		}
		
		
		//For battlefields
		
		BattlefieldsReader battReader = new BattlefieldsReader();
		
		System.out.println("Created battlefields reader");
		
		List<OrdnanceSurveyObject> battlesList = battReader.getEnglishBattlefields("C:\\EnglishHeritageData\\Battlefields\\EnglishBattlefields.csv");
		
		System.out.println("Scanned Battlefields");

		
		List<OrdnanceSurveyObject> scotBatList = battReader.getScottishBattlefields("C:\\EnglishHeritageData\\Battlefields\\ScottishBattlefields.csv");
		
		System.out.println("Scanned Scottish Battlefields");

		
		battlesList.addAll(scotBatList);
		
		System.out.println("Added all battlefields to list");
		
		
		ArrayList<GeoObject> battlesArrayList = new ArrayList<>();
		System.out.println("length of list = "+ battlesList.size());
		
		if (!battlesList.isEmpty()) {
			battlesArrayList = listTransformer.convertList(battlesList);
		}
		
		try {
			listTransformer.convertListToCSV("C:\\EnglishHeritageData\\Battlefields\\battlefields_data.csv", battlesArrayList);
			System.out.println("Converted Data to Battlefields CSV file");
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Writing battlefields list failed");
			e.printStackTrace();
		}
		
		
		//OStoGPSDataTransformer hillListTransformer = new OStoGPSDataTransformer();
		
		HillFortsReader hillFortReader = new HillFortsReader();
		
		List<GeoObject> hillFortList = hillFortReader.getHillforts("C:\\EnglishHeritageData\\ScheduledMonuments\\NewHillfortsFile.csv");
		ArrayList<GeoObject> hillFortArrayList = new ArrayList<>(hillFortList);
		
		
		try {
			
			listTransformer.convertListToCSV("C:\\EnglishHeritageData\\ScheduledMonuments\\hillforts_data.csv", hillFortArrayList);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
