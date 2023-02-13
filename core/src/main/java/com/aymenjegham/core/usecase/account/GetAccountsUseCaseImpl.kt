package com.aymenjegham.core.usecase.account

import com.aymenjegham.core.data.repository.account.AccountRepository
import com.aymenjegham.core.domain.account.Account
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.aymenjegham.core.helper.Response


class GetAccountsUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : GetAccountsUseCase {

    override fun invoke(): Flow<Response<List<Account>>> = accountRepository.getAllAccounts()

}