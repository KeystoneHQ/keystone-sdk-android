# keystone-sdk-android

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
