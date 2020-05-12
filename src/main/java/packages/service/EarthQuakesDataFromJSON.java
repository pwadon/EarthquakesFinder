package packages.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import packages.model.Earthquake;
import packages.service.Abstract.DataFromJson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service("earthquakesDataFromJson")
public class EarthQuakesDataFromJSON extends DataFromJson {
    /**
     * Creating list of earthquakes on earth in last 30 days from :  https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public List<Earthquake> earthquakeList() throws IOException, JSONException {

        List<Earthquake> earthquakes = new ArrayList<>();
            JSONObject json2 =new  JSONObject(readJsonFromUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson").toString());
            JSONArray array = json2.getJSONArray("features");

            for (int i = 0; i <array.length() ; i++) {
                JSONObject p = array.getJSONObject(i).getJSONObject("properties");
                JSONArray p2 =array.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates");

                Earthquake earthquake = new Earthquake();
                earthquake.setLatitude(p2.getDouble(0));
                earthquake.setLongtitude(p2.getDouble(1));
                earthquake.setTitle(p.getString("title"));
                earthquakes.add(earthquake);
            }
        return earthquakes;
    }
}
