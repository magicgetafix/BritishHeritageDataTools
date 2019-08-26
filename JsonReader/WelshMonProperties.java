package JsonReader;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WelshMonProperties {

/*@SerializedName("RecordNumber")
@Expose
private Integer recordNumber;
@SerializedName("SAMNumber")
@Expose
private String sAMNumber;*/
@SerializedName("Name")
@Expose
private String name;
@SerializedName("Name_cy")
@Expose
private String nameCy;/*
@SerializedName("DesignationDate")
@Expose
private String designationDate;
@SerializedName("BroadClass")
@Expose
private String broadClass;
@SerializedName("BroadClass_cy")
@Expose
private String broadClassCy;
@SerializedName("Period")
@Expose
private String period;
@SerializedName("Period_cy")
@Expose
private String periodCy;
@SerializedName("SiteType")
@Expose
private String siteType;
@SerializedName("SiteType_cy")
@Expose
private String siteTypeCy;
@SerializedName("UnitaryAuthority")
@Expose
private String unitaryAuthority;
@SerializedName("UnitaryAuthority_cy")
@Expose
private String unitaryAuthorityCy;
@SerializedName("Community")
@Expose
private String community;
@SerializedName("Report")
@Expose
private String report;*/
@SerializedName("easting")
@Expose
private Integer easting;
@SerializedName("northing")
@Expose
private Integer northing;

/*public Integer getRecordNumber() {
return recordNumber;
}

public void setRecordNumber(Integer recordNumber) {
this.recordNumber = recordNumber;
}

public String getSAMNumber() {
return sAMNumber;
}

public void setSAMNumber(String sAMNumber) {
this.sAMNumber = sAMNumber;
}
*/
public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getNameCy() {
return nameCy;
}

public void setNameCy(String nameCy) {
this.nameCy = nameCy;
}
/*
public String getDesignationDate() {
return designationDate;
}

public void setDesignationDate(String designationDate) {
this.designationDate = designationDate;
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

public String getPeriod() {
return period;
}

public void setPeriod(String period) {
this.period = period;
}

public String getPeriodCy() {
return periodCy;
}

public void setPeriodCy(String periodCy) {
this.periodCy = periodCy;
}

public String getSiteType() {
return siteType;
}

public void setSiteType(String siteType) {
this.siteType = siteType;
}

public String getSiteTypeCy() {
return siteTypeCy;
}

public void setSiteTypeCy(String siteTypeCy) {
this.siteTypeCy = siteTypeCy;
}

public String getUnitaryAuthority() {
return unitaryAuthority;
}

public void setUnitaryAuthority(String unitaryAuthority) {
this.unitaryAuthority = unitaryAuthority;
}

public String getUnitaryAuthorityCy() {
return unitaryAuthorityCy;
}

public void setUnitaryAuthorityCy(String unitaryAuthorityCy) {
this.unitaryAuthorityCy = unitaryAuthorityCy;
}

public String getCommunity() {
return community;
}

public void setCommunity(String community) {
this.community = community;
}

public String getReport() {
return report;
}

public void setReport(String report) {
this.report = report;
}*/

public Integer getEasting() {
return easting;
}

public void setEasting(Integer easting) {
this.easting = easting;
}

public Integer getNorthing() {
return northing;
}

public void setNorthing(Integer northing) {
this.northing = northing;
}

}
