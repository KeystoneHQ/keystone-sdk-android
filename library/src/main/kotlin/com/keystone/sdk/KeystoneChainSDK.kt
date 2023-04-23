package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.KeystoneSignResult
import com.sparrowwallet.hummingbird.UR

open class KeystoneChainSDK: KeystoneBaseSDK() {
    enum class CoinType(val value: Int) {
        LTC(2),
        DASH(5),
        BCH(145)
    }

    fun parseSignResult(ur: UR): KeystoneSignResult {
        val jsonStr = native.parseKeystoneSignResult(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, KeystoneSignResult::class.java)
        return handleError(jsonStr, result)
    }
}
