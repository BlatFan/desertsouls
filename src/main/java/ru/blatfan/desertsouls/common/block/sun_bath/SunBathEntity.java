package ru.blatfan.desertsouls.common.block.sun_bath;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import ru.blatfan.desertsouls.registry.BlockEntityRegistry;
import ru.blatfan.desertsouls.utils.ModColors;
import ru.blatfan.desertsouls.utils.ParticleEffects;

public class SunBathEntity extends BlockEntity {
    @Getter @Setter
    private int sunEnergy;
    private int tick = 0;
    public static final String SUN_TAG = "sun_energy";
    
    public SunBathEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.SUN_BATH.get(), pos, state);
    }
    
    public void tick(Level level, BlockPos blockPos) {
        if(level.isClientSide() && level.isDay()) {
            ParticleEffects.spawnStar(blockPos.getX() + 0.5, blockPos.getY() + 20, blockPos.getZ() + 0.5, 0, -1, 0, ModColors.DESERT);
        }
        if(!level.isClientSide() && level.isDay()) {
            tick++;
            if(tick==20){
                sunEnergy++;
                tick=0;
            }
        }
    }
    
    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt(SUN_TAG, getSunEnergy());
    }
    
    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        setSunEnergy(pTag.getInt(SUN_TAG));
    }
}
