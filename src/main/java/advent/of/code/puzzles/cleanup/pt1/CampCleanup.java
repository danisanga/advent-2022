package advent.of.code.puzzles.cleanup.pt1;

import advent.of.code.file.reader.FileReader;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CampCleanup {

    public static void main(String[] args) {

        final String fileContent = FileReader.getDataFromFile("puzzle-inputs/camp-cleanup.txt");

        final String[] pairs = fileContent.split("\n");

        final AtomicLong coincidences = new AtomicLong(0L);

        Arrays.stream(pairs).forEach(
            pair -> {
                final String[] range = pair.split(",");

                final List<Integer> numberInFirstRange = getNumberInFirstRange(range);
                final List<Integer> numberInSecondRange = getNumberInSecondRange(range);

                // Similar and different numbers.
                final Collection<Integer> similarNumbers = new HashSet<>(numberInFirstRange);
                final Collection<Integer> differentNumbers = new HashSet<>();
                differentNumbers.addAll(numberInFirstRange);
                differentNumbers.addAll(numberInSecondRange);

                similarNumbers.retainAll(numberInSecondRange);
                differentNumbers.removeAll(similarNumbers);

                System.out.printf("One:%s%nTwo:%s%nSimilar:%s%nDifferent:%s%n", numberInFirstRange, numberInSecondRange, similarNumbers, differentNumbers);

                final Collection<Integer> numberInFirstRangeHashSet =
                    new HashSet<>(numberInFirstRange);

                final Collection<Integer> numberInSecondRangeHashSet =
                    new HashSet<>(numberInSecondRange);

                // Comparing in both Collections.
                if (similarNumbers.equals(numberInFirstRangeHashSet)
                    || similarNumbers.equals(numberInSecondRangeHashSet)) {
                    coincidences.addAndGet(1);
                }

            }
        );

        System.out.println("Coincidences -> " + coincidences);
    }

    private static List<Integer> getNumberInFirstRange(final String[] range) {
        final String firstRange = range[0];

        final String[] firstRangeSplit = firstRange.split("-");

        final List<Integer> numberInFirstRange =
            getNumbersUsingIntStreamRangeClosed(
                Integer.valueOf(firstRangeSplit[0]),
                Integer.valueOf(firstRangeSplit[1]));

        return numberInFirstRange;
    }

    private static List<Integer> getNumberInSecondRange(final String[] range) {
        final String secondRange = range[1];

        final String[] secondRangeSplit = secondRange.split("-");

        final List<Integer> numberInSecondRange =
            getNumbersUsingIntStreamRangeClosed(
                Integer.valueOf(secondRangeSplit[0]),
                Integer.valueOf(secondRangeSplit[1]));

        return numberInSecondRange;
    }

    private static List<Integer> getNumbersUsingIntStreamRangeClosed(int start, int end) {
        return IntStream.rangeClosed(start, end)
            .boxed()
            .collect(Collectors.toList());
    }

}
