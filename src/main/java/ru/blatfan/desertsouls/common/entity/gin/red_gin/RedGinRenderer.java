package ru.blatfan.desertsouls.common.entity.gin.red_gin;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import ru.blatfan.desertsouls.common.entity.pet.AbstractGinModel;
import ru.blatfan.desertsouls.common.entity.pet.GinHandRenderLayer;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RedGinRenderer extends GeoEntityRenderer<RedGin> {
    public RedGinRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AbstractGinModel<>("blue_gin", "red_gin", "blue_gin"));
        addRenderLayer(new GinHandRenderLayer<>(this));
    }
    
    @Override
    public boolean shouldRender(RedGin pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return true;
    }
}