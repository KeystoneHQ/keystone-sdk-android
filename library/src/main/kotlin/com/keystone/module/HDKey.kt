package com.keystone.module

import com.google.gson.annotations.SerializedName

enum class Note(val value: String) {
    STANDARD("account.standard"),
    LEDGER_LEGACY("account.ledger_legacy"),
    LEDGER_LIVE("account.ledger_live"),
}

data class HDKey (
    val key: String,
    @SerializedName("chain_code") val chainCode: String,
    @SerializedName("source_fingerprint") val sourceFingerprint: String,
    val note: String,
)

data class MultiHDKeys (
    @SerializedName("hd_keys") val hdKeys: Array<HDKey>,
)