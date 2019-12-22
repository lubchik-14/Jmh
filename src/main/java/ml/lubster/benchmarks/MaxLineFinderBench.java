package ml.lubster.benchmarks;

import ml.lubster.FileLinesReader;
import ml.lubster.MaxLineFinder;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Fork(value = 1, jvmArgs = {"-Xms512m", "-Xmx512m"})
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Timeout(time = 1, timeUnit = TimeUnit.MINUTES)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MaxLineFinderBench {
        @Param({"d:/c.txt", "d:/1c.txt"})
//    @Param("d:/c.txt")
    private String inFile;
    private FileLinesReader reader;
    private MaxLineFinder finder = new MaxLineFinder();
    private Stream<String> stream;

    @Setup(Level.Trial)
    public void init() {
        reader = new FileLinesReader(inFile);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void maxThenConvert(Blackhole blackhole) {
        stream = reader.readAllLines();
        blackhole.consume(finder.maxThenConvert(stream).isPresent());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void parallelMaxThenConvert(Blackhole blackhole) {
        stream = reader.readAllLines();
        blackhole.consume(finder.parallelMaxThenConvert(stream).isPresent());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void convertToIntStreamThenMax(Blackhole blackhole) {
        stream = reader.readAllLines();
        blackhole.consume(finder.convertToIntStreamThenMax(stream).isPresent());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void parallelConvertToIntStreamThenMax(Blackhole blackhole) {
        stream = reader.readAllLines();
        blackhole.consume(finder.parallelConvertToIntStreamThenMax(stream).isPresent());
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void convertThenMax(Blackhole blackhole) {
        stream = reader.readAllLines();
        blackhole.consume(finder.convertThenMax(stream).isPresent());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void parallelConvertThenMax(Blackhole blackhole) {
        stream = reader.readAllLines();
        blackhole.consume(finder.parallelConvertThenMax(stream).isPresent());
    }
}