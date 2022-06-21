
package WeatherDescriberUnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * @author Sean R Hagen
 * Version Jun 19, 2022 
 */
public class WeatherDescriberUnitTests {
    
    private API.WeatherAPIHandler mockedHandler;
    private  WithDI.HelpfulWeatherDescriber mockedDescriber;
    
    @BeforeEach
    void setUp() throws Exception{
        mockedHandler = mock(API.WeatherAPIHandler.class);
    }

    @Test
    void hotWeather() throws Exception {

        when(mockedHandler.getWeatherData("KFAR")).thenReturn(new API.WeatherResponse(33));
        mockedDescriber = new WithDI.HelpfulWeatherDescriber("KFAR", mockedHandler);
        assertEquals("It looks like a hot one!", mockedDescriber.getWeatherDescription());
    }
    
    @Test
    void coldWeather() throws Exception {
        // This is mockito, which defines what the mock does in a given instance
        when(mockedHandler.getWeatherData("KFAR")).thenReturn(new API.WeatherResponse(-1));
        mockedDescriber = new WithDI.HelpfulWeatherDescriber("KFAR", mockedHandler);
        assertEquals("Grab a jacket! It's cold", mockedDescriber.getWeatherDescription());
    }
    
    @Test
    void reallyColdWeather() throws Exception {
        when(mockedHandler.getWeatherData("KFAR")).thenReturn(new API.WeatherResponse(-60));
        mockedDescriber = new WithDI.HelpfulWeatherDescriber("KFAR", mockedHandler);
        assertEquals("SHEEEEESH It's cold", mockedDescriber.getWeatherDescription());
    }
    
    @Test
    void noOpinion() throws Exception {
        when(mockedHandler.getWeatherData("KFAR")).thenReturn(new API.WeatherResponse(30));
        mockedDescriber = new WithDI.HelpfulWeatherDescriber("KFAR", mockedHandler);
        assertEquals("I have no strong opinions about the current weather", mockedDescriber.getWeatherDescription());
    }
}
