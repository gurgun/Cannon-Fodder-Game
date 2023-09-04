package MainPackage.Item;
import MainPackage.Character;

public class Shield extends Weapon {

    public Shield(String name, double weight, double value) {
        super(name, weight, value);
    }


    @Override
    public void specialAttack(Character target, int multiplier) {
        //shield special attack
        int turnCount = (int) value * multiplier;
        turnCount /= 8;
        if (turnCount <= 0) {
            turnCount = 1;
        }
        if (turnCount > 5) {
            turnCount = 5;
        }
        System.out.println(target.getName() + " is stunned for " + turnCount + " turns.");
        target.stun(turnCount);
    }
}
