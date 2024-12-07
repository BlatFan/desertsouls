package ru.blatfan.desertsouls.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.desertsouls.DesertSouls;
import ru.blatfan.desertsouls.common.item.base.BaseDesertItem;

import java.util.function.Supplier;

public class ItemTabRegistry {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DesertSouls.MODID);
    
    public static final RegistryObject<CreativeModeTab> TAB = TABS.register(DesertSouls.MODID+".tab", () -> CreativeModeTab.builder()
        .title(Component.translatable("title.desertsouls"))
        .icon(() -> new ItemStack(Blocks.SANDSTONE))
        .displayItems((itemDisplayParameters, output) -> {
            output.accept(BlockRegistry.SUN_ALTAR.get());
            output.accept(BlockRegistry.SUN_PEDESTAL.get());
            output.accept(BlockRegistry.SUN_BATH.get());
        })
        .build());
}
