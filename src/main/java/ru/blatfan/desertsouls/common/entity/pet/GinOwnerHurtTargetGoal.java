package ru.blatfan.desertsouls.common.entity.pet;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import org.jetbrains.annotations.Nullable;

public class GinOwnerHurtTargetGoal extends OwnerHurtTargetGoal {
    public GinOwnerHurtTargetGoal(TamableAnimal pTameAnimal) {
        super(pTameAnimal);
    }
    
    private static boolean notOwner(LivingEntity owner, LivingEntity entity){
        if(entity instanceof TamableAnimal tamableAnimal && tamableAnimal.getOwner()!=null)
            return tamableAnimal.getOwner().getUUID()!=owner.getUUID();
        return true;
    }
    
    @Override
    protected boolean canAttack(@Nullable LivingEntity pPotentialTarget, TargetingConditions pTargetPredicate) {
        return super.canAttack(pPotentialTarget, pTargetPredicate) && notOwner(tameAnimal.getOwner(), pPotentialTarget);
    }
}
