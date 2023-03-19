package org.example;

import org.example.manager.AuthenticationManager;
import org.example.data.OrderRepository;
import org.example.data.ProductRepository;
import org.example.data.UserRepository;
import org.example.model.User;
import org.example.user.AdminMenu;
import org.example.user.Menu;

import java.util.Scanner;

public class ShopApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthenticationManager authenticationManager = AuthenticationManager.getInstance();
        UserRepository userRepository = UserRepository.getInstance();
        ProductRepository productRepository = ProductRepository.getInstance();
        Menu menu = Menu.getInstance();
        AdminMenu adminMenu = AdminMenu.getInstance();
        OrderRepository orderRepository = OrderRepository.getInstance();

        // reg
        authenticationManager.register("user1", "password1");
        authenticationManager.register("user2", "password2");
        authenticationManager.register("admin", "adminpassword");

        System.out.println("Select your choice:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("register")|| answer.trim().equals("2")){
            System.out.println("############### REGISTRATION ###############");
            while(true) {
                System.out.println("Enter your login: ");
                String login = scanner.nextLine();
                if (UserRepository.getInstance().getUserByUsername(login) == null) {
                    System.out.println("Enter your password: ");
                    String password = scanner.nextLine();
                    authenticationManager.register(login,password);
                    System.out.println("Registration is successful");
                    break;
                }
                System.out.println("Login is exists!");
            }
        }

        System.out.println("############### AUTHORIZATION ###############");
        System.out.println("Enter your login: ");
        String login = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        // login
        User user = authenticationManager.login(login, password);
        if (user != null) {
            System.out.println("Welcome, " + user.getUsername() + "!");
            if (user.isAdmin())
                AdminMenu.showAdminMenu(user);
            else
                Menu.showUserMenu(user);
        } else {
            System.out.println("Login failed.");
        }
    }
}

