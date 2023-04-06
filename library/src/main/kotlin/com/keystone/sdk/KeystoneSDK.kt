package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.MultiAccounts
import com.keystone.module.HDKey
import com.keystone.module.MultiHDKeys

class KeystoneSDK(): KeystoneBaseSDK() {
    companion object {
        var maxFragmentLen: Int = 100
    }

    val eth: KeystoneEthereumSDK by lazy {
        KeystoneEthereumSDK()
    }
    val sol: KeystoneSolanaSDK by lazy {
        KeystoneSolanaSDK()
    }
    val btc: KeystoneBitcoinSDK by lazy {
        KeystoneBitcoinSDK()
    }

    fun parseMultiAccounts(cborHex: String): MultiAccounts {
        val jsonStr = native.parseCryptoMultiAccounts(cborHex)
        val result = Gson().fromJson(jsonStr, MultiAccounts::class.java)
        return handleError(jsonStr, result)
    }

    fun parseSourceHDKey(cborHex: String): HDKey {
        val jsonStr = native.getSourceHDKey(cborHex)
        val result = Gson().fromJson(jsonStr, HDKey::class.java)
        return handleError(jsonStr, result)
    }

    fun parseHDKeys(cborHex: String): MultiHDKeys {
        val jsonStr = native.getHDKeys(cborHex)
        val result = Gson().fromJson(jsonStr, MultiHDKeys::class.java)
        return handleError(jsonStr, result)
    }

    fun getUncompressedKey(compressedKey: String): String {
        val jsonStr = native.getUncompressedKey(compressedKey)
        return handleError(jsonStr, jsonStr)
    }
}
