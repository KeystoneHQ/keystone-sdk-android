package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.KeystoneError
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder

open class KeystoneSDK {
    public fun encodeQR(type: String, cbor: String, maxFragmentLen: Int = 100, minFragmentLen: Int = 10): UREncoder {
        val ur = UR.fromBytes(type, cbor.toByteArray());
        return UREncoder(ur, maxFragmentLen, minFragmentLen, 0)
    }

    public fun <T>handleError(jsonStr: String, data: T): T{
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
