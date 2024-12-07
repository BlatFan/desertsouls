package ru.blatfan.desertsouls.common.entity.pet;

import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class GinRangedGoal extends RangedAttackGoal {
    public GinRangedGoal(RangedAttackMob pRangedAttackMob) {
        super(pRangedAttackMob, 1, 20, 30);
    }
    
    public AbstractGinEntity getPet(){
        return (AbstractGinEntity) this.mob;
    }
    
    @Override
    public boolean canUse() {
        return super.canUse() && getPet().getHand().isEmpty();
    }
}