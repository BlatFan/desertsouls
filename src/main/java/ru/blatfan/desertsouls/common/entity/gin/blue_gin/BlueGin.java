package ru.blatfan.desertsouls.common.entity.gin.blue_gin;

import mod.maxbogomol.fluffy_fur.client.particle.ParticleBuilder;
import mod.maxbogomol.fluffy_fur.client.particle.data.ColorParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.GenericParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.LightParticleData;
import mod.maxbogomol.fluffy_fur.client.particle.data.SpinParticleData;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurParticles;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import ru.blatfan.desertsouls.common.entity.pet.*;
import ru.blatfan.desertsouls.common.entity.spell.SpellBallEntity;
import ru.blatfan.desertsouls.common.gui.gin.GinMenu;
import ru.blatfan.desertsouls.registry.EntityTypeRegistry;
import ru.blatfan.desertsouls.utils.StackHelper;
import ru.blatfan.desertsouls.utils.WorldHelper;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.awt.*;

public class BlueGin extends AbstractGinEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    
    public BlueGin(Level pLevel) {
        super(EntityTypeRegistry.BLUE_GIN.get(), pLevel);
    }
    
    @Override
    public void tick() {
        ParticleBuilder.create(FluffyFurParticles.SMOKE).setRenderType(FluffyFurRenderTypes.ADDITIVE_PARTICLE)
            .setHasPhysics(true)
            .setScaleData(GenericParticleData.create(0.1f, 0.05f).build())
            .setSpinData(SpinParticleData.create().randomSpin(0.5f).build())
            .setLightData(LightParticleData.GLOW)
            .setLifetime(10)
            .randomOffset(0.2)
            .setVelocity(0, -0.1, 0)
            .setColorData(ColorParticleData.create(new Color(79, 202, 228)).build())
            .spawn(level(), getX(), getY(), getZ());
    }
    
    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {
        if(level().isClientSide) return InteractionResult.CONSUME;
        
        if(!isTame() && getOwner()==null) {
            setTame(true);
            setOwnerUUID(pPlayer.getUUID());
            return InteractionResult.CONSUME;
        }
        if(isTame() && getOwnerUUID()==pPlayer.getUUID()){
            if(getHand().isEmpty() && !pPlayer.getMainHandItem().isEmpty()){
                setHand(StackHelper.withSize(pPlayer.getMainHandItem(), 1, false));
                pPlayer.setItemSlot(EquipmentSlot.MAINHAND, StackHelper.shrink(pPlayer.getMainHandItem(), 1, false));
            } else if(!getHand().isEmpty()) {
                WorldHelper.spawnItemOnEntity(pPlayer, getHand());
                setHand(ItemStack.EMPTY);
            }
            pPlayer.openMenu(getMP(this));
        }
        
        return InteractionResult.CONSUME;
    }
    private static MenuProvider getMP(BlueGin gin){
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("");
            }
            
            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                return new GinMenu(i, gin);
            }
        };
    }
    
    @Override
    public ThrowableItemProjectile getProjectile() {
        return new SpellBallEntity(level());
    }
    
    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        WorldHelper.spawnItemOnEntity(this, getHand());
    }
    
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    
    }
    
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
