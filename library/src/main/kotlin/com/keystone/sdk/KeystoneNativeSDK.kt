package com.keystone.sdk

internal class KeystoneNativeSDK {
    // Multiple Accounts
    external fun parseCryptoMultiAccounts(type: String, cbor: String): String
    // HD Key
    external fun parseExtendedPublicKey(type: String, cbor: String): String
    external fun parseMultiPublicKeys(type: String, cbor: String): String
    external fun getUncompressedKey(compressedKey: String): String
    // Solana
    external fun parseSolSignature(type: String, cbor: String): String
    external fun generateSolSignRequest(requestId: String, signData: String, path: String, xfp: String, address: String, origin: String, signType: Int): String
    // Ethereum
    external fun parseETHSignature(type: String, cbor: String): String
    external fun generateETHSignRequest(requestId: String, signData: String, signType: Int, chainId: Int, path: String, xfp: String, address: String, origin: String): String
    // Bitcoin
    external fun parseCryptoPSBT(type: String, cbor: String): String
    external fun generateCryptoPSBT(psbt: String): String
    // Cosmos
    external fun parseCosmosSignature(type: String, cbor: String): String
    external fun generateCosmosSignRequest(requestId: String, signData: String, dataType: Int, accounts: String, origin: String): String
    // Tron
    external fun parseTronSignature(type: String, cbor: String): String
    external fun generateTronSignRequest(requestId: String, signData: String, path: String, xfp: String, tokenInfo: String, address: String, origin: String): String
    // Aptos
    external fun parseAptosSignature(type: String, cbor: String): String
    external fun generateAptosSignRequest(requestId: String, signData: String, accounts: String, origin: String, dataType: Int): String

    companion object {
        init {
            System.loadLibrary("ur_registry_ffi")
        }
    }
}
