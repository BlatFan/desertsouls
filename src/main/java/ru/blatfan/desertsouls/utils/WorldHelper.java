package ru.blatfan.desertsouls.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@SuppressWarnings("unused")
public class WorldHelper {
    public static void spawnItemOnEntity(LivingEntity entity, ItemStack stack) {
        Level level = entity.level();
        ItemEntity itemEntity = new ItemEntity(level, entity.getX(), entity.getY() + 0.5, entity.getZ(), stack);
        itemEntity.setPickUpDelay(40);
        itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().multiply(0, 1, 0));
        level.addFreshEntity(itemEntity);
    }
    
    public static void spawnItemAtPos(Level level, BlockPos pos, ItemStack stack) {
        ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
        itemEntity.setPickUpDelay(10);
        itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().multiply(0.0, 1.0, 0.0));
        level.addFreshEntity(itemEntity);
    }
    
    public static String formatedTime(int ticks){
        int totalSecs = ticks /20;
        int hours = (int) totalSecs / 3600;
        int remainder = (int) totalSecs - hours * 3600;
        int minutes = remainder / 60;
        remainder = remainder - minutes * 60;
        int seconds = remainder;
        if(hours>0) return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return String.format("%02d:%02d", minutes, seconds);
    }
    public static String formatedTimeS(int totalSecs){
        int hours = (int) totalSecs / 3600;
        int remainder = (int) totalSecs - hours * 3600;
        int minutes = remainder / 60;
        remainder = remainder - minutes * 60;
        int seconds = remainder;
        if(hours>0) return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return String.format("%02d:%02d", minutes, seconds);
    }
}