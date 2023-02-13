package com.aymenjegham.core.domain.account

data class Account(
    val Account: List<AccountX>,
    val AccountId: String,
    val AccountSubType: String,
    val AccountType: String,
    val Currency: String,
    val Nickname: String,
    val OpeningDate: String,
    val Status: String,
    val StatusUpdateDateTime: String,
    val transactionsUrl: String
)