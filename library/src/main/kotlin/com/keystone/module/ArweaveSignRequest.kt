package com.keystone.module

import com.keystone.sdk.KeystoneArweaveSDK

data class ArweaveSignRequest(
    val requestId: String,
    val signData: String,
    val signType: KeystoneArweaveSDK.SignType,
    val saltLen: KeystoneArweaveSDK.SaltLen,
    val masterFingerprint: String,
    val account: String = "",
    val origin: String = "",
)
