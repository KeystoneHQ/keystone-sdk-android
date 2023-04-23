package com.keystone.module

import com.keystone.sdk.KeystoneCosmosSDK

data class CosmosAccount(
    val path: String,
    val xfp: String,
    val address: String,
)

data class CosmosSignRequest(
    val requestId: String,
    val signData: String,
    val dataType: KeystoneCosmosSDK.DataType,
    val accounts: List<CosmosAccount>,
    val origin: String = "",
)
