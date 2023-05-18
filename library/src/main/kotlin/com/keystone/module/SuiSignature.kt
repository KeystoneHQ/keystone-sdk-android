package com.keystone.module

data class SuiSignature(
    val requestId: String,
    val signature: String,
    val publicKey: String
)
