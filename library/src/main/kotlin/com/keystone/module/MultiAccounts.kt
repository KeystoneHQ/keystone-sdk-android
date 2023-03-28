package com.keystone.module

import com.google.gson.annotations.SerializedName

data class MultiAccounts (
    val device: String,
    val keys: Array<Account>,
    @SerializedName("master_fingerprint") val masterFingerprint: String,
)
