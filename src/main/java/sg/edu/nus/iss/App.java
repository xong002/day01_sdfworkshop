package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {

        Path dbDir;

        if (args.length == 0) {
            System.out.println("Argument is empty, default 'db' folder created");
            dbDir = Paths.get("db");
        } else {
            dbDir = Paths.get(args[0]);
        }

        try {
            Files.createDirectories(dbDir);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Welcome to your shopping cart");
        ArrayList<String> cart = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String fileName = "";
        String dirPathFileName = "";

        while (!exit) {
            String input = scanner.nextLine();

            if (input.startsWith("login")) {
                cart.clear();
                fileName = input.substring(6, input.length()).trim();
                dirPathFileName = dbDir + File.separator + fileName;
                File file = new File(dirPathFileName);
                if (file.exists()) {
                    if (file.length() == 0) {
                        System.out.println(fileName + ", your cart is empty");
                    } else {
                        System.out.println(fileName + ", your cart contains the following items:");
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                        String line;
                        Integer index = 1;
                        while ((line = br.readLine()) != null) {
                            System.out.println(index.toString() + ". " + line);
                            index++;
                            cart.add(line);
                        }
                        br.close();
                        fr.close();
                    }
                } else {
                    file.createNewFile();
                    System.out.println(fileName + ", your cart is empty");
                }
            }

            if (input.equals("exit")) {
                exit = true;
            }

            // show all saved cart items + items added to 'cart'
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
                // content = scan.next();
                // cart.add(content);
                // }
            }

            if (input.startsWith("delete")) {
                // int itemIndex = Integer.parseInt(input.substring(7, input.length()));
                // if (itemIndex < 1 || itemIndex > cart.size()) {
                // System.out.println("Incorrect item index");
                // } else {
                // String removedItem = cart.get(itemIndex - 1);
                // cart.remove(itemIndex - 1);
                // System.out.printf("%s removed from cart\n", removedItem);
                // }

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
            }

            if (input.startsWith("save")) {
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

            if (input.trim().startsWith("users")) {
                File file = dbDir.toFile();
                File[] userList = file.listFiles();
                // check if file exists and if dir is empty
                if (userList.length > 0) {
                    System.out.println("The following users are registered:");
                    int index = 1;
                    for (File f : userList) {
                        System.out.println(index + ". " + f.getName());
                        index++;
                    }
                } else System.out.println("No users found");
            }
        }
    }
}
