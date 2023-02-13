package com.aymenjegham.core.helper


data class Response<out T>(val status: Status, val data: T?, val error: Error?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Response<T> {
            return Response(Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String, error: Error?): Response<T> {
            return Response(Status.ERROR, null, error, message)
        }

        fun <T> loading(data: T? = null): Response<T> {
            return Response(Status.LOADING, data, null, null)
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, error=$error, message=$message)"
    }
}