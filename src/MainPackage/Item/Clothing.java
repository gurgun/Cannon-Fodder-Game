package MainPackage.Item;

public class Clothing extends Item {
    public Clothing(String name, double weight, double value)
    {
        super(name, weight, value);
    }
    @Override
    public void displayItem() {
        System.out.println(name + " has " + value + " defense point, and " + weight + " unit of weight.");
    }
}
