package net.endrealm.stackablefood.blocks.entities.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.endrealm.stackablefood.blocks.entities.AssemblyBoardBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class AssemblyBoardBlockEntityRenderer implements BlockEntityRenderer<AssemblyBoardBlockEntity> {
    public AssemblyBoardBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {

    }

    @Override
    public void render(@NotNull AssemblyBoardBlockEntity blockEntity, float partialTick,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource,
                       int combinedLight, int combinedOverlay
    ) {
        var itemRenderer = Minecraft.getInstance().getItemRenderer();
        poseStack.pushPose();


        blockEntity.getItems().forEach(itemStack -> {
            poseStack.translate(0,1,0);
            poseStack.pushPose();

            itemRenderer.render(itemStack, ItemDisplayContext.GROUND, true, poseStack, bufferSource, combinedLight, combinedLight, null);

            poseStack.popPose();

        });


        poseStack.popPose();
    }
}
