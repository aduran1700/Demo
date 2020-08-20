package com.adt.demo.api

import com.adt.demo.data.model.Headlines
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HeadlineApi {

    @GET("v2/top-headlines")
    fun getHeadLines(@Query("country") country: String = "us"): Single<Headlines>
}