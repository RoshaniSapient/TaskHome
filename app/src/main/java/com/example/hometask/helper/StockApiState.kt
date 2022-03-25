package com.example.hometask.helper

data class StockApiState<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): StockApiState<T> {
            return StockApiState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): StockApiState<T> {
            return StockApiState(Status.ERROR, null, msg)
        }
        fun <T> loading(): StockApiState<T> {
            return StockApiState(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}