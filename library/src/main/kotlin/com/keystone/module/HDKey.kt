package com.keystone.module

enum class Note(val value: String) {
    STANDARD("account.standard"),
    LEDGER_LEGACY("account.ledger_legacy"),
    LEDGER_LIVE("account.ledger_live"),
}

data class HDKey (
    val key: String,
    val chainCode: String,
    val sourceFingerprint: String,
    val note: String,
)

data class MultiHDKeys (
    val hdKeys: ArrayList<HDKey>,
)
