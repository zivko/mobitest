package nz.co.trademe.wrapper.model;

import com.google.gson.annotations.SerializedName;
import nz.co.trademe.wrapper.model.Listing;
import nz.co.trademe.wrapper.model.SuggestedCategory;

import java.util.List;

public class SearchResponse {

    @SerializedName("Page")
    private int page;
    @SerializedName("PageSize")
    private int pageSize;
    @SerializedName("TotalCount")
    private int totalCount;
    @SerializedName("List")
    private List<Listing> listings;
    @SerializedName("FoundCategories")
    private List<SuggestedCategory> suggestedCategories;

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Listing> getListings() {
        return this.listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    public List<SuggestedCategory> getSuggestedCategories() {
        return this.suggestedCategories;
    }

    public void setSuggestedCategories(List<SuggestedCategory> suggestedCategories) {
        this.suggestedCategories = suggestedCategories;
    }

}
