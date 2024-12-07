package ru.blatfan.desertsouls.registry;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.desertsouls.DesertSouls;
import ru.blatfan.desertsouls.common.entity.gin.blue_gin.BlueGin;
import ru.blatfan.desertsouls.common.entity.gin.red_gin.RedGin;
import ru.blatfan.desertsouls.common.entity.spell.SpellBallEntity;

public class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ETYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DesertSouls.MODID);
    
    public static final RegistryObject<EntityType<SpellBallEntity>> SPELL_BALL = ETYPES.register("spell_ball", () ->
        addEntity("spell_ball", MobCategory.MISC, 1f, 1f, 1, (w, level) -> new SpellBallEntity(level)));
    
    public static final RegistryObject<EntityType<BlueGin>> BLUE_GIN = ETYPES.register("blue_gin", () ->
        addEntity("blue_gin", MobCategory.MISC, 0.5f, 1.25f, 1, (w, level) -> new BlueGin(level)));
    
    public static final RegistryObject<EntityType<RedGin>> RED_GIN = ETYPES.register("red_gin", () ->
        addEntity("red_gin", MobCategory.MISC, 0.5f, 1.25f, 1, (w, level) -> new RedGin(level)));
    
    
    private static <T extends Entity> EntityType<T> addEntity(String name, MobCategory category, float width, float height, int trackingRange, EntityType.EntityFactory<T> factory) {
        return EntityType.Builder.of(factory, category)
            .setTrackingRange(trackingRange)
            .setUpdateInterval(1)
            .sized(width, height)
            .build(DesertSouls.MODID + ":" + name);
        
    }
}
