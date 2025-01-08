package com.kryptography.newworld.common.worldgen.tree;

import com.kryptography.newworld.init.worldgen.features.NWConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class FirTreeGrower extends AbstractTreeGrower {
    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
        return p_222911_ ? NWConfiguredFeatures.FIR_BEES_002 : NWConfiguredFeatures.FIR;
    }
}
