package net.endrealm.stackablefood.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class AssemblyBoardBlockEntity extends BlockEntity {
    public AssemblyBoardBlockEntity(BlockPos pos, BlockState state) {
        super(StackableFoodBlockEntities.ASSEMBLY_BOARD.get(), pos, state);
    }



    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        //Write your data into the tag
        return tag;
    }

    @Override
    public void load(@NotNull CompoundTag data) {
        super.load(data);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        // Will get tag from #getUpdateTag
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
