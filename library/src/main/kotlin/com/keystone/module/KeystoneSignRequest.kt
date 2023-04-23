package com.keystone.module

data class KeystoneSignRequest<T>(
    val requestId: String,
    val signData: T,
    val xfp: String,
    val origin: String = "",
)
