package ru.blatfan.desertsouls.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import ru.blatfan.desertsouls.DesertSouls;

public class WorldGen {
    public static ResourceKey<ConfiguredFeature<?, ?>> PALM = WorldGen.registerKey("palm");

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, DesertSouls.loc(name));
    }
}