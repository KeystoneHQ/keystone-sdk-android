package com.keystone.module

import com.keystone.sdk.KeystoneSolanaSDK

data class SolSignRequest(
    val requestId: String,
    val signData: String,
    val path: String,
    val xfp: String,
    val address: String = "",
    val origin: String = "",
    val signType: KeystoneSolanaSDK.SignType,
)
