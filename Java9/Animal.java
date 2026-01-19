public interface Animal {

    int amountOfHorns();
    int amountOfTeeth();
    int amountOfFur();
    int eyeSize();

    default int score() {
        return this.scariness() + this.cuteness();
    }

    private int scariness() {
        return this.amountOfHorns() * 2 + this.amountOfTeeth();
    }

    private int cuteness() {
        return this.amountOfFur() + this.eyeSize();
    }

    public static String description() {
        return "Animals are " + randomDescription();
    }

    private static String randomDescription() {
        switch ((int)(Math.random() * 3)) {
            case 0: return "God's creatures";
            case 1: return "fauna";
        }
        return "named";
    }
}