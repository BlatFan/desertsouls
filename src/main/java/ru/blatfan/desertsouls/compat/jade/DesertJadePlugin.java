package ru.blatfan.desertsouls.compat.jade;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.blatfan.desertsouls.DesertSouls;
import ru.blatfan.desertsouls.common.block.sun_bath.SunBathBlock;
import ru.blatfan.desertsouls.common.block.sun_bath.SunBathEntity;
import snownee.jade.api.*;

@WailaPlugin
public class DesertJadePlugin implements IWailaPlugin {
    public static final ResourceLocation SUN_ENERGY = DesertSouls.loc("sun");
    
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(SunBathProvider.INSTANCE, SunBathEntity.class);
    }
    
    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerClient(IWailaClientRegistration registration) {
        registration.addConfig(SUN_ENERGY, true);
        
        registration.registerBlockComponent(SunBathProvider.INSTANCE, SunBathBlock.class);
    }
}