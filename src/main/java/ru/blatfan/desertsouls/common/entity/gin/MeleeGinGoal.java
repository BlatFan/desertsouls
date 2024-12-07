package ru.blatfan.desertsouls.common.entity.gin;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.item.SwordItem;
import ru.blatfan.desertsouls.common.entity.pet.AbstractGinEntity;

public class MeleeGinGoal extends MeleeAttackGoal {
    public MeleeGinGoal(AbstractGinEntity pMob) {
        super(pMob, 1.5, true);
    }
    
    public AbstractGinEntity getGin() {
        return (AbstractGinEntity) mob;
    }
    
    @Override
    public boolean canUse() {
        return super.canUse() && getGin().getHand().getItem() instanceof SwordItem;
    }
}