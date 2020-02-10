package packages.model;


import java.util.List;

public class Earthquake {

    protected int id;
    protected double latitude, longtitude, distanceFromGivenPoint;
    protected String title;
    protected List<Earthquake> earthquakeList;
    private static int count =0;

    public Earthquake(double latitude, double longtitude, String title) {
        setId(++count);
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.title = title;
    }

    public Earthquake() {
        setId(++count);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDistanceFromGivenPoint() {
        return distanceFromGivenPoint;
    }

    public void setDistanceFromGivenPoint(double distanceFromGivenPoint) {
        this.distanceFromGivenPoint = distanceFromGivenPoint;
    }

    public List<Earthquake> getEarthquakeList() {
        return earthquakeList;
    }

    public void setEarthquakeList(List<Earthquake> earthquakeList) {
        this.earthquakeList = earthquakeList;
    }
}
