package YearUp.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class Store
{
    public static void main(String[] args)
    {
        // Initialize variables
        ArrayList<Product> inventory = new ArrayList<Product>();
        ArrayList<Product> cart = new ArrayList<Product>();
        double totalAmount = 0.0;

        // Load inventory from CSV file
        loadInventory("products.csv", inventory);

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Display menu and get user choice until they choose to exit
        while (choice != 3)
        {
            System.out.println("Welcome to the Online com.pluralsight.Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            // Call the appropriate method based on user choice
            switch (choice)
            {
                case 1:
                    displayProducts(inventory, cart, scanner);
                    break;
                case 2:
                    displayCart(cart, scanner, totalAmount);
                    break;
                case 3:
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    public static void loadInventory(String fileName, ArrayList<Product> inventory)
    {
        try
        {
            String line;

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] tokens = line.split("\\|");

                if (tokens.length == 3)
                {
                    String sku = tokens[0];
                    String productName = tokens[1];
                    double price = Double.parseDouble(tokens[2]);

                    inventory.add(new Product(sku, productName, price));
                }
            }
            bufferedReader.close();
        }catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        // This method should read a CSV file with product information and
        // populate the inventory ArrayList with Yearup.pluralsight.Product objects. Each line
        // of the CSV file contains product information in the following format:
        //
        // id,name,price
        //
        // where id is a unique string identifier, name is the product name,
        // price is a double value representing the price of the product
    }

    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner)
    {
        if (inventory.isEmpty())
        {
            System.out.println("No products available in the inventory.");
            return;
        }

        boolean running = true;

        System.out.println("Product List:");
        for (Product product : inventory)
        {
            System.out.println(product.toString());
        }

        while(running)
        {
            System.out.println("Enter the SKU or (X) to exit: ");
            String productSKU = scanner.nextLine();

            if (productSKU.equalsIgnoreCase("X"))
            {
                running = false;
            }

            Product productAdd = findProductById(productSKU, inventory);
            if(productAdd != null)
            {
                cart.add(productAdd);
                System.out.println(productAdd.getName() + " added to your cart.");
            }
            else
            {
                System.out.println("Product not found.");
            }
        }
        // This method should display a list of products from the inventory,
        // and prompt the user to add items to their cart. The method should
        // prompt the user to enter the ID of the product they want to add to
        // their cart. The method should
        // add the selected product to the cart ArrayList.
    }

    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount)
    {
        boolean running = true;

        if (cart.isEmpty())
        {
            System.out.println("No items in cart!");
        }
        else
        {
            for(Product product : cart)
            {
                totalAmount += product.getPrice();
            }
            System.out.printf("Total Amount: $%.2f%n", totalAmount);
        }

        while(running)
        {
            System.out.println("Enter the SKU to remove from cart or (X) to exit: ");
            String productSKU = scanner.nextLine();

            if (productSKU.equalsIgnoreCase("X"))
            {
                running = false;
            }

            Product productToRemove = findProductById(productSKU, cart);
            if (productToRemove != null)
            {
                cart.remove(productToRemove);
                System.out.println(productToRemove.getName() + " removed from your cart.");
            }
            else
            {
                System.out.println("Product not found in cart.");
            }
        }
        // This method should display the items in the cart ArrayList, along
        // with the total cost of all items in the cart. The method should
        // prompt the user to remove items from their cart by entering the ID
        // of the product they want to remove. The method should update the cart ArrayList and totalAmount
        // variable accordingly.
    }

    public static void checkOut(ArrayList<Product> cart, double totalAmount)
    {

        // This method should calculate the total cost of all items in the cart,
        // and display a summary of the purchase to the user. The method should
        // prompt the user to confirm the purchase, and deduct the total cost
        // from their account if they confirm.
    }

    public static Product findProductById(String id, ArrayList<Product> inventory)
    {
        for (Product product : inventory)
        {
            if (product.getSku().equalsIgnoreCase(id))
            {
                return product;
            }
        }
        return null;
        // This method should search the inventory ArrayList for a product with
        // the specified ID, and return the corresponding com.pluralsight.Product object. If
        // no product with the specified ID is found, the method should return
        // null.
    }
}
