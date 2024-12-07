package ru.blatfan.desertsouls.common.gui.gin;

import lombok.Getter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import ru.blatfan.desertsouls.common.entity.gin.blue_gin.BlueGin;
import ru.blatfan.desertsouls.registry.MenuRegistry;

public class GinMenu extends AbstractContainerMenu {
    @Getter
    private final BlueGin gin;
    
    public GinMenu(int pContainerId, BlueGin gin) {
        super(MenuRegistry.GIN_MENU.get(), pContainerId);
        this.gin=gin;
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }
    
    @Override
    public boolean stillValid(Player player) {
        return gin.getOnPos().getCenter().distanceTo(player.getOnPos().getCenter())<6;
    }
}
