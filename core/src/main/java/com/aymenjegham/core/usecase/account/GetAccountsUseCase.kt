package com.aymenjegham.core.usecase.account


import com.aymenjegham.core.domain.account.Account
import com.aymenjegham.core.helper.Response
import kotlinx.coroutines.flow.Flow

interface GetAccountsUseCase {

    operator fun invoke():Flow<Response<List<Account>>>
}