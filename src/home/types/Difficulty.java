package home.types;

public class Difficulty {
    private final int level;
    private final String name;

    public Difficulty(String name)
    {
        this.name = name;
        level = getLevel(name);
    }

    private static int getLevel(String name) {
        switch (name) {
            case "Easy": return 1;
            case "Medium": return 2;
            case "Hard": return 3;
            case "Very Hard": return 4;
            default: return 0;
        }
    }

    private int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getLevel() + "";
    }
}
