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
        
        // Below is the same class but it uses dependency injection
        WithDI.HelpfulWeatherDescriber withDIDescribe = new WithDI.HelpfulWeatherDescriber("KFAR",new API.WeatherAPIService());
        System.out.println(withDIDescribe.getWeatherDescription());
        
        //Here, We mock the API And have it return predefined, static information.
        // Line 31 is where we define the mock's behavior
        // See the Test package for more examples of mocking
        API.WeatherAPIService mockedHandler = mock(API.WeatherAPIService.class);
        when(mockedHandler.getWeatherData("KFAR")).thenReturn(new API.WeatherResponse(-100));
        WithDI.HelpfulWeatherDescriber mockedDescriber = new WithDI.HelpfulWeatherDescriber("KFAR", mockedHandler);
        System.out.println(mockedDescriber.getWeatherDescription());
    }

}
