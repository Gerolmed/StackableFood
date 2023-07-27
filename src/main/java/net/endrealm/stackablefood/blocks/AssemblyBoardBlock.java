package net.endrealm.stackablefood.blocks;

import net.endrealm.stackablefood.blocks.entities.AssemblyBoardBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AssemblyBoardBlock extends Block implements EntityBlock {

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 1, 16);

    public AssemblyBoardBlock() {
        super(Properties.of().mapColor(MapColor.STONE));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new AssemblyBoardBlockEntity(pos, state);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if(level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        var enchanter = (AssemblyBoardBlockEntity) level.getBlockEntity(pos);

        var itemStack = player.getMainHandItem();
        boolean emptyClick = itemStack.getItem() == Items.AIR;
        boolean sneak = player.isShiftKeyDown();
        if(emptyClick && sneak) {
            // Remove all items
            var items = enchanter.clearAllItems();
            for(var item : items) {
                level.addFreshEntity(new ItemEntity(level, pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5, item));
            }
        } else if(!emptyClick) {
            // Add an item
            var itemAdd = itemStack.copy();

            itemAdd.setCount(1);

            boolean added = enchanter.addItem(itemAdd);

            if(added) {
                itemStack.setCount(itemStack.getCount() - 1);
            }
            return InteractionResult.CONSUME;
        }

        return InteractionResult.SUCCESS;
    }
}
