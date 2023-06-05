package com.keystone.module

data class MultiAccounts (
    val device: String?,
    val keys: List<Account>,
    val masterFingerprint: String,
)
