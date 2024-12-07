package ru.blatfan.desertsouls.common.item.base;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import ru.blatfan.desertsouls.utils.ModRarities;
import ru.blatfan.desertsouls.utils.ColorHelper;
import ru.blatfan.desertsouls.utils.ModColors;

public class DesertBlockItem extends BlockItem {
    public DesertBlockItem(Block block, Properties properties) {
        super(block, properties.rarity(ModRarities.DESERT));
    }
    
    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(style -> style.withColor(ColorHelper.getColor(ModColors.DESERT)));
    }
}
