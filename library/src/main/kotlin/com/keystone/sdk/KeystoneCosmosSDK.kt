package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.CosmosSignRequest
import com.keystone.module.CosmosSignature
import com.keystone.module.NativeUR
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneCosmosSDK : KeystoneBaseSDK() {
    enum class DataType(val value: Int) {
        Amino(1),
        Direct(2),
        Textual(3),
        Message(4),
    }

    fun parseSignature(ur: UR): CosmosSignature {
        val jsonStr = native.parseCosmosSignature(ur.type, ur.cborBytes.toHexString())
        val result = Gson().fromJson(jsonStr, CosmosSignature::class.java)
        return handleError(jsonStr, result)
    }

    fun generateSignRequest(cosmosSignRequest: CosmosSignRequest): UREncoder {
        val jsonStr = native.generateCosmosSignRequest(
            cosmosSignRequest.requestId,
            cosmosSignRequest.signData,
            cosmosSignRequest.dataType.value,
            Gson().toJson(cosmosSignRequest.accounts),
            cosmosSignRequest.origin
        )
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
