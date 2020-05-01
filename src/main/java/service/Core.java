package service;

import org.json.JSONException;
import packages.model.Earthquake;
import service.Interfaces.CoreInterface;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Core extends service.Abstract.Core {

    /**
     * creating list of top 10 closest earthquakes in previous 30 days to given latitude and longtitude of chosen location
     * @param latitudeOfTheCity
     * @param longitudeOfTheCity
     * @return
     * @throws IOException
     * @throws JSONException
     */
     List<Earthquake> tenClosestEarthquakes( double latitudeOfTheCity , double longitudeOfTheCity, List <Earthquake> earthquakes ) throws IOException, JSONException {

        List<Earthquake> top10ClosestEarthquakes;

        setDistance(earthquakes,latitudeOfTheCity,longitudeOfTheCity);
        top10ClosestEarthquakes =top10Earthquakes(earthquakes);

        return top10ClosestEarthquakes;
    }
}
