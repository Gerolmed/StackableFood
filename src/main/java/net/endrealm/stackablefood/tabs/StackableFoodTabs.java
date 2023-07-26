package net.endrealm.stackablefood.tabs;

import net.endrealm.stackablefood.StackableFood;
import net.endrealm.stackablefood.items.StackableFoodItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class StackableFoodTabs {


    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StackableFood.MOD_ID);
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> StackableFoodItems.EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(StackableFoodItems.EXAMPLE_ITEM.get());
                output.accept(StackableFoodItems.EXAMPLE_BLOCK_ITEM.get());
            }).build());
}
