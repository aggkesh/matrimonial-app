package com.example.matrimonial_app.network

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


object MatrimonialNetwork {
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
                        .addHeader("Content-Type", "application/json")
                        .build()
                    chain.proceed(request)
                }
                .addInterceptor(loggingInterceptor)
                .build()
        }

    private val retrofit: Retrofit
        get() {
            return Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
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