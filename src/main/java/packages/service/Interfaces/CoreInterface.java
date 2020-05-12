package packages.service.Interfaces;

import packages.model.Earthquake;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface CoreInterface {

    /**
     * Calculating distance between 2 points with latitudes and lenghtitudes returning distance in kms
     * @param cityLatitude
     * @param earthquakeLatitude
     * @param cityLongtitude
     * @param earthquakeLongtitude
     * @return
     */
    double calculateDistance(double cityLatitude, double earthquakeLatitude, double cityLongtitude, double earthquakeLongtitude);

    /**
     * Sorting by given parameter
     * @param keyExtractor
     * @param <T>
     * @return
     */
    static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Getting 10 earthquakes with the lowest distance
     * @param earthquakes
     * @return
     */
    static List<Earthquake> top10Earthquakes(List<Earthquake> earthquakes){
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
    List<Earthquake> setDistance(List<Earthquake> earthquakes, double latitudeOfTheCity, double longitudeOfTheCity);
}
