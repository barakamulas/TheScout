package com.baraka.thescout.network;

import com.baraka.thescout.models.EplResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EplApi {

    @GET("competitions/2021")
    Call<EplResponse> getRestaurants(
    );
}
