package esilv.a4.antoine.rattrapage_covid19;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    @GET("countries")
    Call<List<CountryModel>> getCountryModel();

    @GET("/countries/{countryName}")
    Call<CountryModel> getCountryDetails(@Query("query") String country);


}
