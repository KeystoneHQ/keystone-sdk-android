package com.keystone.module

data class TronSignRequest(
    val requestId: String,
    val signData: String,
    val path: String,
    val xfp: String,
    val tokenInfo: TokenInfo? = null,
    val address: String = "",
    val origin: String = "",
)
