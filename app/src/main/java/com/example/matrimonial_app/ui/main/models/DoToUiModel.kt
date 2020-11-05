package com.example.matrimonial_app.ui.main.models

import android.util.Log
import com.example.matrimonial_app.database.entity.Address
import com.example.matrimonial_app.database.entity.User
import com.example.matrimonial_app.database.entity.UserDetail
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by Keshav Aggarwal 11/4/2020
 *
 * Convert Database models to Ui models
 */
object DoToUiModel {
    private val TAG = DoToUiModel::class.java.name
    private const val EMPTY_STRING = ""
    private const val UNKNOWN_DOB = "Unknown Dob"
    private const val UNKNOWN_NUMBER = "Unknown Number"
    private const val UNKNOWN_NAME = "Unknown Name"
    private const val UNKNOWN_ADDRESS = "Unknown Address"
    private const val DATE_FORMAT_DAO = "yyyy-MM-dd'T'HH:mm:ss"
    private const val DATE_FORMAT_UI = "dd-MM-YYYY"
    private const val PHONE_TAG = "Ph:"

    /**
     * Convert [User] to [UserUiModel]
     */
    fun fromUserToUserUiModel(user: User): UserUiModel {
        return UserUiModel(
            userId = user.uuid,
            name = getName(user.userDetail),
            address = getAddress(user.address),
            dob = getDob(user.userDetail),
            number = getUserNumber(user.userDetail),
            imageUrl = user.userDetail.picture?.large,
            selected = user.acceptedMatch
        )
    }

    /**
     * Convert [UserDetail] to Ui presentable DOB
     */
    private fun getDob(userDetail: UserDetail): String {
        return try {
            SimpleDateFormat(DATE_FORMAT_DAO, Locale.getDefault())
                .parse(userDetail.dateOfBirth)?.let {
                    SimpleDateFormat(DATE_FORMAT_UI, Locale.getDefault()).format(it)
                } ?: UNKNOWN_DOB
        } catch (ex: Exception) {
            Log.e(TAG, "Error while parsing date " + ex.message)
            UNKNOWN_DOB
        }
    }

    /**
     * Convert [UserDetail] to Ui presentable Number
     */
    private fun getUserNumber(userDetail: UserDetail): String {
        return if (userDetail.phoneNumber.isNullOrBlank() && userDetail.cellNumber.isNullOrBlank()) {
            UNKNOWN_NUMBER
        } else {
            "$PHONE_TAG ${getDefaultString(userDetail.phoneNumber)} | ${getDefaultString(
                userDetail.cellNumber
            )}"
        }
    }

    /**
     * Convert [UserDetail] to Ui presentable NAME
     */
    private fun getName(userDetail: UserDetail): String {
        return "${userDetail.firstName} ${getDefaultString(
            userDetail.lastName
        )}".takeIf {
            it.isNotBlank()
        } ?: UNKNOWN_NAME
    }

    /**
     * Convert [UserDetail] to Ui presentable ADDRESS
     */
    private fun getAddress(address: Address?): String {
        return address?.let {
            "${getDefaultString(address.streetNumber.toString())} " +
                    "${getDefaultString(
                        address.streetName
                    )} " +
                    "${getDefaultString(
                        address.postCode.city
                    )} " +
                    "${getDefaultString(
                        address.postCode.state
                    )} " +
                    "${getDefaultString(
                        address.postCode.country
                    )} " +
                    "${getDefaultString(
                        address.postCode.postCode
                    )} "
        }.takeIf { !it.isNullOrBlank() } ?: UNKNOWN_ADDRESS
    }

    private fun getDefaultString(value: String?): String = value ?: EMPTY_STRING
}