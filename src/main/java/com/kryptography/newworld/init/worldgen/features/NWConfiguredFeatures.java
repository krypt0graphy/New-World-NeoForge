package com.kryptography.newworld.init.worldgen.features;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.worldgen.NWFeature;
import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class NWConfiguredFeatures {
    private static final BeehiveDecorator BEES_1 = new BeehiveDecorator(1F);
    private static final BeehiveDecorator BEES_002 = new BeehiveDecorator(0.02F);

    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR = registerKey("fir");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_BEES = registerKey("fir_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_BEES_002 = registerKey("fir_bees_002");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NATURAL_FIR = registerKey("natural_fir");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATURAL_FIR_BEES = registerKey("natural_fir_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATURAL_FIR_BEES_002 = registerKey("natural_fir_bees_002");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_MEADOW = registerKey("fir_meadow");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_SPAWN = registerKey("fir_spawn");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_TAIGA = registerKey("fir_taiga");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_FIR_LOG = registerKey("fallen_fir_log");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BURIAL_SITE = registerKey("burial_site");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOW_LICHEN_WOODED_MEADOW = registerKey("glow_lichen_wooded_meadow");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BERRY_WOODED_MEADOW = registerKey("patch_berry_wooded_meadow");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LUSH_CAVE_MUD_PATCH = registerKey("lush_cave_mud_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LOAM_PATCH_CEILING = registerKey("loam_patch_ceiling");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LOAM_ORE = registerKey("loam_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LOAM_SNOW = registerKey("loam_snow");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CALCITE_PATCH = registerKey("calcite_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CALCITE_VEGETATION = registerKey("calcite_vegetation");


    public static List<Block> VALID_BERRY_BUSH_BLOCKS = List.of(Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT, Blocks.MOSS_BLOCK);

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrap) {
        HolderGetter<ConfiguredFeature<?, ?>> features = bootstrap.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> placedFeatures = bootstrap.lookup(Registries.PLACED_FEATURE);




        register(bootstrap, FIR, Feature.TREE, grownFir().build());
        register(bootstrap, FIR_BEES, Feature.TREE, grownFir().decorators(List.of(BEES_1)).build());
        register(bootstrap, FIR_BEES_002, Feature.TREE, grownFir().decorators(List.of(BEES_002)).build());

        register(bootstrap, NATURAL_FIR, Feature.TREE, grownFir().decorators(List.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());
        register(bootstrap, NATURAL_FIR_BEES, Feature.TREE, grownFir().decorators(List.of(BEES_1, new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());
        register(bootstrap, NATURAL_FIR_BEES_002, Feature.TREE, grownFir().decorators(List.of(BEES_002, new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());

        register(bootstrap, FALLEN_FIR_LOG, NWFeature.FALLEN_FIR_LOG.get(), FeatureConfiguration.NONE);

        register(bootstrap, BURIAL_SITE, NWFeature.BURIAL_SITE.get(), FeatureConfiguration.NONE);

        register(bootstrap, FIR_SPAWN, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(NWPlacedFeatures.NATURAL_FIR_BEES_CHECKED), 0.06F), new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.SPRUCE_CHECKED), 0.1f)), placedFeatures.getOrThrow(NWPlacedFeatures.NATURAL_FIR_CHECKED)));
        register(bootstrap, FIR_MEADOW, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(NWPlacedFeatures.FIR_BEES_CHECKED), 1.0F)), placedFeatures.getOrThrow(NWPlacedFeatures.FIR_CHECKED) ));
        register(bootstrap, FIR_TAIGA, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(NWPlacedFeatures.FIR_BEES_CHECKED), 0.0F)), placedFeatures.getOrThrow(NWPlacedFeatures.FIR_CHECKED) ));

        register(bootstrap, PATCH_BERRY_WOODED_MEADOW, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SWEET_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 2))), VALID_BERRY_BUSH_BLOCKS, 60));
        register(bootstrap, GLOW_LICHEN_WOODED_MEADOW, Feature.MULTIFACE_GROWTH, new MultifaceGrowthConfiguration((MultifaceBlock) Blocks.GLOW_LICHEN, 20, true, true, true, 0.5f, HolderSet.direct(Block::builtInRegistryHolder, NWBlocks.FIR_LOG.get(), Blocks.MOSSY_COBBLESTONE)));

        register(bootstrap, LOAM_PATCH_CEILING, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(NWBlocks.LOAM.get()), PlacementUtils.inlinePlaced(features.getOrThrow(MiscOverworldFeatures.ICE_SPIKE)), CaveSurface.CEILING, UniformInt.of(1,2), 0.0F, 5, 0.08F, UniformInt.of(4, 7), 0.3F));
        register(bootstrap, LOAM_ORE, Feature.ORE, new OreConfiguration(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), NWBlocks.LOAM.get().defaultBlockState(), 64));
        register(bootstrap, LOAM_SNOW, NWFeature.LOAM_SNOW.get(), FeatureConfiguration.NONE);

        register(bootstrap, CALCITE_VEGETATION, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.AIR.defaultBlockState(), 16).build())));
        register(bootstrap, CALCITE_PATCH, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.CALCITE), PlacementUtils.inlinePlaced(features.getOrThrow(CALCITE_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.08F, UniformInt.of(4, 7), 0.3F));

        register(bootstrap, LUSH_CAVE_MUD_PATCH, Feature.ORE, new OreConfiguration(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.MUD.defaultBlockState(), 64) );


    }


    private static TreeConfigurationBuilder grownFir() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(NWBlocks.FIR_LOG.get()),
                new StraightTrunkPlacer(6, 1, 2),
                BlockStateProvider.simple(NWBlocks.FIR_LEAVES.get()),
                new SpruceFoliagePlacer(
                        UniformInt.of(1, 3),
                        UniformInt.of(0,1 ),
                        UniformInt.of(3,4)),
                new TwoLayersFeatureSize(2, 0, 2))
                .ignoreVines();
    }
    

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, NewWorld.id( name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
