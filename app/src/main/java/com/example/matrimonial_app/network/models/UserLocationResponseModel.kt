package com.example.matrimonial_app.network.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * Response Model holding user location data
 */
data class UserLocationResponseModel(
    @JsonProperty("street")
    val street: UserStreetResponseModel?,
    @JsonProperty("city")
    val city: String?,
    @JsonProperty("state")
    val state: String?,
    @JsonProperty("country")
    val country: String?,
    @JsonProperty("postcode")
    val postcode: String?
)

/**
 * Response Model holding user street data
 */
data class UserStreetResponseModel(
    @JsonProperty("number")
    val number: String?,
    @JsonProperty("name")
    val name: String?
)