package com.aymenjegham.orange.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.aymenjegham.core.helper.Response
import com.aymenjegham.core.usecase.account.GetAccountsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.aymenjegham.core.domain.account.Account
import com.aymenjegham.framework.di.dispatcher.DispatcherProvider
import com.aymenjegham.orange.global.helper.SingleEventLiveDataEvent


@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application,
    private val getAccountsUseCase: GetAccountsUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : AndroidViewModel(app) {

    private val _response = MutableLiveData<Response<List<Account>>>()
    val response = _response

    private val _accountList = MutableLiveData<List<Account>>()
    val accountList = _accountList

    private val _selectedAccount = MutableLiveData<Account>()
    val selectedAccount = _selectedAccount

    private val _showAccountsListDialog =
        SingleEventLiveDataEvent<Triple<List<Account>, (Int) -> Unit, Int>>()
    val showAccountsListDialog: LiveData<Triple<List<Account>, (Int) -> Unit, Int>>
        get() = _showAccountsListDialog


    init {
        fetchAccounts()
    }

    fun fetchAccounts() {

        viewModelScope.launch() {
            getAccountsUseCase().collect {
                _response.value = it
            }
        }
    }

    fun setAccountsList(accounts: List<Account>) {
        _accountList.value = accounts
        _selectedAccount.value = accounts.first()
    }

    fun showAccountsDialog() {
        accountList.value?.let {

            val initialIndex = it.indexOf(selectedAccount.value)

            if (it.isNotEmpty()) {

                _showAccountsListDialog.value = Triple(it, onAccountSelected, initialIndex)
            } else {
                //  showSimpleDialog(R.sttle, R.string.empty_team_list)
            }
        }
    }

    private val onAccountSelected: (Int) -> Unit = { position ->
        accountList.value?.let {
            val account = it[position]
            _selectedAccount.value = account
        }
    }

    fun navigate(): (Int) -> Unit = {
//        _accountsList.value?.data?.get(it)?.let { photoItem ->
//            navigate(Navigation.NavigationDetails(photoItem))
//        }
    }
}