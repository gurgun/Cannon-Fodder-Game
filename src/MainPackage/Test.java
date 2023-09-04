package MainPackage;

import MainPackage.Item.*;
import MainPackage.Item.Sword;
import MainPackage.Item.Weapon;
import MainPackage.Inventory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Test {
    public static ArrayList<Item> allItems;
    public static ArrayList<Item> lootItems;
    public static boolean isLooting;
    public static int point = 0;
    public static Random random = new Random();


    public static void main(String[] args) throws Exception {

        isLooting = false;
        Scanner scan = new Scanner(System.in);

        System.out.println();
        System.out.println("**************************************************************");
        System.out.println("*********************** CANNON FODDER ************************");
        System.out.println("**************************************************************");
        System.out.println();

        Weapon shortSword = new Sword("ShortSword", 0.5, 3);
        Weapon longSword = new Sword("LongSword", 2, 5);
        Weapon scimitar = new Sword("Scimitar", 1, 6);

        Weapon buckler = new Shield("Buckler", 0.5, 2);
        Weapon smallShield = new Shield("SmallShield", 1.5, 4);
        Weapon towerShield = new Shield("TowerShield", 2, 5);

        Weapon boneWand = new Wand("BoneWand", 1, 0.5);
        Weapon woodWand = new Wand("WoodWand", 2, 1);

        Clothing lightArmor = new Clothing("LightArmor", 1, 1);
        Clothing mediumArmor = new Clothing("MediumArmor", 2, 2);
        Clothing hardArmor = new Clothing("HardArmor", 2.5, 4);


        Fighter fighter = new Fighter("Fighter", shortSword);
        Tank tank = new Tank("Tank", buckler);
        Healer healer = new Healer("Healer", boneWand);
        //fighter.inventory.addItem(boneWand);
        //fighter.inventory.addItem(lightArmor);


        //EnemySoldier enemySoldier = new EnemySoldier("Enemy1", shortSword, lightArmor);

        //fighter.display();
        //healer.display();

        //System.out.println(lightArmor.getClass().getSuperclass());
        //System.out.println(shortSword.getClass().getSuperclass());

        ArrayList<Weapon> weaponsList = new ArrayList<>();
        //I added 16 Swords, 2 Shields and 2 Wands to the weaponList that the enemy will randomly choose from.
        //I did this so that the enemy can randomly have a sword 80 percent of the time, a
        //shield 10 percent of the time, and a wand 10 percent of the time.
        weaponsList.add(shortSword);
        weaponsList.add(longSword);
        weaponsList.add(scimitar);
        weaponsList.add(shortSword);
        weaponsList.add(longSword);
        weaponsList.add(scimitar);
        weaponsList.add(longSword);
        weaponsList.add(scimitar);
        weaponsList.add(shortSword);
        weaponsList.add(longSword);
        weaponsList.add(scimitar);
        weaponsList.add(shortSword);
        weaponsList.add(longSword);
        weaponsList.add(scimitar);
        weaponsList.add(longSword);
        weaponsList.add(scimitar);

        weaponsList.add(smallShield);
        weaponsList.add(towerShield);

        weaponsList.add(boneWand);
        weaponsList.add(woodWand);

        ArrayList<EnemySoldier> enemyList = new ArrayList<>();
        ArrayList<Character> characterList = new ArrayList<>();
        ArrayList<Character> playerList = new ArrayList<>();

        playerList.add(fighter);
        playerList.add(tank);
        playerList.add(healer);

        characterList.add(fighter);
        characterList.add(tank);
        characterList.add(healer);
        //characterList.add(enemySoldier);
        //enemyList.add(enemySoldier);

        allItems = new ArrayList<>();

        allItems.add(shortSword);
        allItems.add(longSword);
        allItems.add(scimitar);
        allItems.add(buckler);
        allItems.add(smallShield);
        allItems.add(towerShield);
        allItems.add(boneWand);
        allItems.add(woodWand);
        allItems.add(lightArmor);
        allItems.add(mediumArmor);
        allItems.add(hardArmor);

        lootItems = new ArrayList<>();


        //fighter.listInventory();
        //fighter.pick(shortSword);

        //ArrayList<Character> enemyList = new ArrayList<>();
        //enemyList.add(enemySoldier);

        //String input = sc.nextLine();

        //String[] inputWrapped = input.split(" ");
        //String character = inputWrapped[0];
        //String action = inputWrapped[1];
        //String target = inputWrapped[2];

        //findCharacterByName(character, characterList).display();

        //enemySoldier.display();
        //fighter.listInventory();

        //fighter.listInventory();
        //enemySoldier.display();


        boolean isLevelCleared = false;
        int levelNumber = 0;
        System.out.println("Creating Level " + levelNumber + ", with " + (int) (Math.pow(2, levelNumber)) + " enemy soldier.");
        System.out.print("Entering Level " + levelNumber + "; ");
        spawnEnemy(0, characterList, enemyList, weaponsList, playerList);


        while (true) {

//            if (!isPlayerTurnFinished){
//                isPlayerTurnFinished = true;
//                isTurnFinished = false;
//                doCharacterActions(scan, itemList, characterList);
//            }
//            else if(!isTurnFinished) {
//                doEnemyActions(characterList, enemyList);
//                isPlayerTurnFinished = false;
//                isTurnFinished = true;
//
//            }

            handleKeepAway(playerList);
            while (true) {
                if (doCharacterActions(scan, lootItems, characterList))
                    break;
            }

            removeDeath(characterList, enemyList);
            doEnemyActions(playerList, enemyList);


            if (isGameOver(playerList)) {
                System.out.println("Game Over");
                System.out.println("Your point: " + point);
                System.out.println("Enter your name:");
                while (true) {
                    String name = scan.nextLine();

                    if (writeScore(name, point, "highscore")) {
                        break;
                    }
                }


            }
            if (enemyList.size() <= 0) {
                isLevelCleared = true;
            }
            if (isLevelCleared) {
                System.out.println("---LOOT PHASE---");
                System.out.println();
                System.out.println("Lootable Items");
                for (var i :
                        lootItems) {
                    System.out.print(i.getName());
                    System.out.println(" ");

                }
                System.out.println();

                isLooting = true;
                //loot phase
                while (true) {
                    if (doCharacterActions(scan, lootItems, characterList)) {
                        isLooting = false;
                        break;
                    }

                }
                lootItems.clear();
                isLevelCleared = false;
                levelNumber += 1;
                System.out.println();
                System.out.println("Creating Level " + levelNumber + " with " + (int) (Math.pow(2, levelNumber)) + " enemy soldiers.");
                spawnEnemy(levelNumber, characterList, enemyList, weaponsList, playerList);
                System.out.println();
            }
        }
    }


    public static boolean isGameOver(ArrayList<Character> playerList) {
        for (var c : playerList) {
            if (c.isAlive()) {
                return false;
            }
        }
        return true;
    }


    public static void removeDeath(ArrayList<Character> characterList, ArrayList<EnemySoldier> enemyList) {

        for (int i = 0; i < characterList.size(); i++) {
            if (!characterList.get(i).isAlive()) {
                characterList.remove(characterList.get(i));
                i=0;
            }
        }

        for (int i = 0; i < enemyList.size(); i++) {
            if (!enemyList.get(i).isAlive()) {
                point += 10;
                enemyList.get(i).dropLoot(allItems, lootItems);
                enemyList.remove(enemyList.get(i));
                i=0;

            }
        }
    }

    public static void handleKeepAway(ArrayList<Character> playerList) {
        for (var p : playerList) {
            if (!p.isAlive())
                continue;
            if (p.isKeepAway()) {
                p.breakKeepAway();
            }
        }
    }

    public static void increaseAttributesAndHP(ArrayList<Character> playerList) {
        for (var c : playerList) {
            if (!c.isAlive())
                continue;
            double currHP = c.getHP();
            c.setHP(c.getHP() + (int) c.getMaxHP() / 2);
            double healRate = c.getHP() - currHP;
            if (healRate > 0) {
                System.out.print(c.getName() + " healed by " + healRate + " HP. ");
            }
        }

        int randChar = random.nextInt(3);
        while (!playerList.get(randChar).isAlive()) {
            randChar = random.nextInt(3);
        }
        Character c = playerList.get(randChar);
        int randStat = random.nextInt(3);
        if (randStat == 0) {
            c.setStrength(c.getStrength() + 1);
            System.out.print(c.getName() + " strength increased by 1.");
        }
        if (randStat == 1) {
            c.setVitality(c.getVitality() + 1);
            System.out.print(c.getName() + " vitality increased by 1.");
        }
        if (randStat == 2) {
            c.setIntelligence(c.getIntelligence() + 1);
            System.out.print(c.getName() + " intelligence increased by 1.");
        }
    }

    public static void doEnemyActions(ArrayList<Character> playerList, ArrayList<EnemySoldier> enemyList) {

        Character tank = findCharacterByName("Tank", playerList);
        Character fighter = findCharacterByName("Fighter", playerList);
        Character healer = findCharacterByName("Healer", playerList);

//        for (EnemySoldier enemy: enemyList) {
//            if(enemy.isStunned()){
//                System.out.println(enemy.getName() +  " is stunned. Hence cannot attack!");
//                enemy.breakStun();
//                continue;
//            }
//            if(tank.isAlive()){
//                enemy.attack(tank);
//                //System.out.println("alive");
//            }
//            else
//            {
//
//                int rand = random.nextInt(2);
//                if(rand == 0 && healer.isAlive()){
//                    enemy.attack(healer);
//                }
//                else {
//                    enemy.attack(fighter);
//                }
//            }
//
//        }
        if (enemyList.size() <= 0) {
            return;
        }
        EnemySoldier e = enemyList.get(0);
        if (e.isStunned()) {
            System.out.println(e.getName() + " is stunned. Hence cannot attack! " + e.getStunTurn() + " turn/turns left");
            e.breakStun();
            return;
        }
        if (tank.isAlive()) {
            e.attack(tank);
            //System.out.println("alive");
        } else {
            while (true) {
                int rand = random.nextInt(2);

                if (rand == 0 && healer.isAlive()) {
                    e.attack(healer);
                    return;
                } else if (fighter.isAlive()) {
                    e.attack(fighter);
                    return;
                }
            }
        }
    }

    public static boolean doCharacterActions(Scanner scan, ArrayList<Item> lootItemList, ArrayList<Character> characterList) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        try {
            String[] input = getInputs(scan);
            String characterName = input[0];
            if (input[0].equals("NEXT") && isLooting) {
                return true;
            }
            if (input[0].equals("PASS")) {
                return true;
            }

            if (input[0].equals("exit")) {
                System.exit(1);
            }

            String actionName = input[1];
            String targetName = input[2];

            Character character = findCharacterByName(characterName, characterList);

            if (character == null) {
                System.out.println("No character or item named: " + characterName);
                return false;
            }

            if (actionName.equals("ListInventory")) {
                character.ListInventory(character);
                return false;
            }


            if (actionName.equals("pick") && isLooting) {
                Item item = findItemByName(targetName, lootItemList);

                if (item == null) {
                    System.out.println("No item named: " + targetName);
                    return false;
                } else {
                    if (!character.isItemSuitableForInventory(item)) {
                        System.out.println(character.getName() + " cannot carry this item.");
                        return false;
                    }
                    lootItemList.remove(item);
                    character.pick(item);
                    return !isLooting;

                }
            }
            if (actionName.equals("examine")) {
                Item item = findItemByName(targetName, character.inventory.getItems());
                if (item == null) {
                    item = findItemByName(targetName, lootItemList);

                    if (item == null) {
                        System.out.println("No item named: " + targetName);

                        return false;
                    }

                }
                character.examine(item);
                return false;
            }
            if (actionName.equals("wield") || actionName.equals("wear")) {
                Item item = findItemByName(targetName, character.inventory.getItems());
                if (item == null) {
                    item = findItemByName(targetName, lootItemList);

                    if (item == null) {
                        System.out.println("No item named: " + targetName);

                        return false;
                    } else {

                        if (character.isItemSuitableForInventory(item)) {
                            if (actionName.equals("wear")) {
                                character.wear((Clothing) item);
                                lootItemList.remove(item);
                                return !isLooting;

                            }
                            if (actionName.equals("wield")) {
                                character.wield((Weapon) item);
                                lootItemList.remove(item);
                                return !isLooting;
                            }

//                            if(invokeMethodByName(character, actionName, item))
//                                lootItemList.remove(item);
                        } else {
                            System.out.println(character.getName() + " cannot carry this item.");
                            return false;
                        }

                    }
                } else {

                    if (actionName.equals("wear")) {
                        if (!item.getClass().getSimpleName().equals("Clothing")) {
                            System.out.println("False target.");
                            return false;
                        }
                        character.wear((Clothing) item);
                        character.inventory.removeItem(item);
                        return !isLooting;

                    }
                    if (actionName.equals("wield")) {

                        if (!item.getClass().getSuperclass().getSimpleName().equals("Weapon")) {
                            System.out.println("False target.");
                            return false;
                        }
                        character.wield((Weapon) item);
                        character.inventory.removeItem(item);
                        return !isLooting;
                    }
//                    if(invokeMethodByName(character, actionName, item)){
//                        character.inventory.removeItem(item);
//                        character.inventory.display();
//                    }
                }
//                character.wield((Weapon) item););
                return !isLooting;
            }


            Character target = findCharacterByName(targetName, characterList);
            if (target == null) {
                System.out.println("No character or item named: " + targetName);
                return false;
            }
            if (actionName.equals("attack")) {

                if (!target.getClass().getSimpleName().equals("EnemySoldier")) {
                    System.out.println(character.getName() + " cannot attack " + target.getName() + "(ally).");
                    return false;
                }
            }
            if (actionName.equals("specialAction")) {
                if (!target.getClass().getSimpleName().equals("EnemySoldier")) {
                    if (character.getCurrentWield().getClass().getSimpleName().equals("Sword") || character.getCurrentWield().getClass().getSimpleName().equals("Shield")) {
                        System.out.println(character.getName() + " cannot specialAction " + target.getName() + "(ally).");
                        return false;
                    }
                }

            }
            if (actionName.equals("attack") || actionName.equals("specialAction")) {
                if (isLooting) {
                    System.out.println("Cannot attack in loot phase.");
                    return false;
                }
                if (character.isKeepAway()) {
                    System.out.println(character.getName() + " cannot attack for " + character.getKeepAwayTurn() + " turn/turns.");
                    return false;
                }

            }

            return invokeMethodByName(character, actionName, target);

//            if(isCharacter(input[2], itemList, characterList)==null){
//                System.out.println("Type again");
//
//            }
//
//            else if (isCharacter(input[2], itemList, characterList)) {
//                Character character = findCharacterByName(input[0], characterList);
//                Character target = findCharacterByName(input[2], characterList);
//
//                invokeMethodByName(character, input[1], target);
//
//            } else if (!isCharacter(input[2], itemList, characterList)) {
//                Character character = findCharacterByName(input[0], characterList);
//                Item target = findItemByName(input[2], itemList);
//                invokeMethodByName(character, input[1], target);
//
//            }
//            else{
//                return false;
//            }
        } catch (Exception e) {

            System.out.println("Wrong input! Please type again.");
            return false;
        }

    }

    public static String[] getInputs(Scanner sc) {

        String input = sc.nextLine();

        if (input.equals("exit")) {
            return new String[]{"exit"};
        }

        String[] inputWrapped = input.split(" ");
//        String character = inputWrapped[0];
//        String action = inputWrapped[1];
//        String target = inputWrapped[2];
        return inputWrapped;
    }


    public static void spawnEnemy(int levelNumber, ArrayList<Character> charList, ArrayList<EnemySoldier> enemyList, ArrayList<Weapon> weaponsList, ArrayList<Character> playerList) {

//        if (levelNumber == 0) {
//            EnemySoldier enemySoldier = new EnemySoldier("Enemy1", weapon, null);
//            charList.add(enemySoldier);
//            enemyList.add(enemySoldier);
//            return;
//        }
        //int randWeapon = random.nextInt(weaponsList.size());
        for (int i = 0; i < Math.pow(2, levelNumber); i++) {
            int randWeapon = random.nextInt(weaponsList.size());
            EnemySoldier enemySoldier = new EnemySoldier("Enemy" + (int) (Math.pow(2, levelNumber) + i), weaponsList.get(randWeapon));
            enemyList.add(enemySoldier);
            charList.add(enemySoldier);
        }
        for (int i = 0; i < charList.size(); i++) {
            System.out.print(charList.get(i).getName() + " enters. ");
        }
        if (levelNumber > 0)
            increaseAttributesAndHP(playerList);
        System.out.println();

    }

    public static boolean invokeMethodByName(Character character, String methodName, Object target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try {
            Method method = character.getClass().getMethod(methodName, target.getClass().getSuperclass());
            method.invoke(character, target);
            return true;

        } catch (NoSuchMethodException e) {
            System.out.println("Wrong! Please type again.");
            return false;
        }
    }


    public static Character findCharacterByName(String name, ArrayList<Character> charList) {

        for (Character c : charList) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
//        System.out.println("No character named: " + name);
        return null;
    }

    public static Item findItemByName(String name, ArrayList<Item> itList) {

        for (Item i : itList) {
            if (i.getName().equals(name)) {
                return i;
            }
        }

        return null;
    }

