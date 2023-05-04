package com.keystone.module

data class ArweaveAccount(
    val masterFingerprint: String,
    val keyData: String,
    val device: String,
)
