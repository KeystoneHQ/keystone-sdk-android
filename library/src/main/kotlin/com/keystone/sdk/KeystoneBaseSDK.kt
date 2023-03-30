package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.KeystoneError
import com.keystone.module.UR
import com.sparrowwallet.hummingbird.ResultType
import com.sparrowwallet.hummingbird.URDecoder
import com.sparrowwallet.hummingbird.UREncoder

open class KeystoneBaseSDK {
    internal val native: KeystoneNativeSDK = KeystoneNativeSDK()
    private var urDecoder: URDecoder = URDecoder()

    protected fun String.decodeHex(): ByteArray {
        check(length % 2 == 0) { "HexString must have an even length" }
        return chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    }

    protected fun ByteArray.toHexString(): String {
        return joinToString("") { "%02X".format(it) }
    }

    protected fun <T>handleError(jsonStr: String, data: T): T{
        val result = Gson().fromJson(jsonStr, KeystoneError::class.java)
        if (result.error != null) {
            throw Exception(result.error)
        }
        return data
    }

    fun encodeQR(ur: UR): UREncoder {
        val encodeUR = com.sparrowwallet.hummingbird.UR(ur.type, ur.cbor.decodeHex())
        return UREncoder(encodeUR, KeystoneSDK.maxFragmentLen, 10, 0)
    }

    fun decodeQR(qr: String): UR? {
        val isReceived = urDecoder.receivePart(qr)
        if (urDecoder.result == null) {
            if (isReceived) {
                return null
            } else {
                resetQRDecoder()
                throw Exception("Unexpected QR code")
            }
        }
        when (urDecoder.result.type) {
            ResultType.SUCCESS -> {
                val ur = urDecoder.result.ur
                return UR(ur.type, ur.cborBytes.toHexString())
            }
            else -> {
                resetQRDecoder()
                throw Exception("Invalid QR code")
            }
        }
    }

    fun resetQRDecoder() {
        urDecoder = URDecoder()
    }
}
