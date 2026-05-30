package com.kryptography.newworld.common.worldgen.tree;

import com.kryptography.newworld.core.registry.datapack.NWFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class FirTreeGrower {

	public static final TreeGrower FIR = new TreeGrower(
			"fir",
			Optional.empty(),
			Optional.of(NWFeatures.NWConfiguredFeatures.FIR),
			Optional.of(NWFeatures.NWConfiguredFeatures.FIR_BEES_002));
}
