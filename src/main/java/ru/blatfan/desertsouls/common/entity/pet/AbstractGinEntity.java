package ru.blatfan.desertsouls.common.entity.pet;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import ru.blatfan.desertsouls.common.entity.gin.MeleeGinGoal;
import software.bernie.geckolib.animatable.GeoEntity;

public abstract class AbstractGinEntity extends TamableAnimal implements RangedAttackMob, FlyingAnimal, GeoEntity {
    @Setter @Getter
    private ItemStack hand = ItemStack.EMPTY;
    
    protected AbstractGinEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        setNoGravity(true);
    }
    
    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation nav = new FlyingPathNavigation(this, pLevel);
        nav.setCanOpenDoors(false);
        nav.setCanFloat(true);
        nav.setCanPassDoors(true);
        return nav;
    }
    
    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 100f)
            .add(Attributes.FLYING_SPEED, 0.8f)
            .add(Attributes.MOVEMENT_SPEED, 0.3f)
            .add(Attributes.ATTACK_DAMAGE, 5f)
            .add(Attributes.FOLLOW_RANGE, 16f)
            .build();
    }
    
    public boolean onClimbable() {
        return false;
    }
    
    @Override
    public boolean isFlying() {
        return !onGround();
    }
    
    @Override
    public boolean fireImmune() {
        return true;
    }
    
    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }
    
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeGinGoal(this));
        this.goalSelector.addGoal(2, new GinRangedGoal(this));
        this.goalSelector.addGoal(3, new GinFollowOwnerGoal(this, 1.0D, 3.0F, 1.5F, true));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new GinOwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new OwnerHurtByTargetGoal(this));
    }
    
    @Override
    public boolean save(CompoundTag pCompound) {
        CompoundTag item = hand.save(new CompoundTag());
        pCompound.put("hand", item);
        return super.save(pCompound);
    }
    
    @Override
    public void load(CompoundTag pCompound) {
        hand = ItemStack.of(pCompound.getCompound("hand"));
        super.load(pCompound);
    }
    
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }
    
    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
    }
    
    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }
    
    public abstract ThrowableItemProjectile getProjectile();
    
    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        if(getProjectile() == null) return;
        Projectile proj = ProjectileUtil.getMobArrow(this, new ItemStack(Items.ARROW), pVelocity);;
        proj.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, 1.5F + 0 * 0.125f, 0F);
        this.playSound(SoundEvents.ENDER_DRAGON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(proj);
    }
}
