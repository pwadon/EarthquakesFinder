package Test;

import org.junit.Assert;
import org.junit.Test;
import packages.model.Earthquake;
import service.Core;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CoreTest {

    @Test
    public void tenClosestEarthquakes() {


    }

    @Test
    public void calculateDistance() {
    }

    /**
     * Testing correct list sorting.
     */
    @Test
    public void top10EarthquakesTest(){

        List <Earthquake> earthquakeList = new ArrayList<>();

        for (int i = 0; i <20 ; i++) {
            Earthquake earthquake = new Earthquake();
            Earthquake earthquake1 = new Earthquake();
            earthquake.setDistanceFromGivenPoint(i+1);
            earthquake1.setDistanceFromGivenPoint(20);
            earthquakeList.add(earthquake);
            earthquakeList.add(earthquake1);
        }

        earthquakeList= Core.top10Earthquakes(earthquakeList);

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