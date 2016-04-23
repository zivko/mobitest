package nz.co.trademe.wrapper.model;

import com.google.gson.annotations.SerializedName;

public class Listing {

    @SerializedName("ListingId")
    private String listingId;
    @SerializedName("Title")
    private String title;
    @SerializedName("PictureHref")
    private String pictureHref;
    @SerializedName("Region")
    private String region;
    @SerializedName("PriceDisplay")
    private String priceDisplay;


    public String getListingId() {
        return this.listingId;
    }

    public void setListingId(String id) {
        this.listingId = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureHref() {
        return this.pictureHref;
    }

    public void setPictureHref(String pictureHref) {
        this.pictureHref = pictureHref;
    }

    public String getPriceDisplay() {
        return this.priceDisplay;
    }

    public void setPriceDisplay(String priceDisplay) {
        this.priceDisplay = priceDisplay;
    }


}
