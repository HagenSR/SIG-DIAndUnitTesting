
package WeatherDescriberUnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;


/**
 * @author Sean R Hagen
 * Version Jun 19, 2022 
 */
public class WeatherDescriberUnitTests {
    
    private API.WeatherAPIService mockedHandler;
    private  WithDI.HelpfulWeatherDescriber mockedDescriber;
    
    @BeforeEach
    void setUp() throws Exception{
        mockedHandler = mock(API.WeatherAPIService.class);
    }

    @Test
    void WeatherDescriber_ShouldBeHot_WhenTempIs33() throws Exception {

        when(mockedHandler.getWeatherData("KFAR")).thenReturn(new API.WeatherResponse(33));
        mockedDescriber = new WithDI.HelpfulWeatherDescriber("KFAR", mockedHandler);
        assertEquals("It looks like a hot one!", mockedDescriber.getWeatherDescription());
    }
    
    @Test
    void WeatherDescriber_ShouldGrabJacket_WhenTempIsNeg1() throws Exception {
        // This is mockito, which defines what the mock does in a given instance
        when(mockedHandler.getWeatherData("KFAR")).thenReturn(new API.WeatherResponse(-1));
        mockedDescriber = new WithDI.HelpfulWeatherDescriber("KFAR", mockedHandler);
        assertEquals("Grab a jacket! It's cold", mockedDescriber.getWeatherDescription());
    }
    
    @Test
    void WeatherDescriber_ShouldSHEESH_WhenTempIsNeg60() throws Exception {
        when(mockedHandler.getWeatherData("KFAR")).thenReturn(new API.WeatherResponse(-60));
        mockedDescriber = new WithDI.HelpfulWeatherDescriber("KFAR", mockedHandler);
        assertEquals("SHEEEEESH It's cold", mockedDescriber.getWeatherDescription());
    }
    
    @Test
    void WeatherDescriber_ShouldHaveNoOpinion_WhenTempIs30() throws Exception {
        when(mockedHandler.getWeatherData("KFAR")).thenReturn(new API.WeatherResponse(30));
        mockedDescriber = new WithDI.HelpfulWeatherDescriber("KFAR", mockedHandler);
        assertEquals("I have no strong opinions about the current weather", mockedDescriber.getWeatherDescription());
    }
    
    /**
     * The below verify() method call checks to see that the getWeatherData method was called with any argument
     * This is more useful for checking if a database insert or API put/post operation occurred
     * @throws Exception 
     */
    @Test
    void WeatherDescriber_ShouldCallAPI_Always() throws Exception {
        when(mockedHandler.getWeatherData("KFAR")).thenReturn(new API.WeatherResponse(30));
        mockedDescriber = new WithDI.HelpfulWeatherDescriber("KFAR", mockedHandler);
        mockedDescriber.getWeatherDescription();
        verify(mockedHandler, times(1)).getWeatherData(any(String.class));
    }
    
    

    @Test
    void WeatherDescriber_ShouldRespondWithError_WhenAPIRaisesError() throws Exception {
        when(mockedHandler.getWeatherData("xx")).thenThrow(new Exception());
        mockedDescriber = new WithDI.HelpfulWeatherDescriber("xx", mockedHandler);
        assertEquals("I couldn't get any weather data for this location", mockedDescriber.getWeatherDescription());
    }
    
}
