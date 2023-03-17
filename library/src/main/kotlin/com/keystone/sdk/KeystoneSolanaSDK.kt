package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.SolanaSignature
import com.keystone.module.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneSolanaSDK : KeystoneSDK() {
    enum class SignType(val value: Int) {
        Transaction(1),
        Message(2),
    }

    public fun parseSignature(cborHex: String): SolanaSignature {
        val jsonStr = super.parseSolSignature(cborHex)
        val result = Gson().fromJson<SolanaSignature>(jsonStr, SolanaSignature::class.java)
        return super.handleError(jsonStr, result)
    }

    public fun generateSignRequest(
        requestId: String,
        signData: String,
        path: String,
        xfp: String,
        address: String,
        origin: String,
        signType: SignType
    ): UREncoder {
        val jsonStr = super.generateSolSignRequest(requestId, signData, path, xfp, address, origin, signType.value)
        val result = super.handleError(jsonStr, Gson().fromJson<UR>(jsonStr, UR::class.java))
        return super.encodeQR(result.type, result.cbor)
    }
}
