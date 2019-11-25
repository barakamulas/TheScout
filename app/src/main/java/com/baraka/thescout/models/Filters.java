
package com.baraka.thescout.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;


@Parcel
public class Filters {

    @SerializedName("limit")
    @Expose
    private Integer limit;

    /**
     * No args constructor for use in serialization
     *
     */
    public Filters() {
    }

    /**
     *
     * @param limit
     */
    public Filters(Integer limit) {
        super();
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
