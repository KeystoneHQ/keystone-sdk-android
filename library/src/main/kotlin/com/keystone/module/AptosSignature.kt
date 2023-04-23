package com.keystone.module

data class AptosSignature(
    val requestId: String,
    val signature: String,
    val authenticationPublicKey: String
)
