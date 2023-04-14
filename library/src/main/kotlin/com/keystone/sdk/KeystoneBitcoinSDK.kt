package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.NativeUR
import com.keystone.module.PSBT
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneBitcoinSDK: KeystoneBaseSDK() {
    fun parsePSBT(ur: UR): ByteArray {
        val jsonStr = native.parseCryptoPSBT(ur.type, ur.cborBytes.toHexString())
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, PSBT::class.java))
        return result.psbt.decodeHex()
    }

    fun generatePSBT(psbt: ByteArray): UREncoder {
        val jsonStr = native.generateCryptoPSBT(psbt.toHexString())
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
