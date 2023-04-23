package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.NativeUR
import com.keystone.module.Signature
import com.keystone.module.SolSignRequest
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneSolanaSDK : KeystoneBaseSDK() {
    enum class SignType(val value: Int) {
        Transaction(1),
        Message(2),
    }

    fun parseSignature(ur: UR): Signature {
        val jsonStr = native.parseSolSignature(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, Signature::class.java)
        return handleError(jsonStr, result)
    }

    fun generateSignRequest(solSignRequest: SolSignRequest): UREncoder {
        val jsonStr = native.generateSolSignRequest(
            solSignRequest.requestId,
            solSignRequest.signData,
            solSignRequest.path,
            solSignRequest.xfp,
            solSignRequest.address,
            solSignRequest.origin,
            solSignRequest.signType.value,
        )
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
