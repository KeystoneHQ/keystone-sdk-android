package com.keystone.module

data class CosmosSignature(
    val requestId: String,
    val signature: String,
    val publicKey: String
)
