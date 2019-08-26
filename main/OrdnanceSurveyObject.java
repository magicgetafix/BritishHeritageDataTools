package main;

public class OrdnanceSurveyObject {
	
	
	private String id;
	private String type;
	private String name;
	private String nationalGridCoord;
	private double eastingCoord;
	private double northingCoord;
	
	
	
	public OrdnanceSurveyObject(String id, String type, String name, String nationalGridCoord, double eastingCoord,
			double northingCoord) {
		
		this.id = id;
		this.type = type;
		this.name = FormatTools.sentenceToProperCase(name);
		this.nationalGridCoord = nationalGridCoord;
		this.eastingCoord = eastingCoord;
		this.northingCoord = northingCoord;
	}



	public String getId() {
		return id;
	}


	public String getType() {
		return type;
	}


	public String getName() {
		return name;
	}



	public String getNationalGridCoord() {
		return nationalGridCoord;
	}



	public double getEastingCoord() {
		return eastingCoord;
	}



	public double getNorthingCoord() {
		return northingCoord;
	}
	
	
	
	

}
