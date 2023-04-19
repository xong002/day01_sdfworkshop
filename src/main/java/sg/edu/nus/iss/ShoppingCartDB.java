package sg.edu.nus.iss;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartDB {

    public static void list(ArrayList<String> cart) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty");
        } else
            for (String item : cart) {
                System.out.println(cart.indexOf(item) + 1 + ". " + item);
            }
    }

    public static void add(String input, ArrayList<String> cart) {
        String items = input.substring(4, input.length());
        String[] result = items.split(",");
        for (String item : result) {
            String lower = item.trim().toLowerCase();
            if (cart.contains(lower)) {
                System.out.printf("You have %s in your cart\n", lower);
            } else {
                cart.add(lower);
                System.out.printf("%s added to cart\n", lower);
            }
        }
        // input = input.replace(',',' ');
        // Scanner scan = new Scanner(input.substring(4));

        // String content = "";
        // while(scan.hasNext()){
        // content = scan.next();
        // cart.add(content);
        // }
    }

    public static void delete(String input, ArrayList<String> cart) {
        Scanner scan = new Scanner(input.substring(6));

        String content = "";
        while (scan.hasNext()) {
            content = scan.next();

            int listIndex = Integer.parseInt(content);

            if (listIndex <= cart.size()) {
                cart.remove(listIndex - 1);
            } else {
                System.err.println("Incorrect item index");
            }
        }

        // int itemIndex = Integer.parseInt(input.substring(7, input.length()));
        // if (itemIndex < 1 || itemIndex > cart.size()) {
        // System.out.println("Incorrect item index");
        // } else {
        // String removedItem = cart.get(itemIndex - 1);
        // cart.remove(itemIndex - 1);
        // System.out.printf("%s removed from cart\n", removedItem);
        // }
    }

    public static void save(String dirPathFileName, ArrayList<String> cart, String fileName) throws IOException {
        File file = new File(dirPathFileName);
        if (file.exists()) {
            FileWriter fw = new FileWriter(file, false);
            String writeToFile = "";
            for (String i : cart) {
                writeToFile = writeToFile + i + "\n";
            }
            fw.write(writeToFile);
            fw.close();
            // cart.clear();
            System.out.println(fileName + ", your cart has been saved");
        } else {
            System.out.println("Please log in as user first.");
        }
    }
}
