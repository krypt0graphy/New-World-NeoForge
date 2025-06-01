package com.kryptography.newworld.integration;

import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import vectorwing.blockbox.common.block.PalisadeBlock;
import vectorwing.blockbox.common.block.SeatBlock;
import vectorwing.blockbox.common.block.SpikedPalisadeBlock;
import vectorwing.blockbox.common.registry.ModBlocks;
import vectorwing.blockbox.data.recipe.CraftingRecipes;

import static com.kryptography.newworld.init.NWBlocks.*;

public class BBIntegration {
	public static final DeferredBlock<Block> STRIPPED_SPIKED_FIR_PALISADE = NWBlocks.register("stripped_spiked_fir_palisade", () -> new SpikedPalisadeBlock(ModBlocks.PROPERTIES_PALISADE.mapColor(MapColor.WOOD).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> SPIKED_FIR_PALISADE = NWBlocks.register("spiked_fir_palisade", () -> new SpikedPalisadeBlock(STRIPPED_SPIKED_FIR_PALISADE, ModBlocks.PROPERTIES_PALISADE.mapColor(MapColor.WOOD).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> STRIPPED_FIR_PALISADE = NWBlocks.register("stripped_fir_palisade", () -> new PalisadeBlock(STRIPPED_SPIKED_FIR_PALISADE, ModBlocks.PROPERTIES_PALISADE.mapColor(MapColor.WOOD).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> FIR_PALISADE = NWBlocks.register("fir_palisade", () -> new PalisadeBlock(SPIKED_FIR_PALISADE, STRIPPED_FIR_PALISADE, ModBlocks.PROPERTIES_PALISADE.mapColor(MapColor.WOOD).sound(SoundType.WOOD)));

	public static final DeferredBlock<Block> FIR_SEAT = NWBlocks.register("fir_seat", () -> new SeatBlock(BlockBehaviour.Properties.ofFullCopy(NWBlocks.FIR_PLANKS.get())));

	public static void register() {
	}

	public static void bbRecipes(RecipeOutput output) {
		CraftingRecipes.palisade(output, FIR_PALISADE, SPIKED_FIR_PALISADE, FIR_LOG);
		CraftingRecipes.palisade(output, STRIPPED_FIR_PALISADE, STRIPPED_SPIKED_FIR_PALISADE, STRIPPED_FIR_LOG);
		CraftingRecipes.chair(output, FIR_SEAT, FIR_PLANKS);
	}
}