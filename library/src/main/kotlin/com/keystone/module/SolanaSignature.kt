package com.keystone.module

import com.google.gson.annotations.SerializedName

public data class SolanaSignature(
    @SerializedName("request_id") val requestId: String,
    val signature: String,
)
