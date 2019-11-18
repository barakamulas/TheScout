
package com.baraka.thescout.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EplResponse {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("filters")
    @Expose
    private Filters filters;
    @SerializedName("competition")
    @Expose
    private Competition competition;
    @SerializedName("season")
    @Expose
    private Season season;
    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EplResponse() {
    }

    /**
     * 
     * @param teams
     * @param count
     * @param season
     * @param competition
     * @param filters
     */
    public EplResponse(Integer count, Filters filters, Competition competition, Season season, List<Team> teams) {
        super();
        this.count = count;
        this.filters = filters;
        this.competition = competition;
        this.season = season;
        this.teams = teams;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

}
