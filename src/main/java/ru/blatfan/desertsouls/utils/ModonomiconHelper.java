package ru.blatfan.desertsouls.utils;

import com.klikli_dev.modonomicon.api.multiblock.*;
import com.klikli_dev.modonomicon.client.render.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Rotation;
import org.jetbrains.annotations.*;

public class ModonomiconHelper {
	public static void renderMultiblock(Multiblock multiblock, Component text, BlockPos pos, Rotation rotation) {
		MultiblockPreviewRenderer.setMultiblock(multiblock, text, false);
		MultiblockPreviewRenderer.anchorTo(pos, rotation);
	}
	
	public static void clearRenderedMultiblock(@Nullable Multiblock multiblock) {
		Multiblock currentlyRenderedMultiblock = MultiblockPreviewRenderer.getMultiblock();
		if (currentlyRenderedMultiblock == null || currentlyRenderedMultiblock != multiblock) {
			return;
		}
		MultiblockPreviewRenderer.setMultiblock(null, Component.empty(), false);
	}
	
}
