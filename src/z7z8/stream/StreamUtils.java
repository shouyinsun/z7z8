package z7z8.stream;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author cash
 * @description stream流式
 * @date 2022/2/22 10:19 AM
 */
public class StreamUtils {
    public static <T> Stream<T> ofNullable(Collection<T> data) {
        return Optional.ofNullable(data).map(Collection::stream).orElse(Stream.empty());
    }

    public static <T> Stream<T> ofNullable(T data) {
        return Optional.ofNullable(data).map(Stream::of).orElse(Stream.empty());
    }
}
