package com.example.matrimonial_app.network.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * Response Model holding user login data
 */
data class UserLoginResponseModel(
    @JsonProperty("uuid")
    val id: String?,
    @JsonProperty("username")
    val username: String?,
    @JsonProperty("password")
    val password: String?
)