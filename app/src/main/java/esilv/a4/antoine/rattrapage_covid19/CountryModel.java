package esilv.a4.antoine.rattrapage_covid19;

import com.google.gson.annotations.SerializedName;

public class CountryModel {


    @SerializedName("country")
    private String country;
    @SerializedName("cases")
    private String cases;
    @SerializedName("todayCases")
    private String todayCases;
    @SerializedName("deaths")
    private String deaths;
    @SerializedName("todayDeaths")
    private String todayDeaths;
    @SerializedName("recovered")
    private String recovered;
    @SerializedName("active")
    private String active;
    @SerializedName("critical")
    private String critical;
    @SerializedName("casesPerOneMillion")
    private String casesPerOneMillion;
    @SerializedName("deathsPerOneMillion")
    private String deathsPerOneMillion;
    @SerializedName("totalTests")
    private String totalTests;
    @SerializedName("testsPerOneMillion")
    private String testsPerOneMillion;





    public CountryModel( String country, String cases, String todayCases, String deaths,
                        String todayDeaths, String recovered, String active, String critical, String casesPerOneMillion,
                        String deathsPerOneMillion, String totalTests, String testsPerOneMillion) {
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
        this.casesPerOneMillion = casesPerOneMillion;
        this.deathsPerOneMillion = deathsPerOneMillion;
        this.totalTests = totalTests;
        this.testsPerOneMillion = testsPerOneMillion;
    }


    public String getCountry(){
        return country;
    }
    public String getCases(){
        return cases;
    }
    public String getTodayCases(){
        return todayCases;
    }
    public String getDeaths(){
        return deaths;
    }
    public String getTodayDeaths(){
        return todayDeaths;
    }
    public String getActive(){
        return active;
    }
    public String getCritical(){
        return critical;
    }
    public String getCasesPerOneMillion(){
        return casesPerOneMillion;
    }
    public String getDeathsPerOneMillion(){
        return deathsPerOneMillion;
    }
    public String getTotalTests(){
        return totalTests;
    }
    public String getTestsPerOneMillion(){
        return testsPerOneMillion;
    }

}
