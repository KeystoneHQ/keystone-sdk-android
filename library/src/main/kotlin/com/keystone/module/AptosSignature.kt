package com.keystone.module

import com.google.gson.annotations.SerializedName

data class AptosSignature(
    @SerializedName("request_id") val requestId: String,
    val signature: String,
    @SerializedName("authentication_public_key") val authenticationPublicKey: String
)
