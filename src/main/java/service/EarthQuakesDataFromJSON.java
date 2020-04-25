package service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import packages.model.Earthquake;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class EarthQuakesDataFromJSON extends DataFromJson {

    /**
     * Creating list of earthquakes on earth in last 30 days from :  https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public List<Earthquake> earthquakeList() throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson");

        List<Earthquake> earthquakes = new ArrayList<>();
            JSONObject json2 =new  JSONObject(json.toString());
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
