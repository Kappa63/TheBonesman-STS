package bonesman.util;

public final class DominoUtils {
    public static String getMatchPowerName(int matchVal) {
        switch (matchVal) {
            case 1: return "MatchOnePower";
            case 2: return "MatchTwoPower";
            case 3: return "MatchThreePower";
            case 4: return "MatchFourPower";
            case 5: return "MatchFivePower";
            case 6: return "MatchSixPower";
            default: return "";
        }
    }
}
