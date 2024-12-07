package ru.blatfan.desertsouls.common.block.sun_pedestal;

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

public class SunPedestalBlock extends BaseEntityBlock {
    public SunPedestalBlock() {
        super(Properties.copy(Blocks.SANDSTONE));
    }
    
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(level.isClientSide)
            return InteractionResult.SUCCESS;
        
        if (level.getBlockEntity(pos) instanceof SunPedestalEntity table) {
            var inventory = table.getItemHandler();
            var input = inventory.getItem(0);
            var held = player.getItemInHand(hand);
            table.getItemHandler().setChanged();
            if (input.isEmpty() && !held.isEmpty()) {
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
    
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SunPedestalEntity(blockPos, blockState);
    }
}
