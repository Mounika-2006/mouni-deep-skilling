import java.util.List;

public record Person(String name, int age) {
}

public class Records {
    public static void main(String[] args) {
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Bob", 22);
        Person p3 = new Person("Charlie", 40);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        List<Person> people = List.of(p1, p2, p3);
        System.out.println("People aged 30 and above:");
        people.stream()
                .filter(person -> person.age() >= 30)
                .forEach(System.out::println);
    }
}
