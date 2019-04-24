package com.example.syahrinka.service;

import com.example.syahrinka.model.Joke;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JokesService {
    @GET("/jokes/random")
    Call<Joke> getRandomJoke();
}
