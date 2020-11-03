package com.example.matrimonial_app.ui.main

data class UserUiModel(
    val name: String,
    val address: String,
    val age: Int,
    val number: String,
    val imageUrl: String?,
    val selected: Boolean?
)