package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.CardanoSignRequest
import com.keystone.module.CardanoSignature
import com.keystone.module.NativeUR
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneCardanoSDK: KeystoneBaseSDK() {
    fun parseSignature(ur: UR): CardanoSignature {
        val jsonStr = native.parseCardanoSignature(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, CardanoSignature::class.java)
        return handleError(jsonStr, result)
    }

    fun generateSignRequest(cardanoSignRequest: CardanoSignRequest): UREncoder {
        val jsonStr = native.generateCardanoSignRequest(
            cardanoSignRequest.requestId,
            cardanoSignRequest.signData,
            toJson(cardanoSignRequest.utxos),
            toJson(cardanoSignRequest.certKeys),
            cardanoSignRequest.origin
        )
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
