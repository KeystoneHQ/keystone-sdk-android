package com.keystone.module

import com.google.gson.annotations.SerializedName

data class Account(
    var chain: String,
    var path: String,
    @SerializedName("public_key") var publicKey: String,
    var name: String,
    @SerializedName("extended_public_key") private var extendedPublicKey: String,
) {
    fun getExtendedPublicKey(): String {
        return extendedPublicKey
    }
}
