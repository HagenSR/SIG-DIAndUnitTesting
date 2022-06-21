
package WithDI;

import API.WeatherAPIHandler;
import API.WeatherResponse;

/**
 * @author Sean R Hagen
 * Version Jun 18, 2022 
 */
public class HelpfulWeatherDescriber {

        private WeatherResponse weatherResponse;
    
    /**
     * This constructor demonstrates dependency injection, where the WeatherAPIHandler class is injected
     * @param station
     * @param apiHandler
     * @throws Exception 
     */
    public HelpfulWeatherDescriber(String station, WeatherAPIHandler apiHandler) throws Exception{
        this.weatherResponse = apiHandler.getWeatherData(station);
    }

    
    public String getWeatherDescription(){
        String response = "";
        if(weatherResponse.getTemp() > 32){
            response += "It looks like a hot one!";
        }else if(weatherResponse.getTemp() < 0 && weatherResponse.getTemp() >  -45){
            response += "Grab a jacket! It's cold";
        }else if(weatherResponse.getTemp() < -45 ){
            response += "SHEEEEESH It's cold";
        }
        else{
            response += "I have no strong opinions about the current weather";
        }
        return response;
    }
    
}
