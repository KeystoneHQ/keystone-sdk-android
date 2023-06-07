package com.keystone.module

enum class Note(val value: String) {
    STANDARD("account.standard"),
    LEDGER_LEGACY("account.ledger_legacy"),
    LEDGER_LIVE("account.ledger_live"),
}

data class OkxExtra(
    var chainId: Int
)

data class Extra(
    var okx: OkxExtra
)

data class Account(
    var chain: String,
    var path: String,
    var publicKey: String,
    var name: String,
    var xfp: String,
    val note: String?,
    var extra: Extra,
    private var chainCode: String,
    private var extendedPublicKey: String,
) {
    fun getChainCode(): String {
        return chainCode
    }

    fun getExtendedPublicKey(): String {
        return extendedPublicKey
    }
}
