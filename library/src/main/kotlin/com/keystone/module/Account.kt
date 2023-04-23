package com.keystone.module

data class Account(
    var chain: String,
    var path: String,
    var publicKey: String,
    var name: String,
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
