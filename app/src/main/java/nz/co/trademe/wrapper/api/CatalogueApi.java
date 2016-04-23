package nz.co.trademe.wrapper.api;

import nz.co.trademe.wrapper.model.Category;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CatalogueApi {

    @GET("/v1/Categories.json")
    Call<Category> retrieveCategories();

    @GET("/v1/Categories/{number}.json")
    Call<Category> retrieveCategories(@Path("number") String number);
}
