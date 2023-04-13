package com.keystone.module

import java.io.Serializable

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
    val path: String,
    val xpub: String,
): Serializable

data class MultiHDKeys (
    val hdKeys: ArrayList<HDKey>,
): Serializable
