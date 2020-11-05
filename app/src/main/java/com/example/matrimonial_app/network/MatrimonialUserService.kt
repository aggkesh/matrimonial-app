package com.example.matrimonial_app.network

import com.example.matrimonial_app.network.models.MatrimonialResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * A retrofit service to fetch matrimonial users
 */
interface MatrimonialUserService {

    /**
     * Fetch Users
     *
     * @param result number of users to fetch
     * @param seed seed value for user
     */
    @GET("api/")
    suspend fun fetchUsers(@Query("results") result: Int, @Query("seed") seed: String): MatrimonialResponseModel
}