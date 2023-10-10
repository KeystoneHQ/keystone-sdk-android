package com.keystone.module

data class MultiAccounts (
    val masterFingerprint: String,
    val keys: List<Account>,
    val device: String?,
    val deviceId: String?,
    val deviceVersion: String?,
)
