package com.kryptography.newworld.init.worldgen.features;

import com.google.common.collect.ImmutableList;
import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.worldgen.NWFeature;
import com.kryptography.newworld.common.worldgen.features.FallenLogFeature;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.worldgen.NWTreeConfigurations;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

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
    public static final ResourceKey<ConfiguredFeature<?, ?>> LOAM_SNOW = registerKey("loam_snow");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrap) {
        HolderGetter<ConfiguredFeature<?, ?>> features = bootstrap.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> placedFeatures = bootstrap.lookup(Registries.PLACED_FEATURE);

        bootstrap.register(FALLEN_FIR_LOG, new ConfiguredFeature<>(NWFeature.FALLEN_FIR_LOG.get(), FeatureConfiguration.NONE));
        bootstrap.register(LOAM_SNOW, new ConfiguredFeature<>(NWFeature.LOAM_SNOW.get(), FeatureConfiguration.NONE));

        register(bootstrap, FIR, Feature.TREE, grownFir().build());
        register(bootstrap, FIR_BEES, Feature.TREE, grownFir().decorators(List.of(BEES_1)).build());
        register(bootstrap, FIR_BEES_002, Feature.TREE, grownFir().decorators(List.of(BEES_002)).build());

        register(bootstrap, NATURAL_FIR, Feature.TREE, grownFir().decorators(List.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());
        register(bootstrap, NATURAL_FIR_BEES, Feature.TREE, grownFir().decorators(List.of(BEES_1, new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());
        register(bootstrap, NATURAL_FIR_BEES_002, Feature.TREE, grownFir().decorators(List.of(BEES_002, new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());

        register(bootstrap, FIR_SPAWN, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(NWPlacedFeatures.NATURAL_FIR_BEES_CHECKED), 0.06F), new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.SPRUCE_CHECKED), 0.1f)), placedFeatures.getOrThrow(NWPlacedFeatures.NATURAL_FIR_CHECKED)));
        register(bootstrap, FIR_MEADOW, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(NWPlacedFeatures.FIR_BEES_CHECKED), 1.0F)), placedFeatures.getOrThrow(NWPlacedFeatures.FIR_CHECKED) ));
        register(bootstrap, FIR_TAIGA, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(NWPlacedFeatures.FIR_BEES_CHECKED), 0.0F)), placedFeatures.getOrThrow(NWPlacedFeatures.FIR_CHECKED) ));


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
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
