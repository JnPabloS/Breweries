package com.jnpablos.breweries.server

import com.jnpablos.breweries.model.BreweriesList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET(" ")
    fun getBreweries() : Call<BreweriesList>

    companion object {

        val urlApi = "https://api.openbrewerydb.org/breweries/"

        fun create() : ApiService {

            val interceptor = HttpLoggingInterceptor()

            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}