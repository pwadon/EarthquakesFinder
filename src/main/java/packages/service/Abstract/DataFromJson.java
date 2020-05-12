package packages.service.Abstract;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class DataFromJson {

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
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
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
