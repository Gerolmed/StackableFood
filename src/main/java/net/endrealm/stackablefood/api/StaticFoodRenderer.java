package net.endrealm.stackablefood.api;

import com.mojang.blaze3d.vertex.PoseStack;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

@RequiredArgsConstructor
public class StaticFoodRenderer implements FoodRenderer {

    private final RenderTransform transform;

    @Override
    public void render(ItemStack itemStack, PoseStack poseStack, int combinedLight, MultiBufferSource bufferSource, Level level) {
        var itemRenderer = Minecraft.getInstance().getItemRenderer();

        poseStack.pushPose();
        apply(poseStack, transform.getPreTranslate());
        poseStack.rotateAround(this.transform.getRotate(), transform.getRotateVec().x, transform.getRotateVec().y, transform.getRotateVec().z);

        itemRenderer.renderStatic(itemStack, ItemDisplayContext.GROUND, combinedLight, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, level, 0);

        poseStack.popPose();

        apply(poseStack, transform.getPostTranslate());
    }

    private void apply(PoseStack stack, Vector3f offset) {
        stack.translate(offset.x, offset.y, offset.z);
    }

    public static StaticFoodRenderer ITEM = new StaticFoodRenderer(RenderTransform.ITEM_TRANSFORM);
    public static StaticFoodRenderer BLOCK = new StaticFoodRenderer(RenderTransform.BLOCK_TRANSFORM);
}
