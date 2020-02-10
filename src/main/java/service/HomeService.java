package service;

import org.json.JSONException;
import packages.model.Earthquake;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class HomeService {

    /**
     * Giving list of 10 closest earthquakes to points given latitude and longtitude
     * @param session
     * @param latitude
     * @param longtitude
     */
    public void closestEarthquakes(HttpSession session, double latitude, double longtitude){
        try {
            Core core = new Core();
            List<Earthquake> earthquakeList = core.tenClosestEarthquakes(latitude, longtitude);
            session.setAttribute("earthquakeList",earthquakeList);
        }
        catch (IOException | JSONException e){e.printStackTrace();}
    }
}
