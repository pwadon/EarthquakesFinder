package packages.service.Abstract;

import org.springframework.stereotype.Component;
import packages.model.Earthquake;
import packages.service.Interfaces.CoreInterface;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public abstract class Core implements CoreInterface {

    final int radiusOfEarth = 6371;
    /**
     * Calculating distance between 2 points with latitudes and longtitudes returning distance in kms
     * @param cityLatitude
     * @param earthquakeLatitude
     * @param cityLongtitude
     * @param earthquakeLongtitude
     * @return
     */
    public double calculateDistance(double cityLatitude, double earthquakeLatitude, double cityLongtitude, double earthquakeLongtitude){

        double latDistance = Math.toRadians(earthquakeLatitude - cityLatitude);
        double lonDistance = Math.toRadians(earthquakeLongtitude - cityLongtitude);

        double distance1 = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
                (Math.cos(Math.toRadians(earthquakeLatitude))) *
                        (Math.cos(Math.toRadians(cityLatitude))) *
                        (Math.sin(lonDistance / 2)) *
                        (Math.sin(lonDistance / 2));

        double distance2 = 2 * Math.atan2(Math.sqrt(distance1), Math.sqrt(1 - distance1));

        return (int) (Math.round(radiusOfEarth * distance2));

    }

    /**
     * Sorting by given parameter
     * @param keyExtractor
     * @param <T>
     * @return
     */
    private  <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Getting 10 earthquakes with the lowest distance
     * @param earthquakes
     * @return
     */
    public List<Earthquake> top10Earthquakes(List<Earthquake> earthquakes){
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
    public List<Earthquake> setDistance(List<Earthquake> earthquakes, double latitudeOfTheCity, double longitudeOfTheCity){
         earthquakes.forEach(earthquake -> earthquake.setDistanceFromGivenPoint(calculateDistance(latitudeOfTheCity, earthquake.getLatitude(), longitudeOfTheCity, earthquake.getLongtitude())));

        return  earthquakes;
    }
}
