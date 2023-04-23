package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.MultiAccounts
import com.keystone.module.HDKey
import com.keystone.module.MultiHDKeys
import com.sparrowwallet.hummingbird.UR

class KeystoneSDK(): KeystoneBaseSDK() {
    companion object {
        var maxFragmentLen: Int = 400
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
    val cosmos: KeystoneCosmosSDK by lazy {
        KeystoneCosmosSDK()
    }
    val tron: KeystoneTronSDK by lazy {
        KeystoneTronSDK()
    }
    val aptos: KeystoneAptosSDK by lazy {
        KeystoneAptosSDK()
    }
    val ltc: KeystoneLtcSDK by lazy {
        KeystoneLtcSDK()
    }
    val bch: KeystoneBchSDK by lazy {
        KeystoneBchSDK()
    }
    val dash: KeystoneDashSDK by lazy {
        KeystoneDashSDK()
    }

    fun parseMultiAccounts(ur: UR): MultiAccounts {
        val jsonStr = native.parseCryptoMultiAccounts(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, MultiAccounts::class.java)
        return handleError(jsonStr, result)
    }

    fun parseExtendedPublicKey(ur: UR): HDKey {
        val jsonStr = native.parseExtendedPublicKey(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, HDKey::class.java)
        return handleError(jsonStr, result)
    }

    fun parseMultiPublicKeys(ur: UR): MultiHDKeys {
        val jsonStr = native.parseMultiPublicKeys(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, MultiHDKeys::class.java)
        return handleError(jsonStr, result)
    }

    fun getUncompressedKey(compressedKey: String): String {
        val jsonStr = native.getUncompressedKey(compressedKey)
        return handleError(jsonStr, jsonStr)
    }
}
