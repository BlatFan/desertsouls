package ru.blatfan.desertsouls;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.blatfan.desertsouls.registry.*;

@Mod(DesertSouls.MODID)
public class DesertSouls {
    public static final String MODID = "desertsouls";
    private static final Logger LOGGER = LoggerFactory.getLogger("DesertSouls");
    
    public DesertSouls() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        
        BlockRegistry.BLOCKS.register(bus);
        ItemRegistry.ITEMS.register(bus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(bus);
        ItemTabRegistry.TABS.register(bus);
        MobEffectRegistry.EFFECTS.register(bus);
        EntityTypeRegistry.ETYPES.register(bus);
        MenuRegistry.MENUS.register(bus);
    }
    
    public static ResourceLocation loc(String s){
        return new ResourceLocation(MODID, s);
    }
}
