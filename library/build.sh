#!/bin/bash

current_dir=$(pwd)

# install rust
if command -v rustc >/dev/null 2>&1; then
  echo 'rustc exists'
else
  # download NDK
  wget -q https://dl.google.com/android/repository/android-ndk-r25c-linux.zip
  unzip -q android-ndk-r25c-linux.zip
  export ANDROID_NDK_HOME=$current_dir/android-ndk-r25c
  curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh -s -- -y --default-toolchain=nightly-2023-12-01
  source "$HOME/.cargo/env"
  cargo install cargo-platform --version=0.1.8
  cargo install cargo-ndk --version=3.5.4
  rustup target add aarch64-linux-android armv7-linux-androideabi x86_64-linux-android i686-linux-android
fi

# build rust
cd ./keystone-sdk-rust || exit
make
cd "$current_dir" || exit

# copy
mkdir -p ./src/main/jniLibs
mkdir -p ./src/main/jniLibs/arm64-v8a
mkdir -p ./src/main/jniLibs/armeabi-v7a
mkdir -p ./src/main/jniLibs/x86_64
mkdir -p ./src/main/jniLibs/x86
cp ./keystone-sdk-rust/target/aarch64-linux-android/release/libur_registry_ffi.so ./src/main/jniLibs/arm64-v8a/
cp ./keystone-sdk-rust/target/armv7-linux-androideabi/release/libur_registry_ffi.so ./src/main/jniLibs/armeabi-v7a/
cp ./keystone-sdk-rust/target/x86_64-linux-android/release/libur_registry_ffi.so ./src/main/jniLibs/x86_64/
cp ./keystone-sdk-rust/target/i686-linux-android/release/libur_registry_ffi.so ./src/main/jniLibs/x86/
