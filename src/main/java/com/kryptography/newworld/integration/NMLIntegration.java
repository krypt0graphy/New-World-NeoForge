package com.kryptography.newworld.integration;


import com.farcr.nomansland.common.block.TrimmedPlankBlock;
import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public class NMLIntegration {
	public static final DeferredBlock<Block> FIR_BOOKSHELF = NWBlocks.register("fir_bookshelf", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));

	public static final DeferredBlock<Block> TRIMMED_FIR_PLANKS = NWBlocks.register("trimmed_fir_planks", () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

	public static void register() {
	}
}