
package WithDI;

import API.WeatherResponse;
import API.IWeatherAPIService;

/**
 * @author Sean R Hagen
 * Version Jun 18, 2022 
 */
public class HelpfulWeatherDescriber {

        private IWeatherAPIService weatherAPIService;
        private String stationID;
    
    /**
     * This constructor demonstrates dependency injection, where the WeatherAPIService class is injected
     * @param station The code for the airport weather station
     * @param weatherService The service that retrieves weather data
     * @throws Exception 
     */
    public HelpfulWeatherDescriber(String station, IWeatherAPIService weatherService) throws Exception{
        this.weatherAPIService = weatherService;
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
