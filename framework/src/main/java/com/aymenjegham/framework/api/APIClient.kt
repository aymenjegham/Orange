package com.aymenjegham.framework.api

import com.aymenjegham.core.domain.account.ApiResponse
import retrofit2.Response
import retrofit2.http.GET


interface APIClient {

    @GET("ea42529b-1a24-4f3e-9ba4-8e6665666d6b")
    suspend fun getAllAccounts(): Response<ApiResponse>


}