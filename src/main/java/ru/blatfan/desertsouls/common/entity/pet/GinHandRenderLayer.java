package ru.blatfan.desertsouls.common.entity.pet;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
import software.bernie.geckolib.util.RenderUtils;
import java.util.Objects;

public class GinHandRenderLayer<T extends AbstractGinEntity> extends GeoRenderLayer<T> {
    public GinHandRenderLayer(GeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
    }
    
    @Override
    public void renderForBone(PoseStack poseStack, T animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ItemStack itemstack = animatable.getHand();
        if (!itemstack.isEmpty() && Objects.equals(bone.getName(), "itemPos")){
            poseStack.pushPose();
            RenderUtils.translateAndRotateMatrixForBone(poseStack, bone);
            poseStack.scale(0.5f, 0.5f, 0.5f);
            Minecraft.getInstance().getItemRenderer().renderStatic(itemstack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND,
                packedLight, packedOverlay, poseStack, bufferSource, animatable.level(), 0);
            poseStack.popPose();
        }
        super.renderForBone(poseStack, animatable, bone, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
    }
}
