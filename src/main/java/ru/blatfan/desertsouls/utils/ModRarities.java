package ru.blatfan.desertsouls.utils;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Rarity;

public class ModRarities {
    public static final Rarity DESERT = Rarity.create("desert", (style) -> style.withColor(TextColor.fromRgb(ModColors.DESERT.getRGB())));
    public static final Rarity ARTIFACT = Rarity.create("artifact", (style) -> style.withColor(TextColor.fromRgb(ModColors.ARTIFACT.getRGB())));
}
