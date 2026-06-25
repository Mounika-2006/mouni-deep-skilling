import java.util.Arrays;
import java.util.Comparator;

// Product Class
class Product {
    int productId;
    String productName;
    String category;

    // Constructor
    Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    // Method to display product details
    void display() {
        System.out.println("Product ID   : " + productId);
        System.out.println("Product Name : " + productName);
        System.out.println("Category     : " + category);
    }
}

// Main Class
public class EcommerceSearch {

    // Linear Search Method
    public static Product linearSearch(Product[] products, int searchId) {
        for (Product product : products) {
            if (product.productId == searchId) {
                return product;
            }
        }
        return null;
    }

    // Binary Search Method
    public static Product binarySearch(Product[] products, int searchId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (products[mid].productId == searchId) {
                return products[mid];
            } else if (products[mid].productId < searchId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        // Array of Products
        Product[] products = {
                new Product(104, "Laptop", "Electronics"),
                new Product(101, "Mobile", "Electronics"),
                new Product(105, "Shoes", "Fashion"),
                new Product(102, "Watch", "Accessories"),
                new Product(103, "Bag", "Fashion")
        };

        int searchId = 103;

        // Linear Search
        System.out.println("===== LINEAR SEARCH =====");
        Product linearResult = linearSearch(products, searchId);

        if (linearResult != null) {
            System.out.println("Product Found:");
            linearResult.display();
        } else {
            System.out.println("Product Not Found");
        }

        // Sort array for Binary Search
        Arrays.sort(products, Comparator.comparingInt(p -> p.productId));

        // Binary Search
        System.out.println("\n===== BINARY SEARCH =====");
        Product binaryResult = binarySearch(products, searchId);

        if (binaryResult != null) {
            System.out.println("Product Found:");
            binaryResult.display();
        } else {
            System.out.println("Product Not Found");
        }
    }
}