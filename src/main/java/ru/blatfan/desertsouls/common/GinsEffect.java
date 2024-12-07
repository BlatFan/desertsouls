package ru.blatfan.desertsouls.common;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import ru.blatfan.desertsouls.utils.ColorHelper;

import java.awt.*;

public class GinsEffect extends MobEffect {
    public GinsEffect(MobEffectCategory pCategory, Color pColor) {
        super(pCategory, ColorHelper.getColor(pColor));
    }
}
