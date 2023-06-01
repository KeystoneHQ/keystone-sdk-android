package com.keystone.sdk

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.keystone.module.DecodeResult
import com.keystone.module.KeystoneError
import com.keystone.module.NativeUR
import com.sparrowwallet.hummingbird.ResultType
import com.sparrowwallet.hummingbird.UR
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
        try {
            val result = Gson().fromJson(jsonStr, KeystoneError::class.java)
            if (result.error != null) {
                throw Exception(result.error)
            }
        } catch (_: JsonSyntaxException) {}
        return data
    }

    fun encodeQR(ur: NativeUR): UREncoder {
        val encodeUR = UR(ur.type, ur.cbor.decodeHex())
        return UREncoder(encodeUR, KeystoneSDK.maxFragmentLen, 10, 0)
    }

    fun decodeQR(qr: String): DecodeResult {
        val isReceived = urDecoder.receivePart(qr)
        if (urDecoder.result == null) {
            if (isReceived) {
                val progress = (urDecoder.estimatedPercentComplete * 100).toInt()
                return DecodeResult(progress)
            } else {
                resetQRDecoder()
                throw Exception("Unexpected QR code")
            }
        }
        when (urDecoder.result.type) {
            ResultType.SUCCESS -> {
                return DecodeResult(100, urDecoder.result.ur)
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

    fun toJson(any: Any, fieldNamingPolicy: FieldNamingPolicy = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES): String {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(fieldNamingPolicy)
        return gsonBuilder.create().toJson(any)
    }

    fun <T> fromJson(jsonStr: String, classOfT: Class<T>, fieldNamingPolicy: FieldNamingPolicy = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES): T {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(fieldNamingPolicy)
        return gsonBuilder.create().fromJson(jsonStr, classOfT)
    }

    fun getTimestamp(): Long {
        return System.currentTimeMillis()
    }
}
