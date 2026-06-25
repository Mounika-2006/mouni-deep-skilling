
import java.util.*;
class Car {
    String make;
    String model;
    int year;
    Car(String make,String model,int year){
        this.make=make;
        this.model=model;
        this.year=year;
    }
    public void displayDetails(){
        System.out.println("car make"+make);
        System.out.println("car model"+model);
        System.out.println("car year"+year);
        System.out.println();
    }
    

}
public class cardemo{
    public static void main(String args[]){
        Car car1=new Car();
        car1.make="Toyota";
        car1.model="Camry";
        car1.year=2020;

        Car car2=new Car();
        car2.make="honda";
        car2.model="civic";
        car2.year=2023;
        car1.displayDetails();
        car2.displayDetails();

    }
}
