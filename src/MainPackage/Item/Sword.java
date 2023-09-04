package MainPackage.Item;
import MainPackage.Character;

public class Sword extends Weapon {


    public Sword(String name, double weight, double value) {
        super(name, weight, value);
    }

    @Override
    public void specialAttack(Character target, int multiplier) {
        int turnValue = multiplier * (int) value;
        turnValue /= 8;

        if (turnValue <= 0) {
            turnValue = 1;
        }

        if (turnValue > 5) {
            turnValue = 5;
        }

        System.out.println(target.getName() + " cannot take or cause any damage for " + (turnValue - 1) + " turn/turns.");
        target.keepAway(turnValue);
    }
}
