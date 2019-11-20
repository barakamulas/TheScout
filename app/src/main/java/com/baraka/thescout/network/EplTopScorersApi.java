package com.baraka.thescout.network;

import com.baraka.thescout.models.EplScorersSearch;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EplTopScorersApi {

    @GET("competitions/2021/scorers")
    Call<EplScorersSearch> getTopScorers(
    );
}
