package net.endrealm.stackablefood.blocks;

import net.endrealm.stackablefood.StackableFood;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class StackableFoodBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, StackableFood.MOD_ID);

    public static final RegistryObject<Block> ASSEMBLY_BOARD = BLOCKS.register("assembly_board", AssemblyBoardBlock::new);

}
