package advent.of.code.puzzles.rps.match;

import java.util.Map;

public class MatchPt2 {

    private String elfMovement;
    private String expectedResult;
    private int result;

    public MatchPt2(String elfMovement, String expectedResult) {
        this.expectedResult = expectedResult;
        this.elfMovement = elfMovement;
    }

    private Map<String, String> parseExpectedResult() {
        return Map.of(
            "X", "L",
            "Y", "D",
            "Z", "W"
        );
    }

    private Map<String, String> getGoodMovement() {
        return Map.of(
            "A", "B",
            "B", "C",
            "C", "A"
        );
    }

    private Map<String, String> getWrongMovement() {
        return Map.of(
            "A", "C",
            "B", "A",
            "C", "B"
        );
    }

    private Map<String, Integer> getScoreByMovement() {
        return Map.of(
            "A", 1,
            "B", 2,
            "C", 3
        );
    }

    private Map<String, Integer> getScoreByResult() {
        return Map.of(
            "W", 6,
            "L", 0,
            "D", 3
        );
    }

    public int getResult() {
        final String myMovement = chooseMovement(this.expectedResult);

        return getScoreResult(myMovement);
    }

    private String chooseMovement(String expectedResult) {
        String movement = "";
        switch (expectedResult) {
            case "X":
                movement = getWrongMovement().get(this.elfMovement);
                break;

            case "Y":
                movement = this.elfMovement;
                break;

            case "Z":
                movement = getGoodMovement().get(this.elfMovement);
                break;

            default:
                break;
        }
        return movement;
    }

    private int getScoreResult(String myMovement) {
        final Integer scoreByMovement = getScoreByMovement().get(myMovement);
        final Integer scoreByResult = getScoreByResult().get(parseExpectedResult().get(this.expectedResult));
        return scoreByMovement + scoreByResult;
    }
}
