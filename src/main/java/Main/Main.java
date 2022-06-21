package Main;

import static org.mockito.Mockito.*;

/**
 * @author Sean R Hagen
 * Version Jun 13, 2022 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        // How do we unit test the HelpfulWeatherDescriber class? 
        // It's dependent on data returned from an API
        // But weather data changes daily!
        // IE unit tests made today will break tomorrow
        WithoutDI.HelpfulWeatherDescriber withoutDIDescribe = new WithoutDI.HelpfulWeatherDescriber("KFAR");
        System.out.println(withoutDIDescribe.getWeatherDescription());
        
        // Below is an example of dependency injection, where we pass in and can control what data the API controls
        WithDI.HelpfulWeatherDescriber withDIDescribe = new WithDI.HelpfulWeatherDescriber("KFAR",new API.WeatherAPIHandler());
        System.out.println(withDIDescribe.getWeatherDescription());
        
        //Here, We mock the API And have it return predefined, static information.
        API.WeatherAPIHandler mockedHandler = mock(API.WeatherAPIHandler.class);
        // This is mockito, which defines what the mock does in a given instance
        when(mockedHandler.getWeatherData("KFAR")).thenReturn(new API.WeatherResponse(-100));
        WithDI.HelpfulWeatherDescriber mockedDescriber = new WithDI.HelpfulWeatherDescriber("KFAR", mockedHandler);
        System.out.println(mockedDescriber.getWeatherDescription());
        // See the Test package for more examples of mocking
    }

}
