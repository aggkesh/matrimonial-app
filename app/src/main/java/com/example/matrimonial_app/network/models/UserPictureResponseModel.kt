package com.example.matrimonial_app.network.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * Response Model holding user picture data
 */
data class UserPictureResponseModel(
    @JsonProperty("large")
    val large: String?,
    @JsonProperty("medium")
    val medium: String?,
    @JsonProperty("thumbnail")
    val thumbnail: String?
)