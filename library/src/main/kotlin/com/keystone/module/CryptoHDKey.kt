package com.keystone.module

import com.google.gson.annotations.SerializedName

public data class CryptoHDKey(
    @SerializedName("is_master") val isMaster: Boolean,
    @SerializedName("is_private_key") val isPrivateKey: Boolean,
    val key: String,
    @SerializedName("chain_code") val chainCode: String,
    @SerializedName("use_info") val useInfo: String,
    val origin: String,
    val children: String,
    @SerializedName("parent_fingerprint") val parentFingerprint: String,
    val name: String,
    val note: String,
)
