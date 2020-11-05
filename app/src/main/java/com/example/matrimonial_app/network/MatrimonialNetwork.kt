package com.example.matrimonial_app.network

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * Network class used to create the [MatrimonialUserService]
 */
object MatrimonialNetwork {
    private const val CONTENT_TYPE = "Content-Type"
    private const val APPLICATION_JSON = "application/json"
    private const val BASE_URL = "https://randomuser.me/"

    private val loggingInterceptor: HttpLoggingInterceptor
        get() {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    private val httpClient: OkHttpClient
        get() {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .build()
                    chain.proceed(request)
                }
                .addInterceptor(loggingInterceptor)
                .build()
        }

    private val retrofit: Retrofit
        get() {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    JacksonConverterFactory.create(
                        ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    )
                )
                .client(httpClient)
                .build()
        }

    val matrimonialUserService: MatrimonialUserService = retrofit.create(MatrimonialUserService::class.java)
}