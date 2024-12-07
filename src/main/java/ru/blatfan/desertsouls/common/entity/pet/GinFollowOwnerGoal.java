package ru.blatfan.desertsouls.common.entity.pet;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;

public class GinFollowOwnerGoal extends FollowOwnerGoal {
    public GinFollowOwnerGoal(TamableAnimal pTamable, double pSpeedModifier, float pStartDistance, float pStopDistance, boolean pCanFly) {
        super(pTamable, pSpeedModifier, pStartDistance, pStopDistance, pCanFly);
    }
    
    @Override
    public boolean canUse() {
        return super.canUse();// && !tamable.isOrderedToSit();
    }
}
