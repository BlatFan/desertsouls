package ru.blatfan.desertsouls.utils;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class BaseItemStackHandler extends ItemStackHandler {
    private final Runnable onContentsChanged;
    private final Map<Integer, Integer> slotSizeMap;
    private BiFunction<Integer, ItemStack, Boolean> canInsert = null;
    private Function<Integer, Boolean> canExtract = null;
    private int maxStackSize = 64;
    private int[] outputSlots = null;
    
    protected BaseItemStackHandler(int size, Runnable onContentsChanged) {
        super(size);
        this.onContentsChanged = onContentsChanged;
        this.slotSizeMap = new HashMap<>();
    }
    
    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return this.insertItem(slot, stack, simulate, false);
    }
    
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate, boolean container) {
        if (!container && this.outputSlots != null && ArrayUtils.contains(this.outputSlots, slot))
            return stack;
        
        return super.insertItem(slot, stack, simulate);
    }
    
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return this.extractItem(slot, amount, simulate, false);
    }
    
    public ItemStack extractItem(int slot, int amount, boolean simulate, boolean container) {
        if (!container) {
            if (this.canExtract != null && !this.canExtract.apply(slot))
                return ItemStack.EMPTY;
            
            if (this.outputSlots != null && !ArrayUtils.contains(this.outputSlots, slot))
                return ItemStack.EMPTY;
        }
        
        return super.extractItem(slot, amount, simulate);
    }
    
    @Override
    public int getSlotLimit(int slot) {
        return this.slotSizeMap.containsKey(slot) ? this.slotSizeMap.get(slot) : this.maxStackSize;
    }
    
    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return this.canInsert == null || this.canInsert.apply(slot, stack);
    }
}
