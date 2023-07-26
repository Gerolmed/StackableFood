package net.endrealm.stackablefood.blocks.entities;

import net.endrealm.stackablefood.StackableFood;
import net.endrealm.stackablefood.blocks.StackableFoodBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class StackableFoodBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, StackableFood.MOD_ID);

    public static final RegistryObject<BlockEntityType<?>> ASSEMBLY_BOARD = BLOCK_ENTITIES.register(
            "assembly_board_be",
            () -> BlockEntityType.Builder
                    .of(AssemblyBoardBlockEntity::new, StackableFoodBlocks.ASSEMBLY_BOARD.get())
                    .build(null)
    );


}
