public class SwitchExpressions {

    // Labels need to be provided as an enum
    public enum Sample {
        NICE,
        BORING,
        GET_UP
    }

    public static void main(String[] args) {
        Sample sample = Sample.valueOf("BORING");

        // NEW STYLE
        System.out.println(switch (sample) {
            case NICE -> "Hello";
            case BORING -> "how are you";
            case GET_UP -> {
                System.out.println("Testing");
                yield "doing?";
            }
        });

        // OLD STYLE
        System.out.println(switch (sample) {
            case NICE:
                System.out.println("Evaluating");
                yield "Hello";
            case BORING:
                System.out.println("Evaluating some more");
            case GET_UP:
                System.out.println("And still evaluating");
                yield "doing?";
        });

    }
}