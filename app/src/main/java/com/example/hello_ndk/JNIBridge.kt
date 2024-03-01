package com.example.hello_ndk

class JNIBridge {
    companion object {
        init {
            System.loadLibrary("hello_ndk")
        }
    }

    external fun helloWorldFromJNI(name: String): String
}
