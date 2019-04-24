package com.example.syahrinka.service;

import com.example.syahrinka.model.Fox;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoxesService {
    @GET("floof/")
    Call<Fox> getRandomFox();
}
