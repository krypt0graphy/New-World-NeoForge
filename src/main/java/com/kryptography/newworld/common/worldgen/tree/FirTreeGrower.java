package com.kryptography.newworld.common.worldgen.tree;

import com.kryptography.newworld.init.worldgen.features.NWConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class FirTreeGrower {

    public static final TreeGrower FIR = new TreeGrower(
            "fir",
            Optional.empty(),
            Optional.of(NWConfiguredFeatures.FIR),
            Optional.of(NWConfiguredFeatures.FIR_BEES_002));
}
