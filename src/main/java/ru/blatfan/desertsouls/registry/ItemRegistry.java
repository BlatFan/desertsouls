package ru.blatfan.desertsouls.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.desertsouls.DesertSouls;
import ru.blatfan.desertsouls.common.item.base.BaseDesertItem;

import java.util.function.Supplier;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DesertSouls.MODID);
    
    public static RegistryObject<Item> register(String id, Supplier<Item> item) {
        return ITEMS.register(id, item);
    }
    public static RegistryObject<Item> registerDesert(String id){
        return register(id, BaseDesertItem::new);
    }
}
