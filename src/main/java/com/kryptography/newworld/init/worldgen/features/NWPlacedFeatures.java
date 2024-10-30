package com.kryptography.newworld.init.worldgen.features;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class NWPlacedFeatures {

    public static final ResourceKey<PlacedFeature> FIR_CHECKED = registerKey("fir_checked");
    public static final ResourceKey<PlacedFeature> FIR_BEES_CHECKED = registerKey("fir_bees_checked");
    public static final ResourceKey<PlacedFeature> NATURAL_FIR_CHECKED = registerKey("natural_fir_checked");
    public static final ResourceKey<PlacedFeature> NATURAL_FIR_BEES_CHECKED = registerKey("natural_fir_bees_checked");

    public static final ResourceKey<PlacedFeature> TREES_FIR = registerKey("trees_fir");
    public static final ResourceKey<PlacedFeature> TREES_FIR_SCARCE = registerKey("trees_fir_scarce");
    public static final ResourceKey<PlacedFeature> TREES_FIR_MEADOW = registerKey("trees_fir_meadow");

    public static final ResourceKey<PlacedFeature> FALLEN_FIR_LOG = registerKey("fallen_fir_log");

    public static final ResourceKey<PlacedFeature> LOAM_SNOW = registerKey("loam_snow");

    public static void bootstrap(BootstrapContext<PlacedFeature> bootstrap) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = bootstrap.lookup(Registries.CONFIGURED_FEATURE);

        register(bootstrap, FIR_CHECKED, configuredFeatures.getOrThrow(NWConfiguredFeatures.FIR),  List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(bootstrap, FIR_BEES_CHECKED, configuredFeatures.getOrThrow(NWConfiguredFeatures.FIR_BEES_002),  List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(bootstrap, NATURAL_FIR_CHECKED, configuredFeatures.getOrThrow(NWConfiguredFeatures.NATURAL_FIR),  List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(bootstrap, NATURAL_FIR_BEES_CHECKED, configuredFeatures.getOrThrow(NWConfiguredFeatures.NATURAL_FIR_BEES_002),  List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(bootstrap, TREES_FIR, configuredFeatures.getOrThrow(NWConfiguredFeatures.FIR_SPAWN), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 5)));
        register(bootstrap, TREES_FIR_SCARCE, configuredFeatures.getOrThrow(NWConfiguredFeatures.FIR_TAIGA), VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(1)));
        register(bootstrap, TREES_FIR_MEADOW, configuredFeatures.getOrThrow(NWConfiguredFeatures.FIR_MEADOW), VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(40)));
        register(bootstrap, FALLEN_FIR_LOG, configuredFeatures.getOrThrow(NWConfiguredFeatures.FALLEN_FIR_LOG), List.of(RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(bootstrap, LOAM_SNOW, configuredFeatures.getOrThrow(NWConfiguredFeatures.LOAM_SNOW), List.of(CountPlacement.of(8), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(32)), BiomeFilter.biome()));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
