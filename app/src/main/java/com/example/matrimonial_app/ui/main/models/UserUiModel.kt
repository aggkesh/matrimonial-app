package com.example.matrimonial_app.ui.main.models

/**
 * Created by Keshav Aggarwal 11/4/2020
 *
 * Data class used to represent user on ui
 */
data class UserUiModel(
    val userId: String,
    val name: String,
    val address: String,
    val dob: String,
    val number: String,
    val imageUrl: String?,
    val selected: Boolean?,
    var acceptDeclineBtnEnabled: Boolean = true
)