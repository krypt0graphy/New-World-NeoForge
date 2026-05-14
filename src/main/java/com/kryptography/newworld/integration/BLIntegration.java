package com.kryptography.newworld.integration;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.registry.NWBlocks;
import com.kryptography.newworld.core.registry.NWItems;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.data.server.BoatloadRecipeProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class BLIntegration {
	public static final BoatloadBoatType FIR = BoatloadBoatType.register(BoatloadBoatType.create(
			NewWorld.id("fir"),
			() -> NWBlocks.FIR_PLANKS.get().asItem(),
			() -> NWItems.FIR_BOAT.getFirst().asItem(),
			() -> NWItems.FIR_BOAT.getSecond().asItem(),
			() -> NWItems.FIR_FURNACE_BOAT.get(),
			() -> NWItems.LARGE_FIR_BOAT.get()
	));

	public static final Supplier<Item> FIR_FURNACE_BOAT = () -> new FurnaceBoatItem(FIR);
	public static final Supplier<Item> LARGE_FIR_BOAT = () -> new LargeBoatItem(FIR);

	public static void blRecipes(RecipeOutput output) {
		BoatloadRecipeProvider.furnaceBoatBuilder(NWItems.FIR_FURNACE_BOAT, NWItems.FIR_BOAT.getFirst()).save(output);
		BoatloadRecipeProvider.largeBoatBuilder(NWItems.LARGE_FIR_BOAT, NWItems.FIR_BOAT.getFirst(), NWBlocks.FIR_PLANKS.asItem()).save(output);
	}
}