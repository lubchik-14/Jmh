package ml.lubster;

public class MaxLineMain {
    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("Not found inFile");
            System.exit(1);
        }
        String inFile = args[0];
        FileLinesReader reader = new FileLinesReader(inFile);
        MaxLineFinder finder = new MaxLineFinder();

//        finder.parallelMaxThenConvert(reader.readAllLines()).ifPresent(i -> System.out.println());
//        finder.parallelConvertThenMax(reader.readAllLines()).ifPresent(System.out::println);
//        finder.parallelConvertToIntStreamThenMax(reader.readAllLines()).ifPresent(System.out::println);
        finder.maxThenConvert(reader.readAllLines()).ifPresent(System.out::println);
        finder.convertThenMax(reader.readAllLines()).ifPresent(System.out::println);
//        finder.convertToIntStreamThenMax(reader.readAllLines()).ifPresent(System.out::println);
    }
}
