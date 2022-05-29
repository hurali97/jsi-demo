package com.rnjsidlog;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.Promise;
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

  public void installLib(JavaScriptContextHolder reactContext) {

    if (reactContext.get() != 0) {
      this.nativeInstall(
        reactContext.get()
      );
    } else {
      Log.e("SimpleJsiModule", "JSI Runtime is not available in debug mode");
    }

  }
}
