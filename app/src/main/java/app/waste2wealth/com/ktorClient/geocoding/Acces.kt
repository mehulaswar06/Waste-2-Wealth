package app.waste2wealth.com.ktorClient.geocoding


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Acces(
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lng")
    val lng: Double?
)