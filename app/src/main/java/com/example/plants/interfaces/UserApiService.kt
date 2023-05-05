package com.example.plants.interfaces

import com.example.plants.data.Users
import retrofit2.http.*

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<Users>

    @POST("users")
    suspend fun addUser(@Body user: Users): Users

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: Users): Users
    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: String)
}