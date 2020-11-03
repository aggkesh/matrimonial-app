package com.example.matrimonial_app.network.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * Response Model holding user dob
 */
data class UserDobResponseModel(
    @JsonProperty("date")
    val data: String?,
    @JsonProperty("age")
    val age: String?
)