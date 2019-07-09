package scb.academy.jinglebell.service

import retrofit2.Call
import retrofit2.http.GET
import scb.academy.jinglebell.model.Country

interface CountryApiService {

    @GET("rest/v2")
    fun countries(): Call<List<Country>>
}
