package scb.academy.jinglebell.model

import com.google.gson.annotations.SerializedName

data class Country(
        @SerializedName("name") val name: String,
        @SerializedName("capital") val capital: String,
        @SerializedName("alpha2Code") val alpha2Code: String
) {
    val imageUrl: String
        get() = "https://www.countryflags.io/$alpha2Code/flat/64.png"
}
