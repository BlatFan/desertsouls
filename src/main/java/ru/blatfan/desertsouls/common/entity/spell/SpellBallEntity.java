package ru.blatfan.desertsouls.common.entity.spell;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import ru.blatfan.desertsouls.registry.EntityTypeRegistry;

import java.util.UUID;

public class SpellBallEntity extends ThrowableItemProjectile {
    public Vec3 shooterPos;
    
    public SpellBallEntity(Level level) {
        super(EntityTypeRegistry.SPELL_BALL.get(), level);
    }
    
    @Override
    public void shootFromRotation(Entity shooter, float rotationPitch, float rotationYaw, float pitchOffset, float velocity, float innacuracy) {
        float f = -Mth.sin(rotationYaw * ((float)Math.PI / 180F)) * Mth.cos(rotationPitch * ((float)Math.PI / 180F));
        float f1 = -Mth.sin((rotationPitch + pitchOffset) * ((float)Math.PI / 180F));
        float f2 = Mth.cos(rotationYaw * ((float)Math.PI / 180F)) * Mth.cos(rotationPitch * ((float)Math.PI / 180F));
        this.shoot(f, f1, f2, velocity, innacuracy);
        this.shooterPos = shooter.position();
        Vec3 vec3 = shooter.getDeltaMovement();
        this.setDeltaMovement(this.getDeltaMovement().add(vec3.x, 0, vec3.z));
    }
    
    @Override
    protected void onHitBlock(BlockHitResult result) {
        if(!level().isClientSide){
            level().explode(this, getX(), getY(), getZ(), 5, Level.ExplosionInteraction.NONE);
            remove(RemovalReason.DISCARDED);
        }
    }
    
    @Override
    protected void onHitEntity(EntityHitResult result) {
        if(!level().isClientSide){
            level().explode(this, getX(), getY(), getZ(), 5, Level.ExplosionInteraction.NONE);
            remove(RemovalReason.DISCARDED);
        }
    }
    
    @Override
    public void tick() {
        super.tick();
        if(this.level()==null) return;
        
        if (this.level().isClientSide) return;
        
        if (this.getXRot() == 0.0F && this.getYRot() == 0.0F) {
            Vec3 vector3d = getDeltaMovement();
            setYBodyRot((float) (Mth.atan2(vector3d.x, vector3d.z) * (double) (180F / (float) Math.PI)));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }
    }
    
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    
    
    @Override
    protected Item getDefaultItem() {
        return Items.AIR;
    }
    
    @Override
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }
    
    @Override
    public boolean isNoGravity() {
        return false;
    }
    
    @Override
    public boolean fireImmune() {
        return true;
    }
    
    @Override
    public boolean ignoreExplosion() {
        return true;
    }
}
