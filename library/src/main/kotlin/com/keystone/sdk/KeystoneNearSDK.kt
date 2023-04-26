package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.NativeUR
import com.keystone.module.NearSignRequest
import com.keystone.module.NearSignature
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneNearSDK : KeystoneBaseSDK() {
    fun parseSignature(ur: UR): NearSignature {
        val jsonStr = native.parseNearSignature(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, NearSignature::class.java)
        return handleError(jsonStr, result)
    }

    fun generateSignRequest(nearSignRequest: NearSignRequest): UREncoder {
        val jsonStr = native.generateNearSignRequest(
            nearSignRequest.requestId,
            toJson(nearSignRequest.signData),
            nearSignRequest.path,
            nearSignRequest.xfp,
            nearSignRequest.account,
            nearSignRequest.origin,
        )
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