//
//    public static boolean isCharacter(String name, ArrayList<Item> itList, ArrayList<Character> charList) {
//        for (Item i : itList) {
//            if (i.getName().equals(name)) {
//                return false;
//            }
//        }
//        for (Character c : charList) {
//            if (c.getName().equals(name)) {
//                return true;
//            }
//        }
//        System.out.println("No character or item named: " + name);
//        return false;
//    }

    public static boolean writeScore(String name, int score, String fileName) {
        Map<Integer, String> scoreData = new TreeMap<>(Collections.reverseOrder());


        try {
            File file = new File(fileName + ".txt");
            file.createNewFile();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();

                String[] dataSplitted = data.split(":");
                String nameData = dataSplitted[0];
                if (nameData.equals(name)) {
                    System.out.println("This username is already taken. Enter a new username.");
                    return false;
                }
                if (name.contains(":")) {
                    System.out.println("Enter a new username.");
                }
                scoreData.put(Integer.parseInt(dataSplitted[1]), dataSplitted[0]);
            }
            scoreData.put(score, name);
            FileWriter fileWriter = new FileWriter(fileName + ".txt");
            System.out.println("-----HIGH SCORES-----");
            int i = 1;
            for (Map.Entry<Integer, String> entry : scoreData.entrySet()) {

                System.out.println(i + "-" + entry.getValue() + ":" + entry.getKey());
                fileWriter.write(entry.getValue() + ":" + entry.getKey() + "\n");
                i++;
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {

            System.out.println("An error occurred while creating a file.");
            e.printStackTrace();
            return false;
        }
    }
}












