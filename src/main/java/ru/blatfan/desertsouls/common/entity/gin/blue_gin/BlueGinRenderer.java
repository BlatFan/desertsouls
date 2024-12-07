package ru.blatfan.desertsouls.common.entity.gin.blue_gin;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LightLayer;
import ru.blatfan.desertsouls.common.entity.pet.AbstractGinModel;
import ru.blatfan.desertsouls.common.entity.pet.GinHandRenderLayer;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import toni.sodiumdynamiclights.SodiumDynamicLights;

public class BlueGinRenderer extends GeoEntityRenderer<BlueGin> {
    public BlueGinRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AbstractGinModel<>("blue_gin", "blue_gin", "blue_gin"));
        addRenderLayer(new GinHandRenderLayer<>(this));
    }
    
    @Override
    public boolean shouldRender(BlueGin pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return true;
    }
    
    @Override
    protected int getBlockLightLevel (BlueGin pEntity, BlockPos pPos){
        int vanilla = super.getBlockLightLevel(pEntity, pPos);
        int posLuminance = (int) SodiumDynamicLights.get().getDynamicLightLevel(pPos);
        
        if (pEntity.level().getBrightness(LightLayer.BLOCK, pPos) > 8 || !pEntity.level().isClientSide && pEntity.isTame())
            return vanilla;
        
        return Math.max(Math.max(vanilla, 12), posLuminance);
    }
}