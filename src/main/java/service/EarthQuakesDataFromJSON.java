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

public class EarthQuakesDataFromJSON {

    /**
     * Creating list of earthquakes on earth in last 30 days from :  https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public List<Earthquake> earthquakeList() throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson");
        JSONArray jsonArray = json.getJSONArray("features");
        List<Earthquake> earthquakes = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject object = jsonArray.getJSONObject(i);


            JSONObject object1 = object.getJSONObject("geometry");
            JSONObject object2 = object.getJSONObject("properties");


            String title = object2.getString("title");
            JSONArray array = object1.getJSONArray("coordinates");


            double longtitude = array.getDouble(0);
            double latitude = array.getDouble(1);

            Earthquake earthquake = new Earthquake();
            earthquake.setLatitude(latitude);
            earthquake.setLongtitude(longtitude);
            earthquake.setTitle(title);

            earthquakes.add(earthquake);
        }
        return earthquakes;
    }

    /**
     * Reads JSON from given URL
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream inputStream = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);

            return json;
        } finally {
            inputStream.close();
        }
    }

    /**
     * Reads all data from given Buffer Reader
     * @param rd
     * @return stringBuilder.toString()
     * @throws IOException
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            stringBuilder.append((char) cp);
        }
        return stringBuilder.toString();
    }

}
