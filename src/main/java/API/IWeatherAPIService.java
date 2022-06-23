
package API;

/**
 *
 * @author Sean R Hagen
 * Version Jun 22, 2022
 */
public interface IWeatherAPIService {
 WeatherResponse getWeatherData(String stationID) throws Exception;
}
