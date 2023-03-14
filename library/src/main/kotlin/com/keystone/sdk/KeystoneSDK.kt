package com.keystone.sdk

import com.google.gson.Gson
import com.sparrowwallet.hummingbird.UR
import com.sparrowwallet.hummingbird.UREncoder
import com.keystone.ur.SolanaSignature

class KeystoneSDK {
    private external fun parseSolSignature(cbor: String): String

    public fun encodeQR(type: String, cbor: String, maxFragmentLen: Int = 100, minFragmentLen: Int = 10): UREncoder {
        val ur = UR.fromBytes(type, cbor.toByteArray());
        return UREncoder(ur, maxFragmentLen, minFragmentLen, 0)
    }

    public fun parseSolanaSignature(cbor: String): SolanaSignature {
        val gson = Gson()
        return gson.fromJson<SolanaSignature>(parseSolSignature(cbor), SolanaSignature::class.java)
    }

    companion object {
        init {
            System.loadLibrary("ur_registry_ffi")
        }
    }
}
