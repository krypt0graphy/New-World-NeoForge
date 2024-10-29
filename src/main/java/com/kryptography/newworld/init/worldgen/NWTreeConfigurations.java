package com.kryptography.newworld.init.worldgen;

import com.google.common.collect.ImmutableList;
import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class NWTreeConfigurations {

    public static final TreeConfiguration GROWN_FIR = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(NWBlocks.FIR_LOG.get()),
            new StraightTrunkPlacer(6, 1, 2),
            BlockStateProvider.simple(NWBlocks.FIR_LEAVES.get()),
            new SpruceFoliagePlacer(
                    UniformInt.of(1, 3),
                    UniformInt.of(0,1 ),
                    UniformInt.of(3,4)),
            new TwoLayersFeatureSize(2, 0, 2))
            .ignoreVines()
            .build();

    public static final TreeConfiguration NATURAL_FIR =  new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(NWBlocks.FIR_LOG.get()),
            new StraightTrunkPlacer(6, 1, 2),
            BlockStateProvider.simple(NWBlocks.FIR_LEAVES.get()),
            new SpruceFoliagePlacer(
                    UniformInt.of(1, 3),
                    UniformInt.of(0,1 ),
                    UniformInt.of(3,4)),
            new TwoLayersFeatureSize(2, 0, 2))
            .ignoreVines()
            .decorators(ImmutableList.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL))))
            .build();
}
