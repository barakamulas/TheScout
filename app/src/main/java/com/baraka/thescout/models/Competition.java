
package com.baraka.thescout.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Competition {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("area")
    @Expose
    private Area area;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("plan")
    @Expose
    private String plan;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Competition() {
    }

    /**
     * 
     * @param area
     * @param lastUpdated
     * @param code
     * @param name
     * @param id
     * @param plan
     */
    public Competition(Integer id, Area area, String name, String code, String plan, String lastUpdated) {
        super();
        this.id = id;
        this.area = area;
        this.name = name;
        this.code = code;
        this.plan = plan;
        this.lastUpdated = lastUpdated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
