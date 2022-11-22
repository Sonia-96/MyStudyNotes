public class Dog implements Comparable<Dog> {
    String name;
    int age;

    Dog(String n, int a) {
        name = n;
        age = a;
    }

    @Override
    public int compareTo(Dog o) {
        return name.compareTo(o.name);
    }
}
