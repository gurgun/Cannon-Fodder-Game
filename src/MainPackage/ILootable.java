package MainPackage;


import MainPackage.Item.Item;

import java.util.ArrayList;

public interface ILootable {
    void dropLoot(ArrayList<Item> allItems, ArrayList<Item> lootItems);
}
