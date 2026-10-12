#!/bin/bash

set -euo pipefail

current_dir="$(cd "$(dirname "$0")" && pwd)"
cd "$current_dir"
export PATH="$HOME/.cargo/bin:$PATH"
toolchain=$(cat ./keystone-sdk-rust/rust-toolchain)

# install rust
if command -v rustc >/dev/null 2>&1; then
  echo 'rustc exists'
else
  # download NDK
  wget -q https://dl.google.com/android/repository/android-ndk-r25c-linux.zip
  unzip -q android-ndk-r25c-linux.zip
  export ANDROID_NDK_HOME=$current_dir/android-ndk-r25c
  curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh -s -- -y --default-toolchain="$toolchain"
  source "$HOME/.cargo/env"
fi

# Use the toolchain pinned by the Rust submodule, including on existing machines.
rustup toolchain install "$toolchain" --profile minimal
rustup target add --toolchain "$toolchain" aarch64-linux-android armv7-linux-androideabi x86_64-linux-android i686-linux-android
if ! command -v cargo-ndk >/dev/null 2>&1; then
  cargo +"$toolchain" install cargo-ndk --version=3.5.4 --locked
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
