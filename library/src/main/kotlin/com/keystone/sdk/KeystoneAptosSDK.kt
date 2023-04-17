package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.AptosSignRequest
import com.keystone.module.AptosSignature
import com.keystone.module.NativeUR
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneAptosSDK: KeystoneBaseSDK() {
    enum class DataType(val value: Int) {
        Single(1),
        Multi(2),
        Message(3),
    }

    fun parseSignature(ur: UR): AptosSignature {
        val jsonStr = native.parseAptosSignature(ur.type, ur.cborBytes.toHexString())
        val result = Gson().fromJson(jsonStr, AptosSignature::class.java)
        return handleError(jsonStr, result)
    }

    fun generateSignRequest(aptosSignRequest: AptosSignRequest): UREncoder {
        val jsonStr = native.generateAptosSignRequest(
            aptosSignRequest.requestId,
            aptosSignRequest.signData,
            Gson().toJson(aptosSignRequest.accounts),
            aptosSignRequest.origin,
            aptosSignRequest.dataType.value,
        )
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
