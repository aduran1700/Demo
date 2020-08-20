package com.adt.demo.data.model

/**
 * Holds the state for the view to update
 * with
 */
sealed class ResponseState {
    data class Success(val list: List<Article>): ResponseState()
    data class Error(val error: String?): ResponseState()
    object Empty: ResponseState()
    object Loading: ResponseState()
}