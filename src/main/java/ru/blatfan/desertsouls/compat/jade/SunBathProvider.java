package ru.blatfan.desertsouls.compat.jade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.blatfan.desertsouls.common.block.sun_bath.SunBathEntity;
import ru.blatfan.desertsouls.utils.ColorHelper;
import ru.blatfan.desertsouls.utils.ModColors;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import java.text.DecimalFormat;

public class SunBathProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    public static final SunBathProvider INSTANCE = new SunBathProvider();
    
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig pluginConfig) {
        if (accessor.getServerData().contains("SE") && accessor.getServerData().contains("WORK") && pluginConfig.get(DesertJadePlugin.SUN_ENERGY)){
            tooltip.add(
                Component.translatable("jade.desertsouls.sun_energy"
                ).withStyle(style -> style.withColor(ColorHelper.getColor(ModColors.DESERT))).append(": ").append(formated(accessor.getServerData().getInt("SE")))
            );
            tooltip.add(
                Component.translatable("jade.desertsouls.work"
                ).withStyle(style -> style.withColor(ColorHelper.getColor(ModColors.DESERT))).append(": ").append(String.valueOf(accessor.getServerData().getBoolean("WORK")))
            );
        }
    }
    
    private static String formated(int energy){
        DecimalFormat df = new DecimalFormat("###,###.##");
        return energy==Integer.MAX_VALUE ? "infinity" : df.format(energy);
    }
    
    @Override
    public ResourceLocation getUid() {
        return DesertJadePlugin.SUN_ENERGY;
    }
    
    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        SunBathEntity bathEntity = (SunBathEntity) blockAccessor.getBlockEntity();
        compoundTag.putInt("SE", bathEntity.getSunEnergy());
        compoundTag.putBoolean("WORK", bathEntity.getLevel().isDay());
    }
}
