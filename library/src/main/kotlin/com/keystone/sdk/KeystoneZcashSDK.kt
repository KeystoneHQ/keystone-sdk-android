package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.NativeUR
import com.keystone.module.ZcashPCZT
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneZcashSDK: KeystoneBaseSDK() {
    fun parsePczt(ur: UR): ByteArray {
        val jsonStr = native.parseZcashPczt(ur.type, ur.cborBytes.toHexString())
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, ZcashPCZT::class.java))
        return result.pczt.decodeHex()
    }

    fun generatePczt(pczt: ByteArray): UREncoder {
        val jsonStr = native.generateZcashPczt(pczt.toHexString())
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
