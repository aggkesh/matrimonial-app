package com.example.matrimonial_app.network.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * Response Model container for parsing users data
 */
data class MatrimonialResponseModel(
    @JsonProperty("results")
    val userResponseModelList: List<UserResponseModel>?
)