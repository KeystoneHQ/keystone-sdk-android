# keystone-sdk-android

## Signing API changes

The legacy `keystone-sign-request` / `keystone-sign-result` APIs have been removed. Use TRON Native for TRON and PSBT Extend for LTC, BCH, DASH and DOGE. These protocols require different transaction inputs and response handling; they are not drop-in replacements. Integrations that still require the removed APIs must pin an earlier SDK version.

[![Release](https://jitpack.io/v/KeystoneHQ/keystone-sdk-android.svg)](https://jitpack.io/#KeystoneHQ/keystone-sdk-android)

KeystoneSDK Android library project that works with jitpack.io.

See this [Tutorial](https://medium.com/@ome450901/publish-an-android-library-by-jitpack-a0342684cbd0) on how to publish an Android Library with JitPack.

For more details check out the [documentation](https://github.com/jitpack/jitpack.io/blob/master/ANDROID.md)

https://jitpack.io/#KeystoneHQ/keystone-sdk-android

Add it to your build.gradle with:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

or add it to your settings.gradle with:
```gradle
dependencyResolutionManagement {
    repositories {
        maven { url 'https://www.jitpack.io' }
    }
}
```

then add dependency:
```gradle
dependencies {
    compile 'com.github.KeystoneHQ/keystone-sdk-android:{latest version}'
}
```

## PSBT Extend

```kotlin
val sdk = KeystoneSDK()
val encoder = sdk.btc.generatePSBTExtend(psbtBytes, coinId = 2)
val result = sdk.btc.parsePSBTExtend(ur)
// result.psbt: ByteArray; result.coinId: Int? (null for an omitted Bitcoin coin field)
```

Supported coin IDs: `0` Bitcoin (omitted in CBOR), `2` Litecoin,
`3` Dogecoin, `5` Dash, `145` Bitcoin Cash.

Build with an installed Android NDK and JDK 17 (the Gradle 7.6.1 wrapper does not support JDK 21):

```bash
export ANDROID_NDK_HOME="/path/to/android/ndk"
./gradlew :library:assembleRelease
```

The preBuild task builds the Rust submodule and copies all four ABI libraries.
The AAR is generated in `library/build/outputs/aar/`.

## TRON native signing

Use `sdk.tronNative` with the Rust submodule pinned to `sdk-0.3.0` (`7f4d2258c9e79770e9c105ee49d5849b6be1c206`). The native libraries must be built from this revision or a compatible later version; older native libraries do not export these methods.

### Generate a request

```kotlin
import com.keystone.sdk.KeystoneSDK
import com.keystone.module.TronNativeSignRequest
import com.keystone.module.Signature
import java.util.UUID

val sdk = KeystoneSDK()
val requestId = UUID.randomUUID().toString()
// transactionRawDataHex is the unsigned transaction's raw_data_hex.
// deviceXfp is the master fingerprint obtained when connecting the device.
val request = TronNativeSignRequest(
    requestId = requestId,
    signData = transactionRawDataHex,
    path = "m/44'/195'/0'/0/0",
    xfp = deviceXfp,
    dataType = 1,
    origin = "My Wallet",
)
val encoder = sdk.tronNative.generateSignRequest(request)
val qrFragment = encoder.nextPart()
// Display successive encoder.nextPart() values as QR frames.
```

### Parse the response

Keep the same SDK instance while scanning a multipart response:

```kotlin
fun receiveTronFragment(qr: String): Signature? {
    val ur = sdk.decodeQR(qr).ur ?: return null
    try {
        val result = sdk.tronNative.parseSignature(ur)
        require(result.requestId == requestId) { "Unexpected signing response" }
        return result // result.signature is hex.
    } finally {
        sdk.resetQRDecoder()
    }
}
```

Invalid requests or responses throw an exception. If you already have a decoded `UR`, call `sdk.tronNative.parseSignature(ur)` directly.

### Request parameters

| Parameter | Meaning |
| --- | --- |
| `requestId` | A new UUID for each signing request. Match the response UUID before using its signature. |
| `signData` | Hex with or without a `0x` / `0X` prefix; the SDK strips the prefix before validation and encoding. For `dataType = 1`, use the serialized TRON transaction **raw_data** (`raw_data_hex`), not JSON, a full signed transaction, or a transaction hash. For `dataType = 2`, use the original personal-message bytes encoded as hex. |
| `path` | Signing derivation path, for example `m/44'/195'/0'/0/0`. |
| `xfp` | The connected device's master fingerprint: exactly 8 hex characters. |
| `dataType` | `1` = transaction (default in mobile SDKs); `2` = personal message. |
| `address` | Optional TRON Base58Check address; an empty string omits it. |
| `origin` | Optional wallet/application name; an empty string omits it. |

`signData` must contain 1–32768 bytes. `path` is limited to 256 bytes and `origin` to 128 UTF-8 bytes.

The request UR type is `tron-sign-request`; the response UR type is `tron-signature`.
The response contains `requestId` and a 65-byte signature encoded as hex. It does not contain a signed transaction or transaction ID. For transactions, retain the original transaction, verify the response request ID and signature against the intended signer, then attach the signature to that transaction before broadcasting. Parsing does not perform that cryptographic verification or broadcast.

Use a firmware version that supports the native TRON UR types.
