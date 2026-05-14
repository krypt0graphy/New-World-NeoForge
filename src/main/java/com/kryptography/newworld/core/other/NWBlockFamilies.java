package com.kryptography.newworld.core.other;

import net.minecraft.data.BlockFamily;

import static com.kryptography.newworld.core.registry.NWBlocks.*;

public class NWBlockFamilies {
	public static final BlockFamily FIR_PLANKS_FAMILY = new BlockFamily.Builder(FIR_PLANKS.get())
			.button(FIR_BUTTON.get())
			.door(FIR_DOOR.get())
			.fence(FIR_FENCE.get())
			.fenceGate(FIR_FENCE_GATE.get())
			.pressurePlate(FIR_PRESSURE_PLATE.get())
			.sign(FIR_SIGNS.getFirst().get(), FIR_SIGNS.getSecond().get())
			.slab(FIR_SLAB.get())
			.stairs(FIR_STAIRS.get())
			.trapdoor(FIR_TRAPDOOR.get())
			.recipeGroupPrefix("wooden")
			.recipeUnlockedBy("has_planks")
			.getFamily();
}
