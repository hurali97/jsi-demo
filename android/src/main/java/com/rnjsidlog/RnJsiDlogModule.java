package com.rnjsidlog;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = RnJsiDlogModule.NAME)
public class RnJsiDlogModule extends ReactContextBaseJavaModule {
    public static final String NAME = "RnJsiDlog";

    public RnJsiDlogModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean install() {
        try {
        System.loadLibrary("nativeJsiDlog");

        ReactApplicationContext context = getReactApplicationContext();
        nativeInstall(
            context.getJavaScriptContextHolder().get()
        );
        return true;
        } catch (Exception exception) {
        return false;
        }
    }

  private native void nativeInstall(long jsi);
}
