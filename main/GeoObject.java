package main;
/**
 * 
 * @author Matthew Baker
 * 
 * This object stores the information associated with the geographical object
 * as well as an object id and the latitude, longitude and geohash associated with 
 * the location
 *
 */
public class GeoObject {
	
	private String id;
	private String type;
	private String info;
	private double latitude;
	private double longitude;
	private String geoHash;
	
	/**
	 * 
	 * @param id type String
	 * @param info type String
	 * @param latitude type double
	 * @param longitude type double
	 * @param geoHash type String
	 */
	public GeoObject(String id, String type, String info, double latitude, double longitude, String geoHash) {
	
		this.id = id;
		this.type = type;
		this.info = info;
		this.latitude = latitude;
		this.longitude = longitude;
		this.geoHash = geoHash;
	}


	public String getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}


	public String getInfo() {
		return info;
	}


	public double getLatitude() {
		return latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public String getGeoHash() {
		return geoHash;
	}
	
	
	
	
	
	
	

}
