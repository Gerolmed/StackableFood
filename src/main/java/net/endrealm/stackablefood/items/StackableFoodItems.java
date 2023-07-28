package net.endrealm.stackablefood.items;

import net.endrealm.stackablefood.StackableFood;
import net.endrealm.stackablefood.blocks.StackableFoodBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class StackableFoodItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StackableFood.MOD_ID);


    public static final RegistryObject<Item> ASSEMBLY_BOARD = ITEMS.register("assembly_board", () -> new BlockItem(StackableFoodBlocks.ASSEMBLY_BOARD.get(), new Item.Properties()));

    public static final RegistryObject<StackedFoodItem> STACKED_FOOD = ITEMS.register("stacked_food", StackedFoodItem::new);

}
