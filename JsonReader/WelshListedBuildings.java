package JsonReader;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WelshListedBuildings {

@SerializedName("type")
@Expose
private String type;
@SerializedName("totalFeatures")
@Expose
private Integer totalFeatures;
@SerializedName("features")
@Expose
private List<Feature> features = null;

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public Integer getTotalFeatures() {
return totalFeatures;
}

public void setTotalFeatures(Integer totalFeatures) {
this.totalFeatures = totalFeatures;
}

public List<Feature> getFeatures() {
return features;
}

public void setFeatures(List<Feature> features) {
this.features = features;
}

}
