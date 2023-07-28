package net.endrealm.stackablefood.api;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface FoodRenderer {
    void render(ItemStack itemStack, PoseStack poseStack, int combinedLight, MultiBufferSource bufferSource, Level level);

    float getHeightMod();
}
