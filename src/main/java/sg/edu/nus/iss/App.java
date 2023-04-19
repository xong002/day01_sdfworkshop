package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

            if (input.equals("exit")) {
                scanner.close();
                exit = true;
            }

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

            if (input.startsWith("list")) {
                ShoppingCartDB.list(cart);
            }

            if (input.startsWith("add")) {
                ShoppingCartDB.add(input, cart);
            }

            if (input.startsWith("delete")) {
                ShoppingCartDB.delete(input, cart);
            }

            if (input.startsWith("save")) {
                ShoppingCartDB.save(dirPathFileName, cart, fileName);
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
