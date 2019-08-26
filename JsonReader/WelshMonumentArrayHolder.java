package JsonReader;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WelshMonumentArrayHolder {
	

		@SerializedName("type")
		@Expose
		private String type;
		@SerializedName("totalFeatures")
		@Expose
		private Integer totalFeatures;
		@SerializedName("features")
		@Expose
		private List<WelshMonFeature> features = null;

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

		public List<WelshMonFeature> getFeatures() {
		return features;
		}

		public void setFeatures(List<WelshMonFeature> features) {
		this.features = features;
		}

	

}
