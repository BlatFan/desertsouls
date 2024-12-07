package ru.blatfan.desertsouls.common.block.sun_altar;

import lombok.Getter;
import mod.maxbogomol.fluffy_fur.common.block.entity.BlockSimpleInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import ru.blatfan.desertsouls.registry.BlockEntityRegistry;
import ru.blatfan.desertsouls.utils.BlockRendererUtils;
import ru.blatfan.desertsouls.utils.ModColors;
import ru.blatfan.desertsouls.utils.ParticleEffects;

@Getter
public class SunAltarEntity extends BlockSimpleInventory {
    private boolean activated = false;
    
    public SunAltarEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.SUN_ALTAR.get(), pos, state);
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
    
    public void setActivated() {
        activated=true;
    }
    
    public void tick(Level level, BlockPos blockPos) {
        if(level.isClientSide() && level.isDay() && isActivated()) {
            ParticleEffects.spawnStar(blockPos.offset(2, 2, -2), -0.1, -0.1, 0.1, ModColors.DESERT);
            ParticleEffects.spawnStar(blockPos.offset(2, 2, 2), -0.1, -0.1, -0.1, ModColors.DESERT);
            ParticleEffects.spawnStar(blockPos.offset(-2, 2, 2), 0.1, -0.1, -0.1, ModColors.DESERT);
            ParticleEffects.spawnStar(blockPos.offset(-2, 2, -2), 0.1, -0.1, 0.1, ModColors.DESERT);
        }
        if(!level.isClientSide() && level.isDay() && isActivated()) {
        
        }
    }
    
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == ForgeCapabilities.ITEM_HANDLER)
            return BlockRendererUtils.getLazyItems(this).cast();
        
        return super.getCapability(cap);
    }
}
