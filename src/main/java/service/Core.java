package service;

import org.json.JSONException;
import packages.model.Earthquake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Core {


    private final static  int RadiusOfEarth =6371;
    private EarthQuakesDataFromJSON earthQuakesDataFromJSON = new EarthQuakesDataFromJSON();
    private List<Earthquake> earthquakeList = new ArrayList<>();

//mala czytelnosc kodu
    /**
     * creating list of top 10 closest earthquakes in previous 30 days to given latitude and longtitude of chosen location
     * @param latitudeOfTheCity
     * @param longitudeOfTheCity
     * @return
     * @throws IOException
     * @throws JSONException
     */
    protected List<Earthquake> tenClosestEarthquakes( double latitudeOfTheCity , double longitudeOfTheCity ) throws IOException, JSONException {

        earthquakeList = earthQuakesDataFromJSON.earthquakeList();
        List<Earthquake> top10ClosestEarthquakes = new ArrayList<>();

        if (earthquakeList.size()>10) {
            for (int i = 0; i < 10; i++) {
                createListOf10Earthquakes(earthquakeList,top10ClosestEarthquakes,latitudeOfTheCity,longitudeOfTheCity);
            }
            Collections.sort(top10ClosestEarthquakes, Comparator.comparingDouble(earthquake2 -> earthquake2.getDistanceFromGivenPoint()));
            Collections.reverse(top10ClosestEarthquakes);

            for (Earthquake earthquake:earthquakeList) {
                /**
                 * checking if earthquake has the same parameters as any earthquake in the list
                 */
                boolean sameEarthquake = false;
                for (int i = 0; i <10 ; i++) {
                    if (earthquake.getLatitude() == top10ClosestEarthquakes.get(i).getLatitude() && earthquake.getLongtitude() == top10ClosestEarthquakes.get(i).getLongtitude()) {
                        sameEarthquake = true;
                        break;
                    }
                }
                earthquake.setDistanceFromGivenPoint(calculateDistance(latitudeOfTheCity, earthquake.getLatitude(), longitudeOfTheCity, earthquake.getLongtitude()));
                if(!sameEarthquake) {
                    for (int i = 0; i < 10; i++) {
                        if (earthquake.getDistanceFromGivenPoint() < top10ClosestEarthquakes.get(i).getDistanceFromGivenPoint()) {
                            top10ClosestEarthquakes.set(i, earthquake);
                            break;
                        }
                    }
                }

            }

            Collections.sort(top10ClosestEarthquakes, Comparator.comparingDouble(earthquake2 -> earthquake2.getDistanceFromGivenPoint()));

        }
        /**
         * if given earthquake list size is = 0 give abstract values and title with information about 0 earthquakes
         */
        else if(earthquakeList.size()==0){
            Earthquake earthquake = new Earthquake();
            earthquake.setLatitude(0);
            earthquake.setLongtitude(0);
            earthquake.setTitle("there were no earthquakes on earth in last 30 days ! it's a miracle !");
            top10ClosestEarthquakes.add(earthquake);

        }
        /**
         * if earthquakes list is > 0 and <= 10 return it in ascending order
         */
        else{
            top10ClosestEarthquakes = earthquakeList;
            Collections.sort(top10ClosestEarthquakes, Comparator.comparingDouble(earthquake2 -> earthquake2.getDistanceFromGivenPoint()));
        }

        return top10ClosestEarthquakes;
    }

    /**
     * creating list of top10 earthquakes from given earthquakes list and removing those earthquakes from given list, also adding the Distance from Given point
     * @param earthquakeList
     * @param top10ClosestEarthquakes
     * @param latitudeOfTheCity
     * @param longitudeOfTheCity
     */
    private static void createListOf10Earthquakes(List<Earthquake> earthquakeList,List<Earthquake> top10ClosestEarthquakes, double latitudeOfTheCity, double longitudeOfTheCity){
        Earthquake earthquake = earthquakeList.get(0);
        earthquake.setDistanceFromGivenPoint(calculateDistance(latitudeOfTheCity, earthquake.getLatitude(), longitudeOfTheCity, earthquake.getLongtitude()));
        top10ClosestEarthquakes.add(earthquake);
        earthquakeList.remove(0);

    }

    /**
     * Calculating distance between 2 points with latitudes and lenghtitudes returning distance in kms
     * @param cityLatitude
     * @param earthquakeLatitude
     * @param cityLongtitude
     * @param earthquakeLongtitude
     * @return
     */
    protected static double calculateDistance(double cityLatitude, double earthquakeLatitude, double cityLongtitude, double earthquakeLongtitude){

        double latDistance = Math.toRadians(earthquakeLatitude - cityLatitude);
        double lonDistance = Math.toRadians(earthquakeLongtitude - cityLongtitude);

        double distance1 = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
                (Math.cos(Math.toRadians(earthquakeLatitude))) *
                        (Math.cos(Math.toRadians(cityLatitude))) *
                        (Math.sin(lonDistance / 2)) *
                        (Math.sin(lonDistance / 2));

        double distance2 = 2 * Math.atan2(Math.sqrt(distance1), Math.sqrt(1 - distance1));

        return (int) (Math.round(RadiusOfEarth * distance2));

    }
}
