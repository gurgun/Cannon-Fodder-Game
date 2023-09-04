package MainPackage.Item;
import MainPackage.*;

import java.util.ArrayList;

abstract public class Item {
    protected String name;
    protected double weight;
    protected double value;


    public Item(String name, double weight, double value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    abstract public void displayItem();


}














