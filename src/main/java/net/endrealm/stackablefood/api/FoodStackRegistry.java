package net.endrealm.stackablefood.api;

import net.minecraft.world.item.Item;

import java.util.HashMap;

public class FoodStackRegistry {


    private static final FoodStackRegistry INSTANCE = new FoodStackRegistry();
    public static FoodStackRegistry get() { return INSTANCE; }

    private final HashMap<Item, FoodRenderer> renderLookUp = new HashMap<>();

    public void register(FoodRenderer renderer, Item item) {
        renderLookUp.put(item, renderer);
    }

    public FoodRenderer get(Item item) {
        return renderLookUp.get(item);
    }

    public boolean contains(Item item) {
        return renderLookUp.containsKey(item);
    }

}
