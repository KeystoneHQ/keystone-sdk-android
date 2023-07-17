package com.keystone.sdk

import com.keystone.module.Account
import com.keystone.module.MultiAccounts
import com.keystone.module.NativeResult
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
    val evm: KeystoneEvmSDK by lazy {
        KeystoneEvmSDK()
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
    val near: KeystoneNearSDK by lazy {
        KeystoneNearSDK()
    }
    val arweave: KeystoneArweaveSDK by lazy {
        KeystoneArweaveSDK()
    }
    val sui: KeystoneSuiSDK by lazy {
        KeystoneSuiSDK()
    }
    val cardano: KeystoneCardanoSDK by lazy {
        KeystoneCardanoSDK()
    }

    fun parseMultiAccounts(ur: UR): MultiAccounts {
        val jsonStr = native.parseCryptoMultiAccounts(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, MultiAccounts::class.java)
        return handleError(jsonStr, result)
    }

    fun parseAccount(ur: UR): Account {
        val jsonStr = native.parseCryptoHDKey(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, Account::class.java)
        return handleError(jsonStr, result)
    }

    fun parseCryptoAccount(ur: UR): MultiAccounts {
        val jsonStr = native.parseCryptoAccount(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, MultiAccounts::class.java)
        return handleError(jsonStr, result)
    }

    fun getUncompressedKey(compressedKey: String): String {
        val jsonStr = native.getUncompressedKey(compressedKey)
        val result = fromJson(jsonStr, NativeResult::class.java)
        return handleError(jsonStr, result).result
    }

    fun derivePublicKey(xpub: String, path: String): String {
        val jsonStr = native.derivePublicKey(xpub, path)
        val result = fromJson(jsonStr, NativeResult::class.java)
        return handleError(jsonStr, result).result
    }
}
