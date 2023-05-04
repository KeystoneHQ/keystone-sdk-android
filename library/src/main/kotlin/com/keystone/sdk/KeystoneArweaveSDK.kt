package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.ArweaveAccount
import com.keystone.module.ArweaveSignRequest
import com.keystone.module.NativeUR
import com.keystone.module.Signature
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

class KeystoneArweaveSDK: KeystoneBaseSDK() {
    enum class SignType(val value: Int) {
        Transaction(1),
        DataItem(2),
        Message(3),
    }

    enum class SaltLen(val value: Int) {
        Zero(0),
        Digest(32),
    }

    fun parseAccount(ur: UR): ArweaveAccount {
        val jsonStr = native.parseArweaveAccount(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, ArweaveAccount::class.java)
        return handleError(jsonStr, result)
    }

    fun parseSignature(ur: UR): Signature {
        val jsonStr = native.parseArweaveSignature(ur.type, ur.cborBytes.toHexString())
        val result = fromJson(jsonStr, Signature::class.java)
        return handleError(jsonStr, result)
    }

    fun generateSignRequest(arweaveSignRequest: ArweaveSignRequest): UREncoder {
        val jsonStr = native.generateArweaveSignRequest(
            arweaveSignRequest.requestId,
            arweaveSignRequest.signData,
            arweaveSignRequest.signType.value,
            arweaveSignRequest.saltLen.value,
            arweaveSignRequest.masterFingerprint,
            arweaveSignRequest.account,
            arweaveSignRequest.origin
        )
        val result = handleError(jsonStr, Gson().fromJson(jsonStr, NativeUR::class.java))
        return encodeQR(result)
    }
}
