# tiktoken-android

Run [openai/tiktoken](https://github.com/openai/tiktoken) on Android 😃

This project is based on [eisber/tiktoken](https://github.com/eisber/tiktoken), thanks his work!

## Build

Assume that you already installed [ndk](https://developer.android.com/studio/projects/install-ndk), [rustup](https://rustup.rs) and [cargo](https://doc.rust-lang.org/cargo/getting-started/installation.html) in your computer, prefer Linux,

```bash
# Specify the path where your Android NDK is located, must be r25 or newer.
export ANDROID_NDK_ROOT=$HOME/Android/Sdk/ndk/25.2.9519653 # or other path.
export PATH=$ANDROID_NDK_ROOT/toolchains/llvm/prebuilt/linux-x86_64/bin:$PATH

# Install rust cross-compilation toolchains.
rustup target add aarch64-linux-android
rustup target add armv7-linux-androideabi
rustup target add x86_64-linux-android
rustup target add i686-linux-android

# Then just run build script.
./build.sh
```

## Usage

The build results are located at `./target` directory,

```
./target/aarch64-linux-android/release/lib_tiktoken_jni.so
./target/armv7-linux-androideabi/release/lib_tiktoken_jni.so
./target/x86_64-linux-android/release/lib_tiktoken_jni.so
./target/i686-linux-android/release/lib_tiktoken_jni.so
```

Just copy those `.so` files and `./java/src/main/java/tiktoken/Encoding.java` to your project,

and make sure your App has `android.permission.INTERNET` permission, then everything shoule be fine 🍻

Here is a example for you,

```java
// lib_tiktoken_jni.so will download target model .tiktoken file when it first init,
// for example https://openaipublic.blob.core.windows.net/encodings/r50k_base.tiktoken
new Thread(() -> {
    Encoding encoding = new Encoding("text-davinci-001");
    long[] a = encoding.encode("test", new String[0], 0); // a = [9288].
    encoding.close();
}).start();
```

## License

```
MIT License

Copyright (c) 2023 Matthew Lee
Copyright (c) 2023 Microsoft, Markus Cozowicz
Copyright (c) 2022 OpenAI, Shantanu Jain

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
