package ru.blatfan.desertsouls.common.block.sun_pedestal;

import mod.maxbogomol.fluffy_fur.common.block.entity.BlockSimpleInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import ru.blatfan.desertsouls.registry.BlockEntityRegistry;
import ru.blatfan.desertsouls.utils.BlockRendererUtils;

public class SunPedestalEntity extends BlockSimpleInventory {
    public SunPedestalEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.SUN_PEDESTAL.get(), pos, state);
    }
    
    @Override
    protected SimpleContainer createItemHandler() {
        return new SimpleContainer(1){
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        };
    }
    
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == ForgeCapabilities.ITEM_HANDLER)
            return BlockRendererUtils.getLazyItems(this).cast();
        
        return super.getCapability(cap);
    }
}
