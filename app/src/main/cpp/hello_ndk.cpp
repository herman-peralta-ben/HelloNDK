#include <jni.h>
#include <string>
#include <android/log.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_hello_1ndk_JNIBridge_helloWorldFromJNI(
        JNIEnv *env,
        jobject thiz,
        jstring jname) {

    jboolean isCopy;
    const char * convertedName = (env)->GetStringUTFChars(jname, &isCopy);
    std::string name = convertedName;

    __android_log_print(ANDROID_LOG_INFO, "C++", "inside helloWorldFromJNI");

    std::string hello = "Hello World from C++, " + name;

    return env->NewStringUTF(hello.c_str());
}
