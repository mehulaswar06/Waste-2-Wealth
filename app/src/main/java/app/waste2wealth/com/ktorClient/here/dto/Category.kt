package app.waste2wealth.com.ktorClient.here.dto


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class Category(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("primary")
    val primary: Boolean?
)