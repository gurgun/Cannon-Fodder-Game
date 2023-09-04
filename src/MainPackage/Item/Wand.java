package MainPackage.Item;
import MainPackage.Character;

public class Wand extends Weapon {

    public Wand(String name, double value, double weight) {
        super(name, weight, value);
    }

    @Override
    public void specialAttack(Character target, int multiplier) {
        double targetHP = target.getHP();
        double healAmount = multiplier * value;
        healAmount /= 2;

        if (targetHP + healAmount > target.getMaxHP()) {
            target.setHP(target.getMaxHP());
        } else {
            target.setHP(targetHP + healAmount);
        }
        System.out.printf("%s is healed by %.0f. %s has %.0f HP left.\n", target.getName(), target.getHP() - targetHP, target.getName(), target.getHP());
    }
}
