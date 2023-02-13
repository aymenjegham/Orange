package com.aymenjegham.core.data.repository.account

import com.aymenjegham.core.domain.account.Account
import com.aymenjegham.core.helper.Response
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun getAllAccounts(): Flow<Response<List<Account>>>


}