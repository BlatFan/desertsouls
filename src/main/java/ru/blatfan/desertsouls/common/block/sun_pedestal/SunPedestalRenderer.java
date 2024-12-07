package ru.blatfan.desertsouls.common.block.sun_pedestal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import ru.blatfan.desertsouls.utils.BlockRendererUtils;

import java.util.Arrays;

public class SunPedestalRenderer implements BlockEntityRenderer<SunPedestalEntity> {
    public SunPedestalRenderer(BlockEntityRendererProvider.Context context){}
    @Override
    public void render(SunPedestalEntity sunPedestalEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        ItemStack stack = sunPedestalEntity.getItemHandler().getItem(0);
        if (!stack.isEmpty())
            BlockRendererUtils.renderItem(sunPedestalEntity, 0.5d, 1.3d, 0.5d,
                Arrays.asList(0.95f*1.5f, 0.75f*1.5f),
                poseStack, multiBufferSource, i, i1,
                stack
            );
    }
}
