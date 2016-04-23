package nz.co.trademe.wrapper.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Category {

    @SerializedName("Name")
    private String name;
    @SerializedName("Number")
    private String number;
    @SerializedName("Path")
    private String path;
    @SerializedName("Subcategories")
    private ArrayList<Category> subcategories;
    @SerializedName("HasClassifieds")
    private boolean hasClassifieds;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ArrayList<Category> getSubcategories() {
        return this.subcategories;
    }

    public void setSubcategories(ArrayList<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setHasClassifieds(boolean hasClassifieds) {
        this.hasClassifieds = hasClassifieds;
    }

    public boolean hasClassifieds() {
        return this.hasClassifieds;
    }


}
