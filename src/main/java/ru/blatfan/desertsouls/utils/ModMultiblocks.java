package ru.blatfan.desertsouls.utils;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.multiblock.Multiblock;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.blatfan.desertsouls.DesertSouls;

public class ModMultiblocks {
    public static final Component  SUN_ALTAR_TEXT = Component.translatable("block.desertsouls.sun_altar");
    public static final Multiblock SUN_ALTAR = ModonomiconAPI.get().getMultiblock(DesertSouls.loc("sun_altar"));
}
