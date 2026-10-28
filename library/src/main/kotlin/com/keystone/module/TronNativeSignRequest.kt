package com.keystone.module

data class TronNativeSignRequest(
    val requestId: String,
    val signData: String,
    val path: String,
    val xfp: String,
    val dataType: Int = 1,
    val address: String = "",
    val origin: String = "",
)
