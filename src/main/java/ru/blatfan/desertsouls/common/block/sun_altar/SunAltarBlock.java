package ru.blatfan.desertsouls.common.block.sun_altar;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import ru.blatfan.desertsouls.utils.StackHelper;

public class SunAltarBlock extends BaseEntityBlock {
    public SunAltarBlock() {
        super(Properties.copy(Blocks.SANDSTONE));
    }
    
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(level.isClientSide){
            verifyStructure(level, pos);
            return InteractionResult.SUCCESS;
        }
        
        if (level.getBlockEntity(pos) instanceof SunAltarEntity table) {
            var inventory = table.getItemHandler();
            var input = inventory.getItem(0);
            var held = player.getItemInHand(hand);
            table.getItemHandler().setChanged();
            
            if (player.isCrouching() && !table.isActivated()) {
                table.setActivated();
            }
            else if (input.isEmpty() && !held.isEmpty()) {
                player.awardStat(Stats.ITEM_USED.get(held.getItem()));
                inventory.setItem(0, StackHelper.withSize(held, 1, false));
                player.setItemInHand(hand, StackHelper.shrink(held, 1, false));
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
            } else {
                player.awardStat(Stats.ITEM_USED.get(held.getItem()));
                inventory.setItem(0, ItemStack.EMPTY);
                var item = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), input);
                item.setNoPickUpDelay();
                level.addFreshEntity(item);
            }
        }
        
        return InteractionResult.SUCCESS;
    }
    
    public static boolean verifyStructure(Level level, BlockPos blockPos) {
        //Multiblock multiblock = ModMultiblocks.SUN_ALTAR;
        //boolean valid = multiblock.validate(level, blockPos.below(1), Rotation.NONE);
        //
        //if (!valid && level.isClientSide)
        //    ModonomiconHelper.renderMultiblock(ModMultiblocks.SUN_ALTAR, ModMultiblocks.SUN_ALTAR_TEXT, blockPos.below(1), Rotation.NONE);
        //
        //return valid;
        return true;
    }
    
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return ((level, blockPos, blockState, t) -> ((SunAltarEntity)t).tick(level, blockPos));
    }
    
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SunAltarEntity(blockPos, blockState);
    }
}
