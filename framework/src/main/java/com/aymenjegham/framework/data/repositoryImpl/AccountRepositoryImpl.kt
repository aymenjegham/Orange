package com.aymenjegham.framework.data.repositoryImpl

import android.content.Context
import com.aymenjegham.core.data.repository.account.AccountRepository
import com.aymenjegham.core.domain.account.Account
import com.aymenjegham.core.domain.account.ApiResponse
import com.aymenjegham.core.helper.Response

import com.aymenjegham.framework.api.APIClient
import com.aymenjegham.framework.di.dispatcher.DispatcherProvider
import com.aymenjegham.framework.global.extention.isNetworkAvailable
import com.aymenjegham.framework.global.utils.ErrorUtils

import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import javax.inject.Inject


class AccountRepositoryImpl @Inject constructor(
    private val api: APIClient,
    private val dispatchers: DispatcherProvider,
    private val retrofit: Retrofit,
    @ApplicationContext private val context: Context,
) : AccountRepository {
    override fun getAllAccounts(): Flow<Response<List<Account>>> {
        return flow {

            emit(Response.loading())
            val result = getAccountsResponse(
                request = { api.getAllAccounts() },
                defaultErrorMessage = "Error fetching photos list"
            )

            when (result.status) {
                Response.Status.SUCCESS -> {
                    emit(Response.success(result.data))
                }
                else -> {
                    emit(Response.error(result.message ?: "No Error Message", result.error))
                }
            }
        }.flowOn(dispatchers.io)
    }

    private suspend fun getAccountsResponse(
        request: suspend () -> retrofit2.Response<ApiResponse>,
        defaultErrorMessage: String,
    ): Response<List<Account>> {
        return try {
            val result = request()
            if (result.isSuccessful) {
                return Response.success(result.body()?.Data?.Account)
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Response.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            if (context.isNetworkAvailable()) {
                Response.error("Unknown Error ", null)
            } else {
                Response.error("No network available", null)
            }

        }
    }

}