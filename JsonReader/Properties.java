package JsonReader;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {

@SerializedName("RecordNumber")
@Expose
private Integer recordNumber;
@SerializedName("Name")
@Expose
private String name;
@SerializedName("Name_cy")
@Expose
private Object nameCy;
@SerializedName("DesignationDate")
@Expose
private String designationDate;
@SerializedName("Grade")
@Expose
private String grade;
@SerializedName("Location")
@Expose
private String location;
@SerializedName("BroadClass")
@Expose
private String broadClass;
@SerializedName("BroadClass_cy")
@Expose
private String broadClassCy;
@SerializedName("Report")
@Expose
private String report;

public Integer getRecordNumber() {
return recordNumber;
}

public void setRecordNumber(Integer recordNumber) {
this.recordNumber = recordNumber;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Object getNameCy() {
return nameCy;
}

public void setNameCy(Object nameCy) {
this.nameCy = nameCy;
}

public String getDesignationDate() {
return designationDate;
}

public void setDesignationDate(String designationDate) {
this.designationDate = designationDate;
}

public String getGrade() {
return grade;
}

public void setGrade(String grade) {
this.grade = grade;
}

public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

public String getBroadClass() {
return broadClass;
}

public void setBroadClass(String broadClass) {
this.broadClass = broadClass;
}

public String getBroadClassCy() {
return broadClassCy;
}

public void setBroadClassCy(String broadClassCy) {
this.broadClassCy = broadClassCy;
}

public String getReport() {
return report;
}

public void setReport(String report) {
this.report = report;
}

}
