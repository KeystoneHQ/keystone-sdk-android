package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.Signature
import com.keystone.module.TokenInfo
import com.keystone.module.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneTronSDK: KeystoneBaseSDK() {
    fun parseSignature(cborHex: String): Signature {
        val jsonStr = native.parseTronSignature(cborHex)
        val result = Gson().fromJson(jsonStr, Signature::class.java)
        return handleError(jsonStr, result)
    }

    fun generateSignRequest(
        requestId: String,
        signData: String,
        path: String,
        xfp: String,
        tokenInfo: TokenInfo? = null,
        address: String = "",
        origin: String = "",
    ): UREncoder {
        val jsonStr = native.generateTronSignRequest(
            requestId,
            signData,
            path,
            xfp,
            if (tokenInfo == null) "" else Gson().toJson(tokenInfo),
            address,
            origin
        )
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, UR::class.java))
        return encodeQR(result)
    }
}
