
package WithoutDI;

import API.WeatherAPIHandler;
import API.WeatherResponse;

/**
 * @author Sean R Hagen
 * Version Jun 18, 2022 
 */
public class HelpfulWeatherDescriber {
    
    private WeatherResponse weatherResponse;
    
    public HelpfulWeatherDescriber(String station) throws Exception{
        this.weatherResponse = new WeatherAPIHandler().getWeatherData(station);
    }

    
    public String getWeatherDescription(){
        String response = "";
        if(weatherResponse.getTemp() > 32){
            response += "It looks like a hot one!";
        }else if(weatherResponse.getTemp() < 0){
            response += "Grab a jacket! It's cold";
        }else{
            response += "I have no strong opinions about the current weather";
        }
        return response;
    }
    
}
