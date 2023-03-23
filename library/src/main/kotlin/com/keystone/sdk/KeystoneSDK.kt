package com.keystone.sdk

import com.google.gson.Gson
import com.keystone.module.KeystoneError
import com.keystone.module.MultiAccounts
import com.keystone.module.UR
import com.sparrowwallet.hummingbird.URDecoder
import com.sparrowwallet.hummingbird.UREncoder

open class KeystoneSDK(chains: Array<ChainType>? = null) {
    private var maxFragmentLen: Int = 100
    private var urDecoder: URDecoder = URDecoder()
    lateinit var eth: KeystoneEthereumSDK
    lateinit var sol: KeystoneSolanaSDK

    enum class ChainType {
        ETH,
        SOL,
    }

    init {
        chains?.forEach{ initChain(it) }
    }

    private fun initChain(chainType: ChainType) {
        when (chainType) {
            ChainType.ETH -> eth = KeystoneEthereumSDK()
            ChainType.SOL -> sol = KeystoneSolanaSDK()
        }
    }

    private fun String.decodeHex(): ByteArray {
        check(length % 2 == 0) { "HexString must have an even length" }
        return chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    }

    private fun ByteArray.toHexString(): String {
        return joinToString("") { "%02X".format(it) }
    }

    fun setMaxFragmentLen(maxFragmentLen: Int) {
        this.maxFragmentLen = maxFragmentLen
    }

    protected fun encodeQR(ur: UR): UREncoder {
        val encodeUR = com.sparrowwallet.hummingbird.UR(ur.type, ur.cbor.decodeHex())
        return UREncoder(encodeUR, maxFragmentLen, 10, 0)
    }

    fun decodeQR(qr: String): com.keystone.module.UR? {
        if (urDecoder.receivePart(qr) && urDecoder.result != null) {
            val ur = urDecoder.result.ur
            return com.keystone.module.UR(ur.type, ur.cborBytes.toHexString())
        }
        return null
    }

    fun resetQRDecoder() {
        urDecoder = URDecoder()
    }

    fun parseMultiAccounts(cborHex: String): MultiAccounts {
        val jsonStr = parseCryptoMultiAccounts(cborHex)
        val result = Gson().fromJson(jsonStr, MultiAccounts::class.java)
        return handleError(jsonStr, result)
    }

    protected fun <T>handleError(jsonStr: String, data: T): T{
        val result = Gson().fromJson(jsonStr, KeystoneError::class.java)
        if (result.error != null) {
            throw Exception(result.error)
        }
        return data
    }

    // Multiple Accounts
    private external fun parseCryptoMultiAccounts(cbor: String): String
    // Solana
    protected external fun parseSolSignature(cbor: String): String
    protected external fun generateSolSignRequest(requestId: String, signData: String, path: String, xfp: String, address: String, origin: String, signType: Int): String
    // Ethereum
    protected external fun parseETHSignature(cbor: String): String
    protected external fun generateETHSignRequest(requestId: String, signData: String, signType: Int, chainId: Int, path: String, xfp: String, address: String, origin: String): String

    companion object {
        init {
            System.loadLibrary("ur_registry_ffi")
        }
    }
}
