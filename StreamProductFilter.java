import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " ($" + String.format("%.2f", price) + ")";
    }
}

public class StreamProductFilter {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(Arrays.asList(
            new Product("Laptop", 1200.00),
            new Product("Mouse", 25.50),
            new Product("Monitor", 350.99),
            new Product("Keyboard", 60.00),
            new Product("Webcam", 45.75),
            new Product("Headphones", 89.90),
            new Product("Desk Mat", 15.00)
        ));

        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Available Products ---");
        products.forEach(p -> System.out.println("- " + p));

        System.out.print("\nEnter the minimum price to filter (e.g., 50.00): $");

        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Using default price of $50.00.");
            scanner.next(); // Consume invalid input
        }
        double filterPrice = scanner.nextDouble();
        scanner.close();

        long expensiveProductCount = products.stream()
            // Intermediate operation: Filter products whose price is greater than the user's input
            .filter(product -> product.getPrice() > filterPrice) 
            // Terminal operation: Count the remaining elements
            .count(); 

        System.out.println("\n--- Analysis Results ---");

        System.out.println("Products costing more than $" + String.format("%.2f", filterPrice) + ":");
        products.stream()
            .filter(product -> product.getPrice() > filterPrice)
            .forEach(product -> System.out.println("-> " + product.getName()));

        System.out.println("\nTotal number of expensive products found: **" + expensiveProductCount + "**");
    }
}