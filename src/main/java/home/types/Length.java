package home.types;

public class Length {
    private final int level;
    private final String length;

    public Length(String length)
    {
        this.length = length;
        level = getLevel(length);
    }

    private static int getLevel(String length) {
        switch (length) {
            case "Short": return 1;
            case "Medium": return 2;
            case "Long": return 3;
            case "Very Long": return 4;
            default: return 0;
        }
    }

    private int getLevel() {
        return level;
    }

    public String getName() {
        return length;
    }

    @Override
    public String toString() {
        return getLevel() + "";
    }
}
