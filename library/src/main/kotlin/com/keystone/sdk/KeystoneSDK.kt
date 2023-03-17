package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.KeystoneError
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

open class KeystoneSDK {
    private var maxFragmentLen: Int = 100

    public fun setMaxFragmentLen(maxFragmentLen: Int) {
        this.maxFragmentLen = maxFragmentLen
    }

    protected fun encodeQR(type: String, cbor: String): UREncoder {
        val ur = UR.fromBytes(type, cbor.toByteArray());
        return UREncoder(ur, this.maxFragmentLen, 10, 0)
    }

    protected fun <T>handleError(jsonStr: String, data: T): T{
        val result = Gson().fromJson<KeystoneError>(jsonStr, KeystoneError::class.java)
        if (result.error != null) {
            throw Exception(result.error)
        }
        return data
    }

    protected external fun parseSolSignature(cbor: String): String
    protected external fun generateSolSignRequest(requestId: String, signData: String, path: String, xfp: String, address: String, origin: String, signType: Int): String

    companion object {
        init {
            System.loadLibrary("ur_registry_ffi")
        }
    }
}