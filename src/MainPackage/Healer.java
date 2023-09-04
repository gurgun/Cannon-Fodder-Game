package MainPackage;

import MainPackage.Item.Clothing;
import MainPackage.Item.Weapon;

import java.util.Random;

public class Healer extends Character {

    Random random = new Random();

    int randomStrength = random.ints(3, 7 + 1).findFirst().getAsInt();
    int randomVitality = random.ints(1, 5 + 1).findFirst().getAsInt();
    int randomIntelligence = random.ints(6, 10 + 1).findFirst().getAsInt();

    public Healer(String name, Weapon currentWield, Clothing currentWearing) {
        super(name, currentWield, currentWearing);
        ;
        setStrength(randomStrength);
        setVitality(randomVitality);
        setIntelligence(randomIntelligence);
        setMaxHP(initializeHP());
        setHP(getMaxHP());
        System.out.println(getClass().getSimpleName() + " created with S: " + getStrength() + ", V: " + getVitality() + ", I: " + getIntelligence() +
                ". " + getClass().getSimpleName() + " wields " + currentWield.getName() + " with " + currentWield.getValue() + " damage and " + currentWield.getWeight() + " units of weight."
                + getClass().getSimpleName() + " wears " + currentWearing.getName() + " with " + currentWearing.getValue() + " defense point and " + currentWearing.getWeight() + " units of weight. ");
    }

    public Healer(String name, Weapon currentWield) {
        super(name, currentWield);
        setStrength(randomStrength);
        setVitality(randomVitality);
        setIntelligence(randomIntelligence);
        setMaxHP(initializeHP());
        setHP(getMaxHP());
        System.out.println(getClass().getSimpleName() + " created with S: " + getStrength() + ", V: " + getVitality() + ", I: " + getIntelligence() +
                ". " + getClass().getSimpleName() + " wields " + currentWield.getName() + " with " + currentWield.getValue() + " damage and " + currentWield.getWeight() + " units of weight.");
    }

    public Healer() {

        setStrength(randomStrength);
        setVitality(randomVitality);
        setIntelligence(randomIntelligence);
        setMaxHP(initializeHP());
        setHP(getMaxHP());
        System.out.println(getClass().getSimpleName() + " created with S: " + getStrength() + ", V: " + getVitality() + ", I: " + getIntelligence() + ".");

    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        if (strength <= 10.0 && strength >= 0) {
            this.strength = strength;
        }
    }

    public double getVitality() {
        return vitality;
    }

    public void setVitality(double vitality) {
        if (vitality <= 10.0 && vitality >= 0) {
            this.vitality = vitality;
        }
    }

    public double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(double intelligence) {
        if (intelligence <= 10.0 && intelligence >= 0) {
            this.intelligence = intelligence;
        }
    }
}
