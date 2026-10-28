package com.keystone.sdk

import com.keystone.module.NativeUR
import com.keystone.module.Signature
import com.keystone.module.TronNativeSignRequest
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneTronNativeSDK: KeystoneBaseSDK() {
    fun generateSignRequest(request: TronNativeSignRequest): UREncoder {
        val jsonStr = native.generateTronNativeSignRequest(request.requestId, request.signData,
            request.path, request.xfp, request.dataType, request.address, request.origin)
        return encodeQR(handleError(jsonStr, fromJson(jsonStr, NativeUR::class.java)))
    }

    fun parseSignature(ur: UR): Signature {
        val jsonStr = native.parseTronNativeSignature(ur.type, ur.cborBytes.toHexString())
        return handleError(jsonStr, fromJson(jsonStr, Signature::class.java))
    }
}
