package tiktoken;

import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

@Keep
public final class Encoding implements AutoCloseable {
    static {
        System.loadLibrary("_tiktoken_jni");
    }

    @SuppressWarnings("unused")
    private long handle;

    public Encoding(@NonNull String modelName) {
        init(modelName);
    }

    public void close() {
        destroy();
    }

    private native void init(@NonNull String modelName);

    private native void destroy();

    public native long[] encode(
            @NonNull String text,
            @NonNull String[] allowedSpecialTokens,
            @IntRange(from = 0) long maxTokenLength);
}
