package com.kryptography.newworld.integration;

import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class FDIntegration {

	public static final DeferredBlock<Block> FIR_CABINET = NWBlocks.register("fir_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)));

	public static void addBlockEntities(BlockEntityTypeAddBlocksEvent event) {
		event.modify(
				ModBlockEntityTypes.CABINET.get(),
				FIR_CABINET.get()
		);
	}

	public static void register() {
	}
}
