package scb.academy.jinglebell.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(
        @SerializedName("trackName") val name: String,
        @SerializedName("artistName") val artistName: String,
        @SerializedName("collectionName") val album: String,
        @SerializedName("artworkUrl100") val artworkUrl: String,
        @SerializedName("primaryGenreName") val genre: String,
        @SerializedName("trackPrice") val price: String,
        @SerializedName("collectionPrice") val collectionPrice: String,
        @SerializedName("currency") val priceCurrency: String,
        @SerializedName("releaseDate") val releaseDate: String
) : Parcelable
