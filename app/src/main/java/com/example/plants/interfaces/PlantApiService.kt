package com.example.plants.interfaces

import com.example.plants.data.Plant
import retrofit2.http.*

interface PlantApiService {
    @GET("plants")
    suspend fun getPlants(): List<Plant>

    @POST("plants")
    suspend fun addPlant(@Body user: Plant): Plant

    @PUT("plants/{id}")
    suspend fun updatePlant(@Path("id") id: String, @Body user: Plant): Plant
    @DELETE("plants/{id}")
    suspend fun deletePlant(@Path("id") id: String)
}