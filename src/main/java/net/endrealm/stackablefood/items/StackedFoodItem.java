package net.endrealm.stackablefood.items;

import net.endrealm.stackablefood.render.FoodBlockEntity;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class StackedFoodItem extends Item {

    private final List<ItemStack> FALLBACK_RENDER = Arrays.stream((new ItemStack[] {
            new ItemStack(Items.DIAMOND_HOE, 1),
            new ItemStack(Items.OAK_LOG, 1),
            new ItemStack(Items.DIAMOND_HOE, 1)
    })).toList();

    public StackedFoodItem() {
        super(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(2f).build()));
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return FoodBlockEntity.INSTANCE.get();
            }
        });
    }

    public List<ItemStack> getItems(ItemStack stack) {
        if(stack.getTag() == null) return FALLBACK_RENDER;
        var stackData = stack.getTag().getCompound("stack_data");

        var count = stackData.getInt("item_count");
        var items = new ArrayList<ItemStack>(count);
        for (int i = 0; i < count; i++) {
            items.add(ItemStack.of(stackData.getCompound("item_"+i)));
        }

        return items;
    }

    public static ItemStack of(List<ItemStack> items) {
        var stack = new ItemStack(StackableFoodItems.STACKED_FOOD.get(), 1);
        var tagData = stack.getOrCreateTag();
        var stackData = new CompoundTag();


        stackData.putInt("item_count", items.size());

        for (int i = 0; i < items.size(); i++) {
            var tag = new CompoundTag();
            items.get(i).save(tag);
            stackData.put("item_"+i, tag);

        }

        tagData.put("stack_data", stackData);

        return stack;
    }
}
