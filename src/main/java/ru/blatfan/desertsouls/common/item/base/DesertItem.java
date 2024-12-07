package ru.blatfan.desertsouls.common.item.base;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import ru.blatfan.desertsouls.utils.ModRarities;
import ru.blatfan.desertsouls.utils.ColorHelper;
import ru.blatfan.desertsouls.utils.ModColors;

public class DesertItem extends Item {
    public DesertItem(Properties properties) {
        super(properties.rarity(ModRarities.DESERT));
    }
    
    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(style -> style.withColor(ColorHelper.getColor(ModColors.DESERT)));
    }
}
