package com.keystone.module

data class MultiAccounts (
    val device: String,
    val keys: Array<Account>,
    val masterFingerprint: String,
)
