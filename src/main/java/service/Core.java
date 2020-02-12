package service;

import org.json.JSONException;
import packages.model.Earthquake;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Core {


    private final static  int RadiusOfEarth =6371;
    private EarthQuakesDataFromJSON earthQuakesDataFromJSON = new EarthQuakesDataFromJSON();
    private List<Earthquake> earthquakeList = new ArrayList<>();

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
        List<Earthquake> top10ClosestEarthquakes;

        for (Earthquake earthquake: earthquakeList) {
            earthquake.setDistanceFromGivenPoint(calculateDistance(latitudeOfTheCity, earthquake.getLatitude(), longitudeOfTheCity, earthquake.getLongtitude()));
        }

        top10ClosestEarthquakes =earthquakeList.stream()
                .filter(distinctByKey(Earthquake::getDistanceFromGivenPoint))
                .sorted(Comparator.comparingDouble(Earthquake::getDistanceFromGivenPoint))
                .limit(10)
                .collect(Collectors.toList());

        return top10ClosestEarthquakes;
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

    /**
     * Sorting by given parameter
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
