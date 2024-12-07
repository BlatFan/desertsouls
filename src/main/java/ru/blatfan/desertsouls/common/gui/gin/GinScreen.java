package ru.blatfan.desertsouls.common.gui.gin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import ru.blatfan.desertsouls.DesertSouls;
import ru.blatfan.desertsouls.common.entity.gin.blue_gin.BlueGin;
import ru.blatfan.desertsouls.utils.ColorHelper;

import java.awt.*;

public class GinScreen extends AbstractContainerScreen<GinMenu> {
    private final BlueGin gin;
    private static final ResourceLocation texture = DesertSouls.loc("/textures/gui/pet_gui.png");
    
    public GinScreen(GinMenu pMenu, Inventory inv, Component pTitle) {
        super(pMenu, inv, Component.literal(""));
        this.gin=pMenu.getGin();
    }
    
    @Override
    public void render(GuiGraphics gui, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(gui);
        renderLabel(gui);
        renderWithTooltip(gui, pMouseX, pMouseY, pPartialTick);
    }
    
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {}
    
    public void renderLabel(GuiGraphics gui) {
        gui.drawString(font, "Blue Gin", 15, 15, ColorHelper.getColor(Color.WHITE));
    }
    
    @Override
    public void renderBackground(GuiGraphics gui) {
        gui.blit(texture, 10, 10, 33, 0, 96, 31);
    }
}
