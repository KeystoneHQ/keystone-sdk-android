package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.Signature
import com.keystone.module.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneSolanaSDK : KeystoneSDK() {
    enum class SignType(val value: Int) {
        Transaction(1),
        Message(2),
    }

    fun parseSignature(cborHex: String): Signature {
        val jsonStr = parseSolSignature(cborHex)
        val result = Gson().fromJson<Signature>(jsonStr, Signature::class.java)
        return handleError(jsonStr, result)
    }

    fun generateSignRequest(
        requestId: String,
        signData: String,
        path: String,
        xfp: String,
        address: String = "",
        origin: String = "",
        signType: SignType,
    ): UREncoder {
        val jsonStr = generateSolSignRequest(requestId, signData, path, xfp, address, origin, signType.value)
        val result = handleError(jsonStr, Gson().fromJson<UR>(jsonStr, UR::class.java))
        return encodeQR(result)
    }
}
