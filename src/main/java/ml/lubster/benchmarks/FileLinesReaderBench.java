package ml.lubster.benchmarks;

import ml.lubster.FileLinesReader;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Fork(value = 1, jvmArgs = {"-Xms512m", "-Xmx512m"})
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Timeout(time = 1, timeUnit = TimeUnit.MINUTES)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class FileLinesReaderBench {
    @Param({"d:/c.txt", "d:/1c.txt"})
    private String inFile;

    private FileLinesReader reader;

    @Setup(Level.Trial)
    public void init() {
        reader = new FileLinesReader(inFile);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void readAllLines(Blackhole blackhole) {
        Stream<String> stream = reader.readAllLines();
        blackhole.consume(stream);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void readByBufferedReader(Blackhole blackhole) {
        Stream<String> stream = reader.readByBufferedReader();
        blackhole.consume(stream);
    }
}