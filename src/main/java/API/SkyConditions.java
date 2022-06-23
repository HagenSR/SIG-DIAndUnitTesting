
package API;


/**
 * @author Sean R Hagen
 * Version Jun 13, 2022 
 */
public class SkyConditions {
    private String coverage;
    private String base_agl;
    
    public SkyConditions(String coverage, String baseAgl){
        this.coverage = coverage;
        this.base_agl = baseAgl;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public String getBase_agl() {
        return base_agl;
    }

    public void setBase_agl(String base_agl) {
        this.base_agl = base_agl;
    }
}
