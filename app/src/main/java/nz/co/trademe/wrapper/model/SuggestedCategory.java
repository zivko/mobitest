package nz.co.trademe.wrapper.model;

import com.google.gson.annotations.SerializedName;

public class SuggestedCategory {

    @SerializedName("Count")
    private int count;

    @SerializedName("Name")
    private String name;

    @SerializedName("Category")
    private String mcat;


    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMcat() {
        return this.mcat;
    }

    public void setMcat(String mcat) {
        this.mcat = mcat;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
