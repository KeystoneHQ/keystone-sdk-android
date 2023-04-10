package com.keystone.module

import com.google.gson.annotations.SerializedName

data class CosmosSignature(
    @SerializedName("request_id") val requestId: String,
    val signature: String,
    @SerializedName("public_key") val publicKey: String
)
