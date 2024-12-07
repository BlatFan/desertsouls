package ru.blatfan.desertsouls.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.desertsouls.DesertSouls;
import ru.blatfan.desertsouls.common.block.sun_altar.SunAltarBlock;
import ru.blatfan.desertsouls.common.block.sun_bath.SunBathBlock;
import ru.blatfan.desertsouls.common.block.sun_pedestal.SunPedestalBlock;
import ru.blatfan.desertsouls.common.item.base.ArtifactBlockItem;
import ru.blatfan.desertsouls.common.item.base.DesertBlockItem;

import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DesertSouls.MODID);
    
    public static final RegistryObject<Block> SUN_ALTAR = register("sun_altar", SunAltarBlock::new);
    public static final RegistryObject<Block> SUN_PEDESTAL = register("sun_pedestal", SunPedestalBlock::new);
    public static final RegistryObject<Block> SUN_BATH = register("sun_bath", SunBathBlock::new);
    
    public static RegistryObject<Block> register(String id, Supplier<Block> block){
        RegistryObject<Block> reg = BLOCKS.register(id, block);
        ItemRegistry.register(id, () -> new DesertBlockItem(reg.get(), new Item.Properties().stacksTo(64)));
        return reg;
    }
    public static RegistryObject<Block> registerArtifact(String id, Supplier<Block> block){
        RegistryObject<Block> reg = BLOCKS.register(id, block);
        ItemRegistry.register(id, () -> new ArtifactBlockItem(reg.get(), new Item.Properties().stacksTo(64)));
        return reg;
    }
}
