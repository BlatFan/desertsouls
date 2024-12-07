package ru.blatfan.desertsouls.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.desertsouls.DesertSouls;
import ru.blatfan.desertsouls.common.block.sun_altar.SunAltarEntity;
import ru.blatfan.desertsouls.common.block.sun_bath.SunBathEntity;
import ru.blatfan.desertsouls.common.block.sun_pedestal.SunPedestalEntity;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DesertSouls.MODID);
    
    public static final RegistryObject<BlockEntityType<SunAltarEntity>> SUN_ALTAR = BLOCK_ENTITIES.register("sun_altar_be", () ->
        BlockEntityType.Builder.of(SunAltarEntity::new, BlockRegistry.SUN_ALTAR.get()).build(null));
    public static final RegistryObject<BlockEntityType<SunPedestalEntity>> SUN_PEDESTAL = BLOCK_ENTITIES.register("sun_pedestal_be", () ->
        BlockEntityType.Builder.of(SunPedestalEntity::new, BlockRegistry.SUN_PEDESTAL.get()).build(null));
    public static final RegistryObject<BlockEntityType<SunBathEntity>> SUN_BATH = BLOCK_ENTITIES.register("sun_bath_be", () ->
        BlockEntityType.Builder.of(SunBathEntity::new, BlockRegistry.SUN_BATH.get()).build(null));
}
