package com.keystone.module

import com.google.gson.annotations.SerializedName

data class PSBTExtend(
    val psbt: ByteArray,
    val coinId: Int?
)

internal data class NativePSBTExtend(
    val psbt: String,
    @SerializedName("coin_id") val coinId: Int?
)
