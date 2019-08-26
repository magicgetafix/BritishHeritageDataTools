package JsonReader;

/**A class to hold the relevant listed buildings information for Welsh Listed Buildings
 * 
 * @author Matthew Baker
 *
 */
public class WelshListedBuildingsObject {
	
	private String id;
	private String name;
	private String type;
	private String grade;
	private Double northing;
	private Double easting;
	
	
	
	public WelshListedBuildingsObject(String id, String name, String type, String grade, Double northing,
			Double easting) {
		super();
		this.id = id;
		
		String modName = name; 
		if (modName!=null) {
		
		while(modName.charAt(modName.length()-1) == ','&& modName.length()>1) {
			modName = modName.substring(0, modName.length()-1);
		}
		
		if (modName.charAt(0) == ',') {
			
			modName = modName.substring(1);
		}
		}
		this.name = modName;
		this.type = type;
		this.grade = grade;
		this.northing = northing;
		this.easting = easting;
	}



	public String getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public String getType() {
		return type;
	}



	public String getGrade() {
		return grade;
	}



	public Double getNorthing() {
		return northing;
	}



	public Double getEasting() {
		return easting;
	}
	
	
	
	
	

}
