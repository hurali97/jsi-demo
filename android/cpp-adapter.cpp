#include <jni.h>
#include "rn-jsi-dlog.h"

extern "C"
JNIEXPORT void JNICALL
Java_com_rnjsidlog_RnJsiDlogModule_nativeInstall(JNIEnv *env, jobject thiz, jlong jsi) {
    // TODO: implement nativeInstall()
    auto runtime = reinterpret_cast<facebook::jsi::Runtime *>(jsi);

    if (runtime) {
        nativeJsiDlog::install(*runtime);
    }
}
