package WithoutDI;

import API.WeatherAPIService;
import API.WeatherResponse;

/**
 * @author Sean R Hagen Version Jun 18, 2022
 */
public class HelpfulWeatherDescriber {

    private WeatherAPIService weatherAPIService;
    private String stationID;

    public HelpfulWeatherDescriber(String station) throws Exception {
        this.weatherAPIService = new WeatherAPIService();
        this.stationID = station;
    }

    public String getWeatherDescription() {
        WeatherResponse weatherResponse = getWeatherReponse();
        String response;
        if (weatherResponse == null) {
            response = "I couldn't get any weather data for this location";
        } else if (weatherResponse.getTemp() > 32) {
            response = "It looks like a hot one!";
        } else if (weatherResponse.getTemp() < 0 && weatherResponse.getTemp() > -45) {
            response = "Grab a jacket! It's cold";
        } else if (weatherResponse.getTemp() < -45) {
            response = "SHEEEEESH It's cold";
        } else {
            response = "I have no strong opinions about the current weather";
        }
        return response;
    }

    private WeatherResponse getWeatherReponse() {
        WeatherResponse response;
        try {
            response = this.weatherAPIService.getWeatherData(stationID);
        } catch (Exception e) {
            response = null;
        }
        return response;
    }

}
