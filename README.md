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

## Multiple build variants

If your library uses multiple flavours then see this example:
https://github.com/jitpack-io/android-jitpack-library-example

## Adding the maven plugin

To enable installing into local maven repository and JitPack you need to add the [android-maven](https://github.com/dcendents/android-maven-gradle-plugin) plugin:

1. Add `classpath 'com.github.dcendents:android-maven-gradle-plugin:2.1'` to root build.gradle under `buildscript { dependencies {`
2. Add `com.github.dcendents.android-maven` to the library/build.gradle

After these changes you should be able to run:

    ./gradlew install
    
from the root of your project. If install works and you have added a GitHub release it should work on jitpack.io

## Adding a sample app 

If you add a sample app to the same repo then your app needs to have a dependency on the library. To do this in your app/build.gradle add:

```gradle
    dependencies {
        compile project(':library')
    }
```
