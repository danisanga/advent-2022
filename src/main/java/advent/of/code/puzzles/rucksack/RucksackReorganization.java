package advent.of.code.puzzles.rucksack;

import advent.of.code.file.reader.FileReader;
import advent.of.code.puzzles.rucksack.priority.Priority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RucksackReorganization {

    private static final String CHAR_REGEX = "(?!^)";

    public static void main(String[] args) {

        final String fileContent = FileReader.getDataFromFile("rucksack.txt");

        // Get the separated rucksacks.
        final String[] rucksacks = fileContent.split("\n");
        int priorityScorePt1 = 0;
        int priorityScorePt2 = 0;

        // Pt 1. Day 3 Advent of Code.
        for (final String rucksack : rucksacks) {
            final int rucksackSize = rucksack.length();
            final String firstCompartment = rucksack.substring(0, rucksackSize / 2);
            final String secondCompartment = rucksack.substring(rucksackSize / 2);

            final String[] firstCompartmentTypes = firstCompartment.split(CHAR_REGEX);
            final String[] secondCompartmentTypes = secondCompartment.split(CHAR_REGEX);

            final List<String> firstTypes = new ArrayList<>(List.of(firstCompartmentTypes));
            final List<String> secondTypes = new ArrayList<>(List.of(secondCompartmentTypes));

            final List<String> duplicates = firstTypes.stream()
                .filter(secondTypes::contains)
                .distinct()
                .collect(Collectors.toList());

            final Integer duplicatesPriorityScore = duplicates
                .stream()
                .map(duplicate -> Priority.getPriorityScore().get(duplicate))
                .reduce(0, Integer::sum);

            priorityScorePt1 += duplicatesPriorityScore;
        }

        // Pt 2. Day 3 Advent of Code.
        int cont = 0;
        while (rucksacks.length - 1 >= cont) {
            final String firstRucksack = rucksacks[cont];
            final String secondRucksack = rucksacks[cont + 1];
            final String thirdRucksack = rucksacks[cont + 2];

            final String[] firstCompartmentTypes = firstRucksack.split(CHAR_REGEX);
            final String[] secondCompartmentTypes = secondRucksack.split(CHAR_REGEX);
            final String[] thirdCompartmentTypes = thirdRucksack.split(CHAR_REGEX);

            final List<String> firstTypes = new ArrayList<>(List.of(firstCompartmentTypes));
            final List<String> secondTypes = new ArrayList<>(List.of(secondCompartmentTypes));
            final List<String> thirdTypes = new ArrayList<>(List.of(thirdCompartmentTypes));

            final List<String> duplicates = firstTypes.stream()
                .filter(element -> secondTypes.contains(element) && thirdTypes.contains(element))
                .distinct()
                .collect(Collectors.toList());

            final Integer duplicatesPriorityScore = duplicates
                .stream()
                .map(duplicate -> Priority.getPriorityScore().get(duplicate))
                .reduce(0, Integer::sum);

            priorityScorePt2 += duplicatesPriorityScore;

            cont += 3;
        }

        System.out.println(priorityScorePt1);
        System.out.println(priorityScorePt2);

    }

}
