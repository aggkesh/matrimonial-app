package com.example.matrimonial_app.ui.main

import com.example.matrimonial_app.database.entity.Address
import com.example.matrimonial_app.database.entity.User
import com.example.matrimonial_app.database.entity.UserDetail

object DoToUiModel {
    private const val EMPTY_STRING = ""

    fun fromUserToUserUiModel(user: User): UserUiModel {
        return UserUiModel(
            name = getName(user.userDetail),
            address = getAddress(user.address),
            age = 26,
            number = getUserNumber(user.userDetail),
            imageUrl = user.userDetail.picture?.large,
            selected = user.acceptedMatch
        )
    }

    private fun getUserNumber(userDetail: UserDetail): String {
        return if (userDetail.phoneNumber.isNullOrBlank() && userDetail.cellNumber.isNullOrBlank()) {
            "Unknown Number"
        } else {
            "${getDefaultString(userDetail.phoneNumber)} | ${getDefaultString(userDetail.cellNumber)}"
        }
    }

    private fun getName(userDetail: UserDetail): String {
        return "${userDetail.firstName} ${getDefaultString(userDetail.lastName)}".takeIf {
            it.isNotBlank()
        } ?: "Unknown Name"
    }

    private fun getAddress(address: Address?): String {
        return address?.let {
            "${getDefaultString(address.streetNumber.toString())} " +
                    "${getDefaultString(address.streetName)} " +
                    "${getDefaultString(address.postCode.city)} " +
                    "${getDefaultString(address.postCode.state)} " +
                    "${getDefaultString(address.postCode.country)} " +
                    "${getDefaultString(address.postCode.postCode)} "
        }.takeIf { !it.isNullOrBlank() } ?: "Unknown Address"
    }

    private fun getDefaultString(value: String?): String = value ?: EMPTY_STRING
}