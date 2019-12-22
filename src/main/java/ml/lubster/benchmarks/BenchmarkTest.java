package ml.lubster.benchmarks;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkTest {
    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(FileLinesReaderBench.class.getSimpleName())
                .include(MaxLineFinderBench.class.getSimpleName())
                .build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}
