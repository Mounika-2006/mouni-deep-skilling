import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaExpressions {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("banana");
        words.add("apple");
        words.add("orange");
        words.add("grape");
        words.add("kiwi");

        Collections.sort(words, (s1, s2) -> s1.compareTo(s2));

        System.out.println("Sorted list: " + words);
    }
}
