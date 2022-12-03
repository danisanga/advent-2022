package advent.of.code.puzzles.rps.match;

import java.util.Map;

public class MatchPt1 {

    private String elfMovement;
    private String myMovement;
    private int result;

    public MatchPt1(String elfMovement, String myMovement) {
        this.myMovement = myMovement;
        this.elfMovement = elfMovement;
    }

    private Map<String, String> parseMovement() {
        return Map.of(
            "X", "A",
            "Y", "B",
            "Z", "C"
        );
    }

    private Map<String, Integer> getScoreByMovement() {
        return Map.of(
            "X", 1,
            "Y", 2,
            "Z", 3
        );
    }

    private Map<String, Integer> getScoreByResult() {
        return Map.of(
            "W", 6,
            "L", 0,
            "D", 3
        );
    }

    public int getScoreResult() {
        String result = "";
        switch (this.elfMovement) {
            case "A":
                result = getResult("Z");
                break;

            case "B":
                result = getResult("X");
                break;

            case "C":
                result = getResult("Y");
                break;

            default:
                break;
        }
        return getScoreResult(result);
    }

    private String getResult(String wrongMovement) {
        if (wrongMovement.equals(this.myMovement)) {
            return "L";
        } else if (this.elfMovement.equals(parseMovement().get(this.myMovement))) {
            return "D";
        } else {
            return "W";
        }
    }

    private int getScoreResult(String result) {
        final Integer scoreByMovement = getScoreByMovement().get(this.myMovement);
        final Integer scoreByResult = getScoreByResult().get(result);
        return scoreByMovement + scoreByResult;
    }
}
