package dsa.hcmiu.a2048pets.entities.model;

/**
 * Created by Admin on 3/27/2018.
 */

public class Features {
    public static int totalScore;
    public static int maxUndo;
    public static int maxKey;

    public static int getTotalScore() {
        return totalScore;
    }

    public static void setTotalScore(int totalScore) {
        Features.totalScore = totalScore;
    }

    public static int getMaxUndo() {
        return maxUndo;
    }

    public static void setMaxUndo(int maxUndo) {
        Features.maxUndo = maxUndo;
    }

    public static int getMaxKey() {
        return maxKey;
    }

    public static void setMaxKey(int maxKey) {
        Features.maxKey = maxKey;
    }
}
