package packages.model;

public class Earthquake {

    protected int id;
    protected double latitude, longtitude, distanceFromGivenPoint;
    protected String title;
    private static int count =0;

    public Earthquake(double latitude, double longtitude, String title) {
        this.id = ++count;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.title = title;
    }

    public Earthquake() {
        this.id= ++count;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public double getDistanceFromGivenPoint() {
        return distanceFromGivenPoint;
    }

    public void setDistanceFromGivenPoint(double distanceFromGivenPoint) {
        this.distanceFromGivenPoint = distanceFromGivenPoint;
    }



    @Override
    public String toString() {
        return "Earthquake{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longtitude=" + longtitude +
                ", distanceFromGivenPoint=" + distanceFromGivenPoint +
                ", title='" + title + '\'' +
                '}';
    }
}
