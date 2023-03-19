package org.example.user;

import org.example.data.OrderRepository;
import org.example.data.ProductRepository;
import org.example.data.UserRepository;
import org.example.model.Order;
import org.example.model.Product;
import org.example.model.User;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static AdminMenu instance = null;

    private AdminMenu() {

    }

    public static AdminMenu getInstance() {
        if (instance == null) {
            instance = new AdminMenu();
        }
        return instance;
    }

    public static void showAdminMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
            System.out.println("Menu");
            System.out.println("1. Show all product.");
            System.out.println("2. Buy product.");
            System.out.println("3. Show user list.");
            System.out.println("4. Set quantity of product.");
            System.out.println("5. Change role.");
            System.out.println("6. Log out.");
            System.out.print("Select your choice: ");
            String answer = scanner.nextLine();
            switch (answer.trim()) {
                case "1":
                    showProductList();
                    break;
                case "2":
                    buyProduct(user);
                    break;
                case "3":
                    showUserList();
                    break;
                case "4":
                    setQuantityOfProduct();
                    break;
                case "5":
                    changeRole();
                    break;
                case "6":
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect variant");
            }
        }
    }

    private static void showUserList() {
        UserRepository userRepository = UserRepository.getInstance();
        List<User> users = userRepository.getUsers();
        System.out.println(users);
    }

    private static void showProductList() {
        ProductRepository productRepository = ProductRepository.getInstance();
        List<Product> products = productRepository.getProducts();
        System.out.println(products);
    }

    private static void setQuantityOfProduct(){
        Scanner scanner = new Scanner(System.in);
        Product product;
        while(true) {
            System.out.println("Which product you want to increase/decrease ?");
            System.out.print("Enter a product: ");
            String productName = scanner.nextLine();
            product = ProductRepository.getInstance().getProductByName(productName);
            if (product != null) break;
        }
        System.out.print("Enter the amount that should be:");
        int quantity = scanner.nextInt();
        product.setQuantity(quantity);
        System.out.println("Quantity was changed to " + quantity);
    }

    private static void changeRole(){
        User user;
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Enter user name: ");
            user = UserRepository.getInstance().getUserByUsername(scanner.nextLine());
            if (user != null) break;
        }

        while(true) {
            System.out.println("Enter new role for user");
            System.out.println("1. Admin");
            System.out.println("2. User");
            String role = scanner.nextLine();
            if (role.equalsIgnoreCase("admin") || role.trim().equals("1")){
                user.setAdmin(true);
                System.out.println("Role was changed");
                break;
            }
            else if (role.equalsIgnoreCase("user") || role.trim().equals("2")){
                user.setAdmin(false);
                System.out.println("Role was changed");
                break;
            }
        }
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
            if (quantity >= 1 && quantity <= product.getQuantity()){
                System.out.println("You bought " + product.getName() + ", count " + quantity);
                break;
            };
        }
        OrderRepository orderRepository = new OrderRepository();
        orderRepository.addOrder(new Order(user,product,quantity));
    }
}
