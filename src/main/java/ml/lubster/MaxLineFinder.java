package ml.lubster;

import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class MaxLineFinder {

    public Optional<Integer> parallelMaxThenConvert(Stream<String> stream) {
        return stream.parallel()
                .max(Comparator.comparing(String::length))
                .map(String::length);
    }

    public Optional<Integer> maxThenConvert(Stream<String> stream) {
        return stream
                .max(Comparator.comparing(String::length))
                .map(String::length);
    }

    public Optional<Integer> parallelConvertThenMax(Stream<String> stream) {
        return stream.parallel()
                .map(String::length)
                .max(Integer::compareTo);
    }

    public Optional<Integer> convertThenMax(Stream<String> stream) {
        return stream
                .map(String::length)
                .max(Integer::compareTo);
    }

    public OptionalInt parallelConvertToIntStreamThenMax(Stream<String> stream) {
        return stream.parallel()
                .mapToInt(String::length)
                .max();
    }

    public OptionalInt convertToIntStreamThenMax(Stream<String> stream) {
        return stream
                .mapToInt(String::length)
                .max();
    }
}
