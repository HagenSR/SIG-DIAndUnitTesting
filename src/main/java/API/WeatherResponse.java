package API;

import java.util.List;

/**
 * @author Sean R Hagen Version Jun 13, 2022
 */
public class WeatherResponse {

    private String station_id;
    private String raw;
    private float temp;
    private float dewpoint;
    private float wind;
    private float wind_vel;
    private float visibility;
    private float alt_hg;
    private float alt_mb;
    private float wx;
    private boolean auto_report;
    private List<SkyConditions> sky_conditions;
    private String category;
    private String report_type;
    private String time_of_obs;

    public String getStation_id() {
        return station_id;
    }

    public String getRaw() {
        return raw;
    }

    public float getTemp() {
        return temp;
    }

    public float getDewpoint() {
        return dewpoint;
    }

    public float getWind() {
        return wind;
    }

    public float getWind_vel() {
        return wind_vel;
    }

    public float getVisibility() {
        return visibility;
    }

    public float getAlt_hg() {
        return alt_hg;
    }

    public float getAlt_mb() {
        return alt_mb;
    }

    public float getWx() {
        return wx;
    }

    public boolean isAuto_report() {
        return auto_report;
    }

    public List<SkyConditions> getSky_conditions() {
        return sky_conditions;
    }

    public String getCategory() {
        return category;
    }

    public String getReport_type() {
        return report_type;
    }

    public String getTime_of_obs() {
        return time_of_obs;
    }    
    
    public WeatherResponse(int temp){
        this.temp = temp;
    }
}
