package net.endrealm.stackablefood.blocks.entities.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.endrealm.stackablefood.api.FoodStackRegistry;
import net.endrealm.stackablefood.blocks.entities.AssemblyBoardBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import org.jetbrains.annotations.NotNull;

public class AssemblyBoardBlockEntityRenderer implements BlockEntityRenderer<AssemblyBoardBlockEntity> {


    public AssemblyBoardBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {

    }

    @Override
    public void render(@NotNull AssemblyBoardBlockEntity blockEntity, float partialTick,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource,
                       int combinedLight, int combinedOverlay
    ) {
        var items = blockEntity.getItems();
        poseStack.pushPose();

        poseStack.translate(.5, 1 / 16f, .5);


        items.forEach(itemStack -> FoodStackRegistry.get().getSafe(itemStack.getItem())
                .render(itemStack, poseStack, combinedLight, bufferSource, blockEntity.getLevel()));


        poseStack.popPose();
    }
}
