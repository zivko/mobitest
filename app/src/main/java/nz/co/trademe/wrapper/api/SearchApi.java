package nz.co.trademe.wrapper.api;

import nz.co.trademe.wrapper.model.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface SearchApi {

    @GET("/v1/Search/General.json")
    Call<SearchResponse> searchGeneral();

    @GET("/v1/Search/General.json")
    Call<SearchResponse> searchGeneral(@Query("category") String category, @Query("rows") Integer rows);
}

