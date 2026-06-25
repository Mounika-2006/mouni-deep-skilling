public class PatternMatchingforSwitch {
    public static void determineType(Object obj) {
        String message = switch (obj) {
            case Integer i -> "Integer with value " + i;
            case String s -> "String with value \"" + s + "\"";
            case Double d -> "Double with value " + d;
            case Float f -> "Float with value " + f;
            case Long l -> "Long with value " + l;
            case Boolean b -> "Boolean with value " + b;
            case null -> "Null object";
            default -> "Unknown type: " + obj.getClass().getName();
        };

        System.out.println(message);
    }

    public static void main(String[] args) {
        determineType(42);
        determineType("Hello World");
        determineType(3.14);
        determineType(2.5f);
        determineType(100L);
        determineType(true);
        determineType(null);
        determineType(new Object());
    }
}
