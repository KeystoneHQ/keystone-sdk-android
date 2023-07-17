package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.EvmSignRequest
import com.keystone.module.NativeUR
import com.keystone.module.Signature
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneEvmSDK : KeystoneBaseSDK() {
    enum class DataType(val value: Int) {
        Arbitrary(1),
        CosmosAmino(2),
        CosmosDirect(3),
    }

    fun parseSignature(ur: UR): Signature {
        val jsonStr = native.parseEvmSignature(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, Signature::class.java)
        return handleError(jsonStr, result)
    }

    fun generateSignRequest(evmSignRequest: EvmSignRequest): UREncoder {
        val jsonStr = native.generateEvmSignRequest(
            evmSignRequest.requestId,
            evmSignRequest.signData,
            evmSignRequest.dataType.value,
            evmSignRequest.customChainIdentifier,
            toJson(evmSignRequest.account),
            evmSignRequest.origin
        )
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
