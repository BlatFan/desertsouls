package ru.blatfan.desertsouls.registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.desertsouls.DesertSouls;
import ru.blatfan.desertsouls.common.gui.gin.GinMenu;

public class MenuRegistry {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, DesertSouls.MODID);
    
    public static final RegistryObject<MenuType<GinMenu>> GIN_MENU = MENUS.register("gin_menu", () ->
        IForgeMenuType.create((windowId, inv, data) -> new GinMenu(windowId, null)));
}
