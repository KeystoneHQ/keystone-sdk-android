package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.SuiSignRequest
import com.keystone.module.SuiSignature
import com.keystone.module.NativeUR
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneSuiSDK: KeystoneBaseSDK() {
    enum class SignType(val value: Int) {
        Single(1),
        Multi(2),
        Message(3),
    }

    fun parseSignature(ur: UR): SuiSignature {
        val jsonStr = native.parseSuiSignature(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, SuiSignature::class.java)
        return handleError(jsonStr, result)
    }

    fun generateSignRequest(suiSignRequest: SuiSignRequest): UREncoder {
        val jsonStr = native.generateSuiSignRequest(
            suiSignRequest.requestId,
            suiSignRequest.signData,
            suiSignRequest.signType.value,
            Gson().toJson(suiSignRequest.accounts),
            suiSignRequest.origin,
        )
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
