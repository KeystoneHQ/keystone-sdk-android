package com.keystone.module

import com.keystone.sdk.KeystoneSuiSDK

data class SuiAccount(
    val path: String,
    val xfp: String,
    val address: String = "",
)

data class SuiSignRequest(
    val requestId: String,
    val signData: String,
    val signType: KeystoneSuiSDK.SignType,
    val accounts: List<SuiAccount>,
    val origin: String = "",
)
