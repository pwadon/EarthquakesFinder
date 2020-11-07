package packages.service;

import org.json.JSONException;
import org.springframework.stereotype.Component;
import packages.model.Earthquake;

import java.io.IOException;
import java.util.*;

@Component
public class Core extends packages.service.Abstract.Core {

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
        top10ClosestEarthquakes = top10Earthquakes(earthquakes);

        return top10ClosestEarthquakes;
    }
}
