#!/bin/bash

# https://developer.android.com/ndk/guides/other_build_systems?hl=en#autoconf
export AR=llvm-ar
export RANLIB=llvm-ranlib

export CC=aarch64-linux-android33-clang
cargo build --target aarch64-linux-android --release

export CC=armv7a-linux-androideabi33-clang
cargo build --target armv7-linux-androideabi --release

export CC=x86_64-linux-android33-clang
cargo build --target x86_64-linux-android --release

export CC=i686-linux-android33-clang
cargo build --target i686-linux-android --release

unset AR
unset CC
unset RANLIB
