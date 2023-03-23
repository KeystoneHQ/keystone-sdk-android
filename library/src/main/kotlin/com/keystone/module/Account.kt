package com.keystone.module

class Account(
    var chain: String,
    var path: String,
    var publicKey: String,
    var name: String,
    private var chainCode: String
) {
    fun getExtendedPublicKey(): String {
        // TODO: implement
        return publicKey + chainCode
    }
}
