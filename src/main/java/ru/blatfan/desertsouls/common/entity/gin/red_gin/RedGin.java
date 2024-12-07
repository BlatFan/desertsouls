package ru.blatfan.desertsouls.common.entity.gin.red_gin;

import mod.maxbogomol.fluffy_fur.client.particle.ParticleBuilder;
import mod.maxbogomol.fluffy_fur.client.particle.data.ColorParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.GenericParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.LightParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.SpinParticleData;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurParticles;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import ru.blatfan.desertsouls.common.entity.pet.AbstractGinEntity;
import ru.blatfan.desertsouls.registry.EntityTypeRegistry;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.awt.*;

public class RedGin extends AbstractGinEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    
    public RedGin(Level pLevel) {
        super(EntityTypeRegistry.RED_GIN.get(), pLevel);
    }
    
    @Override
    public void tick() {
        ParticleBuilder.create(FluffyFurParticles.SKULL).setRenderType(FluffyFurRenderTypes.ADDITIVE_PARTICLE)
            .setHasPhysics(true)
            .setScaleData(GenericParticleData.create(0.1f, 0.05f).build())
            .setSpinData(SpinParticleData.create().randomSpin(0.5f).build())
            .setLightData(LightParticleData.GLOW)
            .setLifetime(10)
            .randomOffset(0.2)
            .setVelocity(0, -0.1, 0)
            .setColorData(ColorParticleData.create(new Color(196, 20, 17)).build())
            .spawn(level(), getX(), getY(), getZ());
        setHand(new ItemStack(Items.NETHERITE_SWORD));
    }
    
    @Override
    public ThrowableItemProjectile getProjectile() {
        return null;
    }
    
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    
    }
    
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
