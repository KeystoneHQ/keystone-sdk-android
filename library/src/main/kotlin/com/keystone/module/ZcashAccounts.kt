package com.keystone.module

data class ZcashAccounts (
    val seedFingerprint: String,
    val accounts: List<Account>,
)

data class ZcashAccount(
    val ufvk: String,
    val index: Int,
    val name: String?
)
