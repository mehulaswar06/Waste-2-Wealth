package app.waste2wealth.com.ktorClient.geocoding


import app.waste2wealth.com.ktorClient.geocoding.FieldScore
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Scoring(
    @SerializedName("fieldScore")
    val fieldScore: FieldScore?,
    @SerializedName("queryScore")
    val queryScore: Double?
)