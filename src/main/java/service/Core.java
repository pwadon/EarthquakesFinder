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

        setDistance(earthquakeList,latitudeOfTheCity,longitudeOfTheCity);
        top10ClosestEarthquakes =top10Earthquakes(earthquakeList);

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
    private double calculateDistance(double cityLatitude, double earthquakeLatitude, double cityLongtitude, double earthquakeLongtitude){

        double latDistance = Math.toRadians(earthquakeLatitude - cityLatitude);
        double lonDistance = Math.toRadians(earthquakeLongtitude - cityLongtitude);

        double distance1 = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
                (Math.cos(Math.toRadians(earthquakeLatitude))) *
                        (Math.cos(Math.toRadians(cityLatitude))) *
                        (Math.sin(lonDistance / 2)) *
                        (Math.sin(lonDistance / 2));

        double distance2 = 2 * Math.atan2(Math.sqrt(distance1), Math.sqrt(1 - distance1));

        int radiusOfEarth = 6371;
        return (int) (Math.round(radiusOfEarth * distance2));

    }

    /**
     * Sorting by given parameter
     * @param keyExtractor
     * @param <T>
     * @return
     */
    private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Getting 10 earthquakes with the lowest distance
     * @param earthquakes
     * @return
     */
    private List<Earthquake> top10Earthquakes(List<Earthquake> earthquakes){
        List <Earthquake> top10Earthquakes;

        top10Earthquakes = earthquakes.stream()
                .filter(distinctByKey(Earthquake::getDistanceFromGivenPoint))
                .sorted(Comparator.comparingDouble(Earthquake::getDistanceFromGivenPoint))
                .limit(10)
                .collect(Collectors.toList());

        return top10Earthquakes;
    }

    /**
     * Set Distance from given point to every earthquake on the list
     * @param earthquakes
     * @param latitudeOfTheCity
     * @param longitudeOfTheCity
     * @return
     */
    private List<Earthquake> setDistance(List <Earthquake> earthquakes, double latitudeOfTheCity, double longitudeOfTheCity){

        for (Earthquake earthquake: earthquakes) {
            earthquake.setDistanceFromGivenPoint(calculateDistance(latitudeOfTheCity, earthquake.getLatitude(), longitudeOfTheCity, earthquake.getLongtitude()));
        }

        return  earthquakes;
    }
}
