package ru.blatfan.desertsouls.common.item.base;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.maxbogomol.fluffy_fur.client.event.ClientTickHandler;
import mod.maxbogomol.fluffy_fur.client.render.RenderBuilder;
import mod.maxbogomol.fluffy_fur.common.item.IGuiParticleItem;
import mod.maxbogomol.fluffy_fur.common.item.IParticleItem;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import ru.blatfan.desertsouls.utils.ParticleEffects;

import java.awt.*;

public interface ParticleItem extends IGuiParticleItem, IParticleItem {
    Color getColor();
    
    @Override
    default void renderParticle(PoseStack poseStack, LivingEntity livingEntity, Level level, ItemStack itemStack, int i, int i1, int i2, int i3) {
        poseStack.popPose();
        poseStack.translate(i+8, i1+8, 0);
        guiParticles(poseStack, itemStack, i3);
    }
    
    default void guiParticles(PoseStack poseStack, ItemStack itemStack, int seed) {
        RenderBuilder.create().replaceBufferSource(Minecraft.getInstance().renderBuffers().bufferSource()).setRenderType(FluffyFurRenderTypes.ADDITIVE)
            .setColor(getColor()).setAlpha(0.5f)
            .renderDragon(poseStack, 0.05, 0.25, 0.0725, 0.5f, ClientTickHandler.partialTicks, seed)
            .endBatch();
        poseStack.popPose();
    }
    
    @Override
    default void addParticles(Level level, ItemEntity itemEntity) {
        worldParticle(itemEntity.getX(), itemEntity.getY(), itemEntity.getZ());
    }
    
    default void worldParticle(double x, double y, double z){
        ParticleEffects.spawnStar(x, y, z, getColor());
        ParticleEffects.spawnSparkle(x, y, z, getColor());
    }
}
