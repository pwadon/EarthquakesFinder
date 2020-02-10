package packages.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.HomeService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class HomeController {

    /**
     * home page
     * @return home page
     */
    @RequestMapping
    public String home () {
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
    public String homeEarthquakes (@RequestParam("latitude") double latitude, @RequestParam("longtitude") double longtitude, HttpSession session){
        HomeService homeService = new HomeService();
        homeService.closestEarthquakes(session, latitude, longtitude);
        return "home";
    }
}


