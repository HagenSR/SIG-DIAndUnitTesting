package API;

import WithoutDI.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;

/**
 * @author Sean R Hagen Version Jun 13, 2022
 */
public class WeatherAPIService implements IWeatherAPIService {
    
    @Override
    public WeatherResponse getWeatherData(String stationID) throws Exception {
        String response = getResponseString(stationID);
        return parseResponse(response);
    }

    private String getResponseString(String stationID) throws Exception {
        URL url = new URL("https://api.aviationapi.com/v1/weather/metar?apt=" + stationID);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        StringBuilder content = new StringBuilder();
        try ( BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        }
        return content.toString();
    }
    
    private WeatherResponse parseResponse(String response){
        Gson gson = new Gson();
        String trimmed = removeIDWrapper(response);
        return gson.fromJson(trimmed, WeatherResponse.class);
    }
    
    /**
     * Annoying ID wrapper around the API response
     * @param wrapper The string with the 'ID Wrapper' around it
     * @return A string without the wrapper
     */
    private String removeIDWrapper(String wrapper){
        int secondBracketIndex = wrapper.indexOf("{", 1);
        String temp = wrapper.substring(secondBracketIndex, wrapper.length() - 1);
        return temp;
    }

}
