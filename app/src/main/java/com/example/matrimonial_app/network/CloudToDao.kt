package com.example.matrimonial_app.network

import com.example.matrimonial_app.database.entity.Address
import com.example.matrimonial_app.database.entity.Picture
import com.example.matrimonial_app.database.entity.PostCode
import com.example.matrimonial_app.database.entity.User
import com.example.matrimonial_app.database.entity.UserDetail
import com.example.matrimonial_app.network.models.UserLocationResponseModel
import com.example.matrimonial_app.network.models.UserPictureResponseModel
import com.example.matrimonial_app.network.models.UserResponseModel
import kotlin.jvm.Throws

object CloudToDao {

    @Throws(NumberFormatException::class)
    fun fromUserResponseModelToUserDao(userResponseModel: UserResponseModel): User? {
        val userLoginResponseModel = userResponseModel.userLoginResponseModel ?: return null
        val userDetail = fromUserResponseModelToUserDetail(userResponseModel) ?: return null

        if (userLoginResponseModel.id.isNullOrEmpty() ||
            userLoginResponseModel.username.isNullOrEmpty() ||
            userLoginResponseModel.password.isNullOrEmpty() ||
            userResponseModel.email.isNullOrEmpty()
        ) return null

        val address = userResponseModel.userLocationResponseModel?.let {
            fromUserLocationResponseModelToAddress(it)
        }

        return User(
            uuid = userLoginResponseModel.id,
            username = userLoginResponseModel.username,
            password = userLoginResponseModel.password,
            email = userResponseModel.email,
            userDetail = userDetail,
            address = address
        )
    }

    @Throws(NumberFormatException::class)
    private fun fromUserLocationResponseModelToAddress(userLocationResponseModel: UserLocationResponseModel): Address? {
        // Validate the Location response
        userLocationResponseModel.run {
            if ((street == null || street.name.isNullOrEmpty() && street.number.isNullOrEmpty()) &&
                postcode.isNullOrEmpty() &&
                city.isNullOrEmpty() &&
                state.isNullOrEmpty() &&
                country.isNullOrEmpty()
            ) return null
        }

        return Address(
            streetNumber = userLocationResponseModel.street?.number?.toLong(),
            streetName = userLocationResponseModel.street?.name,
            postCode = PostCode(
                postCode = userLocationResponseModel.postcode,
                city = userLocationResponseModel.city,
                state = userLocationResponseModel.state,
                country = userLocationResponseModel.country
            )
        )
    }

    private fun fromUserResponseModelToUserDetail(userResponseModel: UserResponseModel): UserDetail? {
        userResponseModel.gender ?: return null
        userResponseModel.userNameResponseModel?.first ?: return null

        return UserDetail(
            gender = userResponseModel.gender,
            firstName = userResponseModel.userNameResponseModel.first,
            lastName = userResponseModel.userNameResponseModel.last,
            picture = userResponseModel.userPictureResponseModel?.let {
                fromUserPictureResponseModelToPicture(it)
            }
        )
    }

    private fun fromUserPictureResponseModelToPicture(
        userPictureResponseModel: UserPictureResponseModel
    ): Picture {
        return Picture(
            userPictureResponseModel.large,
            userPictureResponseModel.medium,
            userPictureResponseModel.thumbnail
        )
    }
}