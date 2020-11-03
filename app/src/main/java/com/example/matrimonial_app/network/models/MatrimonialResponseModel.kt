package com.example.matrimonial_app.network.models

import com.fasterxml.jackson.annotation.JsonProperty

data class MatrimonialResponseModel(
    @JsonProperty("results")
    val userResponseModelList: List<UserResponseModel>?
)