package packages.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import packages.model.Earthquake;
import packages.service.EarthQuakesDataFromJSON;
import packages.service.HomeService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {

    final EarthQuakesDataFromJSON earthQuakesDataFromJSON;

    final HomeService homeService;

    List<Earthquake> earthquakeList = new ArrayList<>();

    public HomeController(EarthQuakesDataFromJSON earthQuakesDataFromJSON, HomeService homeService) {
        this.earthQuakesDataFromJSON = earthQuakesDataFromJSON;
        this.homeService = homeService;
    }

    /**
     * home page
     * @return home page
     */
    @RequestMapping
    public String home () throws  IOException{
        earthquakeList = earthQuakesDataFromJSON.earthquakeList();
        return "home";
    }

    /**
     * home page with list of 10 closest earthquakes
     * @param latitude
     * @param longtitude
     * @param session
     * @return
     */
    @RequestMapping("/home")
    public String homeEarthquakes (@RequestParam("latitude") double latitude, @RequestParam("longtitude") double longtitude, HttpSession session) throws IOException {
        homeService.closestEarthquakes(session, latitude, longtitude, earthquakeList);
        return "home";
    }
}


