package app.waste2wealth.com.ktorClient.repository

import app.waste2wealth.com.ktorClient.Resource
import app.waste2wealth.com.ktorClient.geocoding.GeoCodes
import app.waste2wealth.com.ktorClient.here.dto.HerePlaces
import app.waste2wealth.com.ktorClient.hereSearch.HereSearchResponse
import app.waste2wealth.com.ktorClient.placesAPI.dto.Places


interface PlacesRepository {
    suspend fun getPlaces(latLong: String): Resource<HerePlaces>

    suspend fun getGeocodingData(query: String): GeoCodes

    suspend fun hereSearch(
        query: String,
        latitude: Double,
        longitude: Double,
        limit: Int = 6,
    ): HereSearchResponse


}
