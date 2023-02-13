package com.aymenjegham.orange.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aymenjegham.core.domain.account.Account
import com.aymenjegham.core.helper.Response
import com.aymenjegham.framework.global.helper.Navigation
import com.aymenjegham.orange.databinding.FragmentHomeBinding
import com.aymenjegham.orange.ui.dialog.ListDialog
import com.aymenjegham.orange.ui.home.adapter.AccountListAdapter
import com.aymenjegham.orange.ui.home.adapter.AccountListHolder

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*


@AndroidEntryPoint
class HomeFragment : Fragment() {


    private val viewModel by viewModels<HomeViewModel>()


    private var accountListDialog: ListDialog<AccountListHolder, Account>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding =
            FragmentHomeBinding.inflate(layoutInflater, container, false)

        bind(binding)

        registerHomeObservers(binding)
        registerShowAccountListDialogObserver()

        return binding.root
    }


    private fun bind(binding: FragmentHomeBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

    }


    private fun registerHomeObservers(binding: FragmentHomeBinding) {
        registerSubmitAdapter()
        setupRefresh(binding)
    }

    private fun registerShowAccountListDialogObserver() {
        viewModel.showAccountsListDialog.observe(
            viewLifecycleOwner
        ) { (accountList, selectAction, initialIndex) ->
            accountListDialog(accountList, selectAction, initialIndex)
        }

    }

    private fun accountListDialog(
        accountsList: List<Account>,
        selectAction: (Int) -> Unit,
        initialIndex: Int,
    ) {
        val isSelected = ObservableBoolean(false)


        val adapter = AccountListAdapter(initialIndex) {
            selectAction(it)
            accountListDialog?.dismiss()
        }

        accountListDialog = ListDialog<AccountListHolder, Account>(
            requireContext(),
            "Accounts",
            adapter,
            isSelected,
            accountsList,
        ).apply {
            setOnCancelListener { accountListDialog = null }
            show()
        }

    }

    private fun registerSubmitAdapter() {
        viewModel.response.observe(viewLifecycleOwner) { result ->

            when (result.status) {
                Response.Status.SUCCESS -> {
                    result.data?.let { accounts ->
                        viewModel.setAccountsList(accounts)
                    }
                    loading.visibility = View.GONE
                }
                Response.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }
                    loading.visibility = View.GONE
                }

                Response.Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
            }
        }
    }


    private fun setupRefresh(binding: FragmentHomeBinding) {
        binding.refresh.setOnRefreshListener {
            viewModel.fetchAccounts()
            binding.refresh.isRefreshing = false
        }
    }

    private fun showError(msg: String) {
        //viewModel.showSnackBar(msg) { viewModel.fetchAccounts() }
    }

    fun navigate(navigationTo: Navigation) {

    }


}