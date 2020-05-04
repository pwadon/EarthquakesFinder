package service;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import packages.model.Earthquake;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Service
public class HomeService {

    private Core core;

    @Autowired
    public HomeService(Core core) {
        this.core = core;
    }

    /**
     * Giving list of 10 closest earthquakes to points given latitude and longtitude
     * @param session
     * @param latitude
     * @param longtitude
     */
    public void closestEarthquakes(HttpSession session, double latitude, double longtitude, List <Earthquake> earthquakes){
        try {

            List<Earthquake> earthquakeList = core.tenClosestEarthquakes(latitude, longtitude,earthquakes);
            session.setAttribute("earthquakeList",earthquakeList);
        }
        catch (IOException | JSONException e){e.printStackTrace();}
    }
}
