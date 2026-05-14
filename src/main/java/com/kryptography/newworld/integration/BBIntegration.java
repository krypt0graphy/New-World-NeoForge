package com.kryptography.newworld.integration;

import com.kryptography.newworld.core.registry.NWBlocks;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import vectorwing.blockbox.common.block.PalisadeBlock;
import vectorwing.blockbox.common.block.SeatBlock;
import vectorwing.blockbox.common.block.SpikedPalisadeBlock;
import vectorwing.blockbox.common.registry.ModBlocks;
import vectorwing.blockbox.data.recipe.CraftingRecipes;

import java.util.function.Supplier;

import static com.kryptography.newworld.core.registry.NWBlocks.*;

public class BBIntegration {
	public static final Supplier<Block> STRIPPED_SPIKED_FIR_PALISADE = () -> new SpikedPalisadeBlock(ModBlocks.PROPERTIES_PALISADE.mapColor(MapColor.WOOD).sound(SoundType.WOOD));
	public static final Supplier<Block> SPIKED_FIR_PALISADE = () -> new SpikedPalisadeBlock(NWBlocks.STRIPPED_SPIKED_FIR_PALISADE, ModBlocks.PROPERTIES_PALISADE.mapColor(MapColor.WOOD).sound(SoundType.WOOD));
	public static final Supplier<Block> STRIPPED_FIR_PALISADE = () -> new PalisadeBlock(NWBlocks.STRIPPED_SPIKED_FIR_PALISADE, ModBlocks.PROPERTIES_PALISADE.mapColor(MapColor.WOOD).sound(SoundType.WOOD));
	public static final Supplier<Block> FIR_PALISADE = () -> new PalisadeBlock(NWBlocks.SPIKED_FIR_PALISADE, NWBlocks.STRIPPED_FIR_PALISADE, ModBlocks.PROPERTIES_PALISADE.mapColor(MapColor.WOOD).sound(SoundType.WOOD));

	public static final Supplier<Block> FIR_SEAT = () -> new SeatBlock(BlockBehaviour.Properties.ofFullCopy(NWBlocks.FIR_PLANKS.get()));

	public static void register() {
	}

	public static void bbRecipes(RecipeOutput output) {
		CraftingRecipes.palisade(output, NWBlocks.FIR_PALISADE, NWBlocks.SPIKED_FIR_PALISADE, FIR_LOG);
		CraftingRecipes.palisade(output, NWBlocks.STRIPPED_FIR_PALISADE, NWBlocks.STRIPPED_SPIKED_FIR_PALISADE, STRIPPED_FIR_LOG);
		CraftingRecipes.chair(output, NWBlocks.FIR_SEAT, FIR_PLANKS);
	}
}
