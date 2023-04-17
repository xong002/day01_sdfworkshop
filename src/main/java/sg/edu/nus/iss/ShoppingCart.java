package sg.edu.nus.iss;

import java.util.ArrayList;

public class ShoppingCart {
    
    ArrayList<Item> item;

    ShoppingCart(){
        this.item = new ArrayList<Item>();
    }

    public void list(){
        System.out.println("List shopping cart");
    }

}
