package home.types;

public class Difficulty {
    private final int level;
    private final String difficulty;

    public Difficulty(String difficulty)
    {
        this.difficulty = difficulty;
        level = getLevel(difficulty);
    }

    private static int getLevel(String difficulty) {
            switch (difficulty) {
                case "Easy":
                    return 1;
                case "Medium":
                    return 2;
                case "Hard":
                    return 3;
                case "Very Hard":
                case "Elite":
                    return 4;
                default:
                    return 0;
            }
    }

    private int getLevel() {
        return level;
    }

    public String getName() {
        return difficulty;
    }

    @Override
    public String toString() {
        return getLevel() + "";
    }
}
