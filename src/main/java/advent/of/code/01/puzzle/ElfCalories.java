package advent.of.code.challenge01;

import advent.of.code.file.reader.FileReader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ElfCalories {

    public static void main(String[] args) {

        final String fileContent = FileReader.getDataFromFile("elf-calories.txt");

        // Pt. 1 Get the elf with more calories.

        // Get the separated calories.
        final String[] separatedCalories = fileContent.split("\n\n");

        // Get the separated calories per elf.
        final Map<Integer, String> separatedCaloriesPerElf = getSeparatedCaloriesPerElf(separatedCalories);

        final Map<Integer, Integer> elvesWithTotalCalories = new HashMap<>();

        separatedCaloriesPerElf.forEach(
            (elf, calories) -> elvesWithTotalCalories.put(elf, getTotalCaloriesPerElf(calories))
        );

        final Integer elfWithMoreCalories = getElfWithMoreCalories(elvesWithTotalCalories);

        System.out.println(elvesWithTotalCalories);
        System.out.println("Elf with more calories is -> " + elfWithMoreCalories);


        // Pt. 2 Get top 3 elves with more calories. Sum the results to solve the puzzle.

        // LinkedHashMap preserve the ordering of elements in which they are inserted
        final LinkedHashMap<Integer, Integer> elvesWithTotalCaloriesSorted = new LinkedHashMap<>();

        sortElvesByMaxCalories(elvesWithTotalCalories, elvesWithTotalCaloriesSorted);

        System.out.println(elvesWithTotalCaloriesSorted);
    }

    // TODO - Try to return a sorted Map
    private static void sortElvesByMaxCalories(
        Map<Integer, Integer> elvesWithTotalCalories,
        LinkedHashMap<Integer, Integer> elvesWithTotalCaloriesSorted) {
        elvesWithTotalCalories.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEachOrdered(elf -> elvesWithTotalCaloriesSorted.put(elf.getKey(), elf.getValue()));
    }

    private static Map<Integer, String> getSeparatedCaloriesPerElf(String[] separatedCalories) {
        return IntStream.range(0, separatedCalories.length)
            .boxed()
        .collect(Collectors.toMap(i -> i, i -> separatedCalories[i]));
    }

    private static Integer getElfWithMoreCalories(Map<Integer, Integer> elvesWithTotalCalories) {
        return Collections.max(elvesWithTotalCalories.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private static Integer getTotalCaloriesPerElf(String v) {
        final List<String> elfCalories = Arrays.asList(v.split("\n"));

        final List<Integer> elfCaloriesNumeric =
            elfCalories
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        return elfCaloriesNumeric
            .stream()
            .reduce(0, Integer::sum);
    }

}
