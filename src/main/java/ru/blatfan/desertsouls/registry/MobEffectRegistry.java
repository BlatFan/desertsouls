package ru.blatfan.desertsouls.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.desertsouls.DesertSouls;
import ru.blatfan.desertsouls.common.GinsEffect;
import ru.blatfan.desertsouls.utils.ColorHelper;

import java.awt.*;

public class MobEffectRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DesertSouls.MODID);
    
    public static final RegistryObject<MobEffect> GINS_BLESSING = EFFECTS.register("gins_blessing", () -> (new GinsEffect(MobEffectCategory.BENEFICIAL, new Color(79, 202, 228)))
        .addAttributeModifier(Attributes.ATTACK_DAMAGE, "56ace107-1537-417b-9308-e01ecd738ebf", 0.1, AttributeModifier.Operation.ADDITION)
        .addAttributeModifier(Attributes.MOVEMENT_SPEED, "aa5aa0cb-94fe-4bb5-bdff-b43a814a910c", 0.02, AttributeModifier.Operation.ADDITION)
    );
    public static final RegistryObject<MobEffect> GINS_CURSE = EFFECTS.register("gins_curse", () -> (new GinsEffect(MobEffectCategory.HARMFUL, new Color(196, 20, 17)))
        .addAttributeModifier(Attributes.ATTACK_DAMAGE, "8055c4c1-03f9-43d2-ba94-1092a2200d86", -0.1, AttributeModifier.Operation.ADDITION)
        .addAttributeModifier(Attributes.MOVEMENT_SPEED, "3b5a8116-2fdb-4654-9467-a1e769d6e9c6", -0.02, AttributeModifier.Operation.ADDITION)
    );
}
