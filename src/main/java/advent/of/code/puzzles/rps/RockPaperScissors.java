package advent.of.code.puzzles.rps;

import advent.of.code.file.reader.FileReader;
import advent.of.code.puzzles.rps.match.MatchPt1;
import advent.of.code.puzzles.rps.match.MatchPt2;

public class RockPaperScissors {

    public static void main(String[] args) {

        final String fileContent = FileReader.getDataFromFile("rock-paper-scissors.txt");

        // Get the separated matches.
        final String[] separatedMatches = fileContent.split("\n");

        // Results
        int matchResultPt1 = 0;
        int matchResultPt2 = 0;

        for (final String match : separatedMatches) {
            final String[] movements = match.split(" ");

            // Pt 1. Day 2 Advent of Code.
            final MatchPt1 matchPt1 = new MatchPt1(movements[0], movements[1]);
            matchResultPt1 += matchPt1.getScoreResult();

            // Pt 2. Day 2 Advent of Code.
            final MatchPt2 matchPt2 = new MatchPt2(movements[0], movements[1]);
            matchResultPt2 += matchPt2.getResult();
        }

        System.out.println("Final result Pt 1 -> " + matchResultPt1);
        System.out.println("Final result Pt 2 -> " + matchResultPt2);

    }

}
