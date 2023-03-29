package com.keystone.sdk

internal class KeystoneNativeSDK {
    // Multiple Accounts
    external fun parseCryptoMultiAccounts(cbor: String): String
    // Solana
    external fun parseSolSignature(cbor: String): String
    external fun generateSolSignRequest(requestId: String, signData: String, path: String, xfp: String, address: String, origin: String, signType: Int): String
    // Ethereum
    external fun parseETHSignature(cbor: String): String
    external fun generateETHSignRequest(requestId: String, signData: String, signType: Int, chainId: Int, path: String, xfp: String, address: String, origin: String): String
    // Bitcoin
    external fun parseCryptoPSBT(cbor: String): String
    external fun generateCryptoPSBT(psbt: String): String

    companion object {
        init {
            System.loadLibrary("ur_registry_ffi")
        }
    }
}
