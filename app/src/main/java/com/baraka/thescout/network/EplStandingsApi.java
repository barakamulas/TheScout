package com.baraka.thescout.network;

import com.baraka.thescout.models.EplResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EplStandingsApi {

    @GET("competitions/2021/standings")
    Call<EplResponse> getStandings(
    );
}
