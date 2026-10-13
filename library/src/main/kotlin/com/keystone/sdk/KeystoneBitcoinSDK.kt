package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.NativeUR
import com.keystone.module.PSBT
import com.keystone.module.PSBTExtend
import com.keystone.module.NativePSBTExtend
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

    /** coinId: 0 Bitcoin, 2 Litecoin, 3 Dogecoin, 4 Dash, 145 Bitcoin Cash. */
    @JvmOverloads
    fun generatePSBTExtend(psbt: ByteArray, coinId: Int = 0): UREncoder {
        val jsonStr = native.generateCryptoPSBTExtend(psbt.toHexString(), coinId)
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }

    /** coinId is null when the payload omits the coin field (Bitcoin). */
    fun parsePSBTExtend(ur: UR): PSBTExtend {
        val jsonStr = native.parseCryptoPSBTExtend(ur.type, ur.cborBytes.toHexString())
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativePSBTExtend::class.java))
        return PSBTExtend(result.psbt.decodeHex(), result.coinId)
    }
}
