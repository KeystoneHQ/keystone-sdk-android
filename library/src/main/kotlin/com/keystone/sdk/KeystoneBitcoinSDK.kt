package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.PSBT
import com.keystone.module.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneBitcoinSDK: KeystoneBaseSDK() {
    fun parsePSBT(cborHex: String): ByteArray {
        val jsonStr = native.parseCryptoPSBT(cborHex)
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, PSBT::class.java))
        return result.psbt.decodeHex()
    }

    fun generatePSBT(psbt: ByteArray): UREncoder {
        val jsonStr = native.generateCryptoPSBT(psbt.toHexString())
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, UR::class.java))
        return encodeQR(result)
    }
}
