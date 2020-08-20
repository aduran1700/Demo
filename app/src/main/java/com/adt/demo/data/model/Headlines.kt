package com.adt.demo.data.model

data class Headlines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)