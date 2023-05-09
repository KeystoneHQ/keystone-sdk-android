package com.keystone.sdk

internal class KeystoneNativeSDK {
    // Multiple Accounts
    external fun parseCryptoMultiAccounts(type: String, cbor: String): String
    // HD Key
    external fun parseExtendedPublicKey(type: String, cbor: String): String
    external fun parseMultiPublicKeys(type: String, cbor: String): String
    external fun getUncompressedKey(compressedKey: String): String
    external fun derivePublicKey(xpub: String, path: String): String
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
    external fun generateTronSignRequest(requestId: String, signData: String, path: String, xfp: String, tokenInfo: String, origin: String, timestamp: Long): String
    // Aptos
    external fun parseAptosSignature(type: String, cbor: String): String
    external fun generateAptosSignRequest(requestId: String, signData: String, accounts: String, origin: String, signType: Int): String
    // Keystone
    external fun parseKeystoneSignResult(type: String, cbor: String): String
    external fun generateKeystoneSignRequest(requestId: String, coinType: Int, signData: String, xfp: String, origin: String, timestamp: Long): String
    // Near
    external fun parseNearSignature(type: String, cbor: String): String
    external fun generateNearSignRequest(requestId: String, signData: String, path: String, xfp: String, account: String, origin: String): String
    // Arweave
    external fun parseArweaveAccount(type: String, cbor: String): String
    external fun parseArweaveSignature(type: String, cbor: String): String
    external fun generateArweaveSignRequest(requestId: String, signData: String, signType: Int, saltLen: Int, masterFingerprint: String, account: String, origin: String): String

    companion object {
        init {
            System.loadLibrary("ur_registry_ffi")
        }
    }
}
