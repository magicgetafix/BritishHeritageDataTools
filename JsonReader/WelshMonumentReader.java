package JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import main.OrdnanceSurveyObject;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
/**
 * 
 * @author Matthew Baker
 * 
 * Historic Wales does not provide easily accessible datasets in CSV format. They also claim to not have
 * the data when you email them. However, after some digging around I found that JSON files were accessible online
 * which are used for the backend of this site: https://cadw.gov.wales/historicenvironment/recordsv1/cof-cymru/?skip=1&lang=en
 * and are hosted at http://lle.gov.wales/catalogue/item/ScheduledMonuments.json
 *
 */
public class WelshMonumentReader {

	public static int count = 0;
	
	public WelshMonumentReader() {
		
		
	}
	
	public ArrayList<OrdnanceSurveyObject> getWelshMonuments(String url) throws IOException{
		
		ArrayList<OrdnanceSurveyObject> osObjectList = new ArrayList<>();
	
		URL address = new URL(url);
		InputStreamReader reader = new InputStreamReader(address.openStream());
		
		WelshMonumentArrayHolder monumentsHolder = new Gson().fromJson(reader, WelshMonumentArrayHolder.class);
		
		final String TYPE = "SM";
		
		
		
		while (monumentsHolder == null) {
			
			try {
				wait(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Waiting for monument list to load");
		}
		
		
		if (monumentsHolder!=null) {
			
			
			monumentsHolder.getFeatures().forEach(m->{
					
					String id = null;
					if (m.getId()!=null) {
					id = m.getId();}
					
					String type = "SM";
					
					if (m.getProperties()!=null) {
						
						if (m.getProperties().getNorthing()!=null&&m.getProperties().getEasting()!=null) {
					
					String name = m.getProperties().getName();
					
					String nationGridCoord = "Not available";
					
					double northing = (double) m.getProperties().getNorthing();
					
					double easting = (double) m.getProperties().getEasting();
					
					OrdnanceSurveyObject obj = new OrdnanceSurveyObject(id, type, name, nationGridCoord,
							easting, northing);
					
					osObjectList.add(obj);
					
					count++;
					}
					}
		
		});
		
		}
		
		System.out.println("Found "+count+" monuments in Wales");
		return osObjectList;
		
	}
	

}
