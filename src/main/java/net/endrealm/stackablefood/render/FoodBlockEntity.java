package net.endrealm.stackablefood.render;

import com.google.common.util.concurrent.AtomicDouble;
import com.mojang.blaze3d.vertex.PoseStack;
import net.endrealm.stackablefood.api.FoodStackRegistry;
import net.endrealm.stackablefood.items.StackedFoodItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class FoodBlockEntity extends BlockEntityWithoutLevelRenderer {
    public static Supplier<FoodBlockEntity> INSTANCE = new Supplier<>() {
        private FoodBlockEntity ref;

        @Override
        public FoodBlockEntity get() {
            if (ref != null) return ref;

            return ref = new FoodBlockEntity(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        }
    };

    public FoodBlockEntity(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {


        var items = ((StackedFoodItem) pStack.getItem()).getItems(pStack);
        var totalHeight = new AtomicDouble(0);
        items.forEach(itemStack -> totalHeight.addAndGet(FoodStackRegistry.get().get(itemStack.getItem()).getHeightMod()));
        var scale = Math.min(1 / (float)totalHeight.get(), 1);

        poseStack.pushPose();

        if(pDisplayContext == ItemDisplayContext.GUI) {

            if(totalHeight.get() > 1) {
                poseStack.translate(0, .15, 0);
            }

            poseStack.translate(.25, .35, 0);
            poseStack.pushPose();
            poseStack.rotateAround(new Quaternionf(0.354,0.354,0.146,0.854), 1,1, 0);

        } else {
            poseStack.translate(.5, .5, .5);
        }

        poseStack.pushPose();

        if(pDisplayContext == ItemDisplayContext.GUI) {
            poseStack.scale(scale, scale, scale);
        }


        items.forEach(itemStack -> {
            FoodStackRegistry.get().get(itemStack.getItem())
                    .render(itemStack, poseStack, pPackedLight, pBuffer, null);
        });

        poseStack.popPose();
        if(pDisplayContext == ItemDisplayContext.GUI) {
            poseStack.popPose();
        }
        poseStack.popPose();

    }
}
