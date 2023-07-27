package net.endrealm.stackablefood.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AssemblyBoardBlockEntity extends BlockEntity {


    private final List<ItemStack> items = new ArrayList<>();

    public AssemblyBoardBlockEntity(BlockPos pos, BlockState state) {
        super(StackableFoodBlockEntities.ASSEMBLY_BOARD.get(), pos, state);
    }



    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag data = new CompoundTag();

        saveTo(data);
        return data;
    }
    public  void saveTo(CompoundTag data) {
        data.putInt("item_count", items.size());

        for (int i = 0; i < items.size(); i++) {
            data.put("item_"+i, items.get(i).getOrCreateTag());

        }
    }

    @Override
    public void load(@NotNull CompoundTag data) {

        super.load(data);
        items.clear();
        var count = data.getInt("item_count");
        for (int i = 0; i < count; i++) {
            items.add(ItemStack.of(data.getCompound("item_"+i)));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag data) {
        super.saveAdditional(data);
        saveTo(data);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        // Will get tag from #getUpdateTag
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public boolean addItem(ItemStack newItem) {
        items.add(newItem);
        setChanged();
        getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);

        return true;
    }

    public List<ItemStack> clearAllItems() {
        var old = new ArrayList<>(items);
        items.clear();
        setChanged();
        getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
        return old;
    }
}
