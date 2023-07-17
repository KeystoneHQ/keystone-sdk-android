package com.keystone.module

import com.keystone.sdk.KeystoneEvmSDK

data class EvmAccount(
    val path: String,
    val xfp: String,
    val address: String = "",
)

data class EvmSignRequest(
    val requestId: String,
    val signData: String,
    val dataType: KeystoneEvmSDK.DataType,
    val customChainIdentifier: Int,
    val account: EvmAccount,
    val origin: String = "",
)
