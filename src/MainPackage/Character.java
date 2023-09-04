package MainPackage;

import MainPackage.Item.*;

import java.util.ArrayList;

public class Character implements IStunnable {
    protected double strength;
    protected double vitality;
    protected double intelligence;

    protected double maxHP;
    protected double HP;


    protected Inventory inventory;
    private Weapon currentWield;
    private Clothing currentWearing;

    private String name;

    private boolean isAlive;

    private boolean isStunned;
    private int stunTurn;

    private boolean isKeepAway;
    private int keepAwayTurn;


    public Character() {
        this.inventory = new Inventory();
    }

    public Character(String name, Weapon currentWield, Clothing currentWearing) {
        isStunned = false;
        this.inventory = new Inventory();
        this.name = name;
        this.currentWield = currentWield;
        this.currentWearing = currentWearing;
        this.isAlive = true;

    }

    public Character(String name, Weapon currentWield) {
        isStunned = false;
        this.inventory = new Inventory();
        this.name = name;
        this.currentWield = currentWield;
        this.isAlive = true;

    }


    public void attack(Character target) {

        double attackPoint = currentWield.getValue();

        if (target.isKeepAway()) {
            System.out.println(target.getName() + " cannot take damage for " + target.getKeepAwayTurn() + " turns.");
            return;
        }

        if (currentWield.getClass().getSimpleName().equals("Sword")) {
            attackPoint *= strength;
        } else if (currentWield.getClass().getSimpleName().equals("Shield")) {
            attackPoint *= vitality;
        } else if (currentWield.getClass().getSimpleName().equals("Wand")) {
            attackPoint *= intelligence;
        }

        if (target.currentWearing != null)
            attackPoint -= currentWield.getValue();

        target.setHP(target.getHP() - attackPoint);

        if (target.getHP() <= 0) {
            target.isAlive = false;
            System.out.printf("%s does %.0f damage. %s is dead.\n", getName(), attackPoint, target.getName());

            return;
        }
        System.out.printf("%s does %.0f damage. %s has %.0f HP left.\n", getName(), attackPoint, target.getName(), target.getHP());


    }

    public void specialAction(Character target) {
        int multiplier = 1;
        if (currentWield.getClass().getSimpleName().equals("Sword")) {
            multiplier = (int) strength;
            currentWield.specialAttack(this, multiplier);
            return;
        } else if (currentWield.getClass().getSimpleName().equals("Shield")) {
            multiplier *= (int) vitality;
        } else if (currentWield.getClass().getSimpleName().equals("Wand")) {
            multiplier = (int) intelligence;
        }
        currentWield.specialAttack(target, multiplier);
    }

    public void pick(Item item) {

        if (isItemSuitableForInventory(item)) {
            inventory.addItem(item);
            System.out.println(getClass().getSimpleName() + " picks " + item.getName() + ".");
        } else {
            System.out.println(getClass().getSimpleName() + " cannot carry this item.");
        }
    }
//    public void pick(Weapon item){
//
//        if(isItemSuitableForInventory(item)){
//            inventory.addItem(item);
//            System.out.println(getClass().getSimpleName() +" picks item");
//        }
//        else{
//            System.out.println(getClass().getSimpleName() + " cannot carry this item.");
//        }
//    }

    public void examine(Item item) {
        item.displayItem();
    }

    public void wield(Weapon weapon) {
//        if (isItemSuitableForInventory(weapon)){
//            if(currentWield != null)
//                inventory.addItem(currentWield);
//
//            currentWield = (Weapon) weapon;
//            System.out.println(getClass().getSimpleName() + " is now wielding " + currentWield.getName() + ".");
//        }
//        else {
//            System.out.println(getClass().getSimpleName() + " cannot carry this item.");
//        }

        if (currentWield != null)
            inventory.addItem(currentWield);

        currentWield = weapon;
        System.out.println(getClass().getSimpleName() + " is now wielding " + currentWield.getName() + ".");


    }

    public void wear(Clothing clothing) {
//        if (isItemSuitableForInventory(clothing)){
//            if(currentWearing != null)
//                inventory.addItem(currentWearing);
//            currentWearing = (Clothing) clothing;
//            System.out.println(getClass().getSimpleName() + " is now wearing " + currentWearing.getName() + ".");
//        }
//        else {
//            System.out.println(getClass().getSimpleName() + " cannot carry this item.");
//        }
        if (currentWearing != null)
            inventory.addItem(currentWearing);

        currentWearing = (Clothing) clothing;
        System.out.println(getClass().getSimpleName() + " is now wearing " + currentWearing.getName() + ".");
    }


    public void ListInventory(Character target) {
        if (currentWield == null)
            System.out.print(getClass().getSimpleName() + " wields nothing, ");
        else
            System.out.print(getClass().getSimpleName() + " wields " + currentWield.getName() + ", ");

        if (currentWearing == null)
            System.out.print("wears nothing. ");
        else
            System.out.print("wears " + currentWearing.getName() + ". ");


        inventory.display();

    }

    public void display() {
        System.out.println("Character Name: " + getClass().getSimpleName());
        System.out.println("HP " + getHP());
        System.out.printf("Strength: %s, Vitality: %s, Intelligence: %s \n", strength, vitality, intelligence);

    }


    public boolean isItemSuitableForInventory(Item item) {
        double wieldWeight = 0;
        double wearingWeight = 0;

        if (currentWield != null)
            wieldWeight = currentWield.getWeight();
        if (currentWearing != null)
            wearingWeight = currentWearing.getWeight();

        return inventory.getTotalWeight() + item.getWeight() + wieldWeight + wearingWeight <= strength;

    }

    @Override
    public void stun(int stunTurn) {
        isStunned = true;
        this.stunTurn = stunTurn;
    }

    @Override
    public void breakStun() {
        stunTurn -= 1;
        if (stunTurn <= 0) {
            isStunned = false;
        }

    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getVitality() {
        return vitality;
    }

    public void setVitality(double vitality) {
        this.vitality = vitality;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    public double getHP() {
        return HP;
    }

    public int getStunTurn() {
        return stunTurn;
    }

    public void setStunTurn(int stunTurn) {
        this.stunTurn = stunTurn;
    }

    public boolean isStunned() {
        return isStunned;
    }

    public boolean isKeepAway() {
        return isKeepAway;
    }

    public void setKeepAway(boolean keepAway) {
        isKeepAway = keepAway;
    }

    public void keepAway(int keepAwayTurn) {
        setKeepAway(true);
        this.keepAwayTurn = keepAwayTurn;
    }

    public void breakKeepAway() {
        this.keepAwayTurn -= 1;
        if (keepAwayTurn <= 0) {
            setKeepAway(false);
        }
    }

    public int getKeepAwayTurn() {
        return keepAwayTurn;
    }

    public void setKeepAwayTurn(int keepAwayTurn) {
        this.keepAwayTurn = keepAwayTurn;
    }

    public void setHP(double HP) {
        if (HP > getMaxHP()) {
            this.HP = getMaxHP();
            return;
        }
        this.HP = HP;
    }

    public double initializeHP() {
        return Math.round((0.7 * getVitality() + 0.2 * getStrength() + 0.1 * getIntelligence()) * 10); //HP = Round(0.7*V + 0.2*S + 0.1*I) Round ne??
    }

    public Weapon getCurrentWield() {
        return currentWield;
    }

    public void setCurrentWield(Weapon currentWield) {
        this.currentWield = currentWield;
    }
}
