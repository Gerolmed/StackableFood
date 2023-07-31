package net.endrealm.stackablefood.api;

import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Optional;

public class FoodStackRegistry {


    private static final FoodStackRegistry INSTANCE = new FoodStackRegistry();
    public static FoodStackRegistry get() { return INSTANCE; }

    private static FoodRenderer FALLBACK = new StaticFoodRenderer(RenderTransform.ITEM_TRANSFORM);

    private final HashMap<Item, FoodRenderer> renderLookUp = new HashMap<>();

    public void register(FoodRenderer renderer, Item item) {
        renderLookUp.put(item, renderer);
    }

    public FoodRenderer get(Item item) {
        return renderLookUp.get(item);
    }
    public FoodRenderer getSafe(Item item) {
        return Optional.ofNullable(renderLookUp.get(item)).orElse(FALLBACK);
    }

    public boolean contains(Item item) {
        return renderLookUp.containsKey(item);
    }

}
