package ru.blatfan.desertsouls.common.entity.pet;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import ru.blatfan.desertsouls.DesertSouls;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class AbstractGinModel<T extends AbstractGinEntity> extends GeoModel<T> {
    private final String model, texture, anim;
    
    public AbstractGinModel(String model, String texture, String anim) {
        this.model = model;
        this.texture = texture;
        this.anim = anim;
    }
    
    @Override
    public ResourceLocation getModelResource(T gin) {
        return DesertSouls.loc("geo/entity/"+model+".geo.json");
    }
    
    @Override
    public ResourceLocation getTextureResource(T gin) {
        return DesertSouls.loc("textures/entity/"+texture+".png");
    }
    
    @Override
    public ResourceLocation getAnimationResource(T gin) {
        return null;//DesertSouls.loc("animation/entity/"+anim+".animation.json");
    }
    
    @Override
    public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        
        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}