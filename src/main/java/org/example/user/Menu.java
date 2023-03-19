package org.example.user;

import org.example.data.OrderRepository;
import org.example.data.ProductRepository;
import org.example.model.Order;
import org.example.model.Product;
import org.example.model.User;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Menu instance = null;

    private Menu() {}

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public static void showUserMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
            System.out.println("Menu");
            System.out.println("1. Show all product.");
            System.out.println("2. Buy product.");
            System.out.println("3. Log out.");
            System.out.print("Select your choice: ");
            String answer = scanner.nextLine();
            switch (answer) {
                case "1":
                    showProductList();
                    break;
                case "2":
                    buyProduct(user);
                    break;
                case "3":
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect variant");
            }
        }
    }

    private static void showProductList() {
        ProductRepository productRepository = ProductRepository.getInstance();
        List<Product> products = productRepository.getProducts();
        System.out.println(products);
    }

    private static void buyProduct(User user){
        Scanner scanner = new Scanner(System.in);
        Product product;
        while(true) {
            System.out.println("Which product you want to buy?");
            System.out.print("Enter a product: ");
            String productName = scanner.nextLine();
            product = ProductRepository.getInstance().getProductByName(productName);
            if (product != null) break;
        }
        int quantity;
        while (true) {
            System.out.println("How many ?" + "(1 - " + product.getQuantity() + " )");
            quantity = scanner.nextInt();
            if (quantity >= 1 && quantity <= product.getQuantity()) {
                System.out.println("You bought " + product.getName() + ", count " + quantity);
                break;
            };
        }
        OrderRepository orderRepository = new OrderRepository();
        orderRepository.addOrder(new Order(user,product,quantity));

    }
}
