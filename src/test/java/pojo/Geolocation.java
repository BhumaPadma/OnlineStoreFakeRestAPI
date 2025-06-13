package pojo;

public class Geolocation {

	private String latitude;
	private String longitude;

	public Geolocation(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public void setLatitute(String latitude) {
		this.latitude = latitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLongitude() {
		return longitude;
	}

}
