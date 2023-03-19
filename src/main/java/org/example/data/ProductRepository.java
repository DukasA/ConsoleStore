package org.example.data;

import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static ProductRepository instance = null;
    private List<Product> products;

    private ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("Product 1", 10, 100.0));
        products.add(new Product("Product 2", 20, 200.0));
        products.add(new Product("Product 3", 30, 300.0));
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "ProductRepository{ products = " + products + " }";
    }
    public Product getProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
}
