package sg.edu.nus.iss;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to your shopping cart");
        ArrayList<String> cart = new ArrayList<String>();

        boolean exit = false;

        while (!exit) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                exit = true;
            }

            if (input.startsWith("list")) {
                if (cart.isEmpty()) {
                    System.out.println("Your cart is empty");
                } else
                    for (String item : cart) {
                        System.out.println(cart.indexOf(item) + 1 + ". " + item);
                    }
            }

            if (input.startsWith("add")) {
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
                //     content = scan.next();
                //     cart.add(content);
                // }
            }

            if (input.startsWith("delete")) {
                // int itemIndex = Integer.parseInt(input.substring(7, input.length()));
                // if (itemIndex < 1 || itemIndex > cart.size()) {
                //     System.out.println("Incorrect item index");
                // } else {
                //     String removedItem = cart.get(itemIndex - 1);
                //     cart.remove(itemIndex - 1);
                //     System.out.printf("%s removed from cart\n", removedItem);
                // }

                Scanner scan = new Scanner(input.substring(6));

                String content = "";
                while (scan.hasNext()){
                    content = scan.next();

                    int listIndex = Integer.parseInt(content);

                    if (listIndex <= cart.size()){
                        cart.remove(listIndex-1);
                    } else {
                        System.err.println("Incorrect item index");
                    }
                }
            }

        }
    }
}
