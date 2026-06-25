class Animal {
    public void makeSound(){
        System.out.println("Animal makes sound");
    }
}
class Dog extends Animal{
    @overide
    public void makeSound(){
        System.out.println("bark");
    }
}
public class InheritanceDemo{
    public static void main(String args[])
    {
        Animal animal=new Animal();
        Dog dog=new Dog();
        animal.makeSound();
        dog.makeSound();
    }
}