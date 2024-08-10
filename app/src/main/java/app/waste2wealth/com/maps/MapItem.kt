package app.waste2wealth.com.maps

import com.mapbox.geojson.Point

data class MapItem(
    val image: String,
    val location: String,
    val time: Long,
    val point: Point
)