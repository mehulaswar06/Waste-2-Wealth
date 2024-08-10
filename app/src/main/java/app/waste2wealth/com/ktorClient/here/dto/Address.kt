package app.waste2wealth.com.ktorClient.here.dto


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class Address(
    @SerializedName("city")
    val city: String?,
    @SerializedName("countryCode")
    val countryCode: String?,
    @SerializedName("countryName")
    val countryName: String?,
    @SerializedName("county")
    val county: String?,
    @SerializedName("district")
    val district: String?,
    @SerializedName("label")
    val label: String?,
    @SerializedName("postalCode")
    val postalCode: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("stateCode")
    val stateCode: String?,
    @SerializedName("subdistrict")
    val subdistrict: String?
)