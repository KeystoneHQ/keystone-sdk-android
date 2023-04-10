package com.keystone.module

import com.google.gson.annotations.SerializedName

data class MultiAccounts (
    val device: String,
    val keys: List<Account>,
    @SerializedName("master_fingerprint") val masterFingerprint: String,
)
