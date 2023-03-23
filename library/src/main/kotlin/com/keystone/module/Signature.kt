package com.keystone.module

import com.google.gson.annotations.SerializedName

data class Signature(
    @SerializedName("request_id") val requestId: String,
    val signature: String,
)
