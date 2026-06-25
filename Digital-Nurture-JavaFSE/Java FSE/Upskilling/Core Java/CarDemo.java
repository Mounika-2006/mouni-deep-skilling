
class Car {
    String make;
    String model;
    int year;

    Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public void displayDetails() {
        System.out.println("car make" + make);
        System.out.println("car model" + model);
        System.out.println("car year" + year);
        System.out.println();
    }

}

public class CarDemo {
    public static void main(String args[]) {
        Car car1 = new Car("toyoto", "camry", 2020);

        Car car2 = new Car("honda", "civic", 2023);

        car1.displayDetails();
        car2.displayDetails();

    }
}
