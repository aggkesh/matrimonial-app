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

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * Convert Cloud Response Model to Database object
 */
object CloudToDao {

    /**
     * Convert [UserResponseModel] to [User]
     */
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

    /**
     * Convert [UserLocationResponseModel] to [Address]
     */
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

    /**
     * Convert [UserResponseModel] to [UserDetail]
     */
    private fun fromUserResponseModelToUserDetail(userResponseModel: UserResponseModel): UserDetail? {
        userResponseModel.gender ?: return null
        userResponseModel.userNameResponseModel?.first ?: return null
        userResponseModel.userDobResponseModel?.data ?: return null

        return UserDetail(
            gender = userResponseModel.gender,
            firstName = userResponseModel.userNameResponseModel.first,
            lastName = userResponseModel.userNameResponseModel.last,
            cellNumber = userResponseModel.cell,
            phoneNumber = userResponseModel.phone,
            dateOfBirth = userResponseModel.userDobResponseModel.data,
            picture = userResponseModel.userPictureResponseModel?.let {
                fromUserPictureResponseModelToPicture(it)
            }
        )
    }

    /**
     * Convert [UserPictureResponseModel] to [Picture]
     */
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