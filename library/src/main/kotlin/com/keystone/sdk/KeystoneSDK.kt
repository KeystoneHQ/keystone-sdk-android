package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.MultiAccounts

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
}
