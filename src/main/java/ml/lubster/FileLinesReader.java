package ml.lubster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileLinesReader {
    private final String inFile;

    public FileLinesReader(String inFile) {
        this.inFile = inFile;
    }

    public Stream<String> readAllLines() {
        Stream<String> stream;
        try {
            stream = Files.lines(Paths.get(inFile), Charset.forName("Windows-1251"));
        } catch (IOException e) {
            stream = Stream.empty();
        }
        return stream;
    }

    public Stream<String> readByBufferedReader() {
        Stream.Builder<String> builder = Stream.builder();
        try (BufferedReader in = new BufferedReader(new FileReader(inFile))) {
            String line;
            while ((line = in.readLine()) != null) {
                builder.add(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return builder.build();
    }
}
