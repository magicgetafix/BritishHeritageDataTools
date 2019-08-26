package JsonReader;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WelshMonGeometry {

@SerializedName("type")
@Expose
private String type;
@SerializedName("coordinates")
@Expose
private List<List<Integer>> coordinates = null;

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public List<List<Integer>> getCoordinates() {
return coordinates;
}

public void setCoordinates(List<List<Integer>> coordinates) {
this.coordinates = coordinates;
}

}
