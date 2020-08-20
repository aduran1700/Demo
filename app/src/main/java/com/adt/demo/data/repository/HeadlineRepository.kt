package com.adt.demo.data.repository

import com.adt.demo.api.HeadlineApi
import io.reactivex.schedulers.Schedulers

class HeadlineRepository(
    private val api: HeadlineApi
) {

    fun getHeadlineData() = api.getHeadLines()
        .map { it.articles }
        .onErrorReturn { emptyList() }
        .subscribeOn(Schedulers.io())
}