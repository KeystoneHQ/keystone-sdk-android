package com.keystone.module

import com.keystone.sdk.KeystoneAptosSDK

data class AptosAccount(
    val path: String,
    val xfp: String,
    val key: String = "",
)

data class AptosSignRequest(
    val requestId: String,
    val signData: String,
    val signType: KeystoneAptosSDK.SignType,
    val accounts: List<AptosAccount>,
    val origin: String = "",
)
