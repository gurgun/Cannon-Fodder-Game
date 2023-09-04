package MainPackage;

import MainPackage.Item.*;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> items;


    public Inventory() {
        items = new ArrayList<>();
    }


    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public double getTotalWeight(){
        double sum = 0;
        for (Item item:items) {
            sum += item.getWeight();

        }
        return sum;
    }

    public void display(){

        if(items.size() <= 0){
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.print("Inventory: ");

        for (int i = 0; i < items.size() ; i++) {
            System.out.print(items.get(i).getName());
            if(i + 1 < items.size())
                System.out.print(", ");
        }
        System.out.println(".");

    }


    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }


}
