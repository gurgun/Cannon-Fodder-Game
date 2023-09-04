package MainPackage.Item;
import MainPackage.Character;


public class Weapon extends Item {

    public Weapon(String name, double weight, double value) {
        super(name, weight, value);
    }


    @Override
    public void displayItem() {
        System.out.println(name + " has " + value + " damage, and " + weight + " unit of weight.");
    }

    public void specialAttack(Character target, int multiplier){

    }
}
