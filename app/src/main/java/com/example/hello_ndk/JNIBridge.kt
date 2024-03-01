package com.example.hello_ndk

import android.util.Log

class JNIBridge {
    companion object {
        init {
            System.loadLibrary("hello_ndk")
        }
    }

    external fun helloWorldFromJNI(name: String): String

    external fun makeJNICallVm()

    fun helloWorldFromVm() {
        Log.i("ANDROID", "helloWorldFromVm")
    }
}
