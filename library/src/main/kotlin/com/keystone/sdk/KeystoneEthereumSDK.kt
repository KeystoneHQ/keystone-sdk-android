package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.Signature
import com.keystone.module.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneEthereumSDK: KeystoneSDK() {
    enum class DataType(val value: Int) {
        Transaction(1),
        TypedData(2),
        PersonalMessage(3),
        TypedTransaction(4),
    }

    public fun parseSignature(cborHex: String): Signature {
        val jsonStr = parseETHSignature(cborHex)
        val result = Gson().fromJson<Signature>(jsonStr, Signature::class.java)
        return handleError(jsonStr, result)
    }

    public fun generateSignRequest(
        requestId: String,
        signData: String,
        dataType: DataType,
        chainId: Int,
        path: String,
        xfp: String,
        address: String = "",
        origin: String = "",
    ): UREncoder {
        val jsonStr = generateETHSignRequest(requestId, signData, dataType.value, chainId, path, xfp, address, origin)
        val result = handleError(jsonStr, Gson().fromJson<UR>(jsonStr, UR::class.java))
        return encodeQR(result)
    }
}
