package Test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import packages.model.Earthquake;
import packages.service.Core;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CoreTest {

    private List <Earthquake> earthquakeList;
    private Core core;

    @Before
    public void setup(){
        earthquakeList = new ArrayList<>();
        core = new Core();
    }


    @Test
    public void calculateDistance() {
    }


    @Test
    public void top10ClosestEarthquakesTest(){

        for (int i = 0; i <20 ; i++) {
            Earthquake earthquake = new Earthquake();
            Earthquake earthquake1 = new Earthquake();
            earthquake.setDistanceFromGivenPoint(i+1);
            earthquake1.setDistanceFromGivenPoint(20);
            earthquakeList.add(earthquake);
            earthquakeList.add(earthquake1);
        }

        earthquakeList= core.top10Earthquakes(earthquakeList);

        List<Earthquake> earthquakeList2 = new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            Earthquake earthquake = new Earthquake();
            earthquake.setDistanceFromGivenPoint(i+1);
            earthquakeList2.add(earthquake);
        }

        for (int i = 0; i <10 ; i++) {
            assertEquals(earthquakeList.get(i).getDistanceFromGivenPoint(),earthquakeList2.get(i).getDistanceFromGivenPoint(),0);
        }

    }
}
