package com.keystone.module

import com.keystone.sdk.KeystoneEthereumSDK

data class EthSignRequest(
    val requestId: String,
    val signData: String,
    val dataType: KeystoneEthereumSDK.DataType,
    val chainId: Int,
    val path: String,
    val xfp: String,
    val address: String = "",
    val origin: String = "",
)
