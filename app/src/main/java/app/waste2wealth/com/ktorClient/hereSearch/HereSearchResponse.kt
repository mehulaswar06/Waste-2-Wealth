package app.waste2wealth.com.ktorClient.hereSearch


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class HereSearchResponse(
    @SerializedName("items")
    val items: List<Item>?,
)