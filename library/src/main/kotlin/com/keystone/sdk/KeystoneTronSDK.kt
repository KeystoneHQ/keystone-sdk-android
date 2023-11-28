package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.NativeUR
import com.keystone.module.TronSignRequest
import com.keystone.module.TronSignature
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneTronSDK: KeystoneBaseSDK() {
    fun parseSignature(ur: UR): TronSignature {
        val jsonStr = native.parseTronSignature(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, TronSignature::class.java)
        return handleError(jsonStr, result)
    }

    fun generateSignRequest(tronSignRequest: TronSignRequest): UREncoder {
        val jsonStr = native.generateTronSignRequest(
            tronSignRequest.requestId,
            tronSignRequest.signData,
            tronSignRequest.path,
            tronSignRequest.xfp,
            if (tronSignRequest.tokenInfo == null) "" else Gson().toJson(tronSignRequest.tokenInfo),
            tronSignRequest.origin,
            getTimestamp(),
        )
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
