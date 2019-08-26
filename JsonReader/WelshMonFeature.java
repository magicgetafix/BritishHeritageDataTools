package JsonReader;




import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WelshMonFeature {

/*@SerializedName("type")
@Expose
private String type;*/
@SerializedName("id")
@Expose
private String id;/*
@SerializedName("geometry")
@Expose
private WelshMonGeometry welshMonGeometry;
@SerializedName("geometry_name")
@Expose
private String geometryName;*/
@SerializedName("properties")
@Expose
private WelshMonProperties welshMonProperties;

/*public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}
*/
public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}
/*
public WelshMonGeometry getGeometry() {
return welshMonGeometry;
}

public void setGeometry(WelshMonGeometry welshMonGeometry) {
this.welshMonGeometry = welshMonGeometry;
}

public String getGeometryName() {
return geometryName;
}

public void setGeometryName(String geometryName) {
this.geometryName = geometryName;
}*/

public WelshMonProperties getProperties() {
return welshMonProperties;
}

public void setProperties(WelshMonProperties welshMonProperties) {
this.welshMonProperties = welshMonProperties;
}

}
