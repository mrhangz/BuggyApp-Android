package scb.academy.jinglebell.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import scb.academy.jinglebell.model.SongSearchResult

interface ArtistApiService {

    @GET("songlist")
    fun songs(@Query("term") artistName: String? = "taylorswift"): Call<SongSearchResult>
}
