package com.example.matrimonial_app.network.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * Response Model holding username data
 */
data class UserNameResponseModel(
    @JsonProperty("title")
    val title: String,
    @JsonProperty("first")
    val first: String,
    @JsonProperty("last")
    val last: String
)