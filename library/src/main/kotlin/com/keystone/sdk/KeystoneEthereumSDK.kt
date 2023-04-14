package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.EthSignRequest
import com.keystone.module.NativeUR
import com.keystone.module.Signature
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneEthereumSDK: KeystoneBaseSDK() {
    enum class DataType(val value: Int) {
        Transaction(1),
        TypedData(2),
        PersonalMessage(3),
        TypedTransaction(4),
    }

    fun parseSignature(ur: UR): Signature {
        val jsonStr = native.parseETHSignature(ur.type, ur.cborBytes.toHexString())
        val result = Gson().fromJson(jsonStr, Signature::class.java)
        return handleError(jsonStr, result)
    }

    fun generateSignRequest(ethSignRequest: EthSignRequest): UREncoder {
        val jsonStr = native.generateETHSignRequest(
            ethSignRequest.requestId,
            ethSignRequest.signData,
            ethSignRequest.dataType.value,
            ethSignRequest.chainId,
            ethSignRequest.path,
            ethSignRequest.xfp,
            ethSignRequest.address,
            ethSignRequest.origin
        )
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
