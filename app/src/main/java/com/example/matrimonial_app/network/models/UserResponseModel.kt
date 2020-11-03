package com.example.matrimonial_app.network.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * Response Model holding user data
 */
data class UserResponseModel(
    @JsonProperty("gender")
    val gender: String?,
    @JsonProperty("name")
    val userNameResponseModel: UserNameResponseModel?,
    @JsonProperty("location")
    val userLocationResponseModel: UserLocationResponseModel?,
    @JsonProperty("email")
    val email: String?,
    @JsonProperty("login")
    val userLoginResponseModel: UserLoginResponseModel?,
    @JsonProperty("dob")
    val userDobResponseModel: UserDobResponseModel?,
    @JsonProperty("phone")
    val phone: String?,
    @JsonProperty("cell")
    val cell: String?,
    @JsonProperty("picture")
    val userPictureResponseModel: UserPictureResponseModel?
)