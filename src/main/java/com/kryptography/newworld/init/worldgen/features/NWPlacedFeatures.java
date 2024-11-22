package com.kryptography.newworld.init.worldgen.features;

import com.kryptography.newworld.NewWorld;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
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
    public static final ResourceKey<PlacedFeature> BURIAL_SITE = registerKey("burial_site");

    public static final ResourceKey<PlacedFeature> LOAM_PATCH_CEILING = registerKey("loam_patch_ceiling");
    public static final ResourceKey<PlacedFeature> LOAM_ORE = registerKey("loam_ore");
    public static final ResourceKey<PlacedFeature> LOAM_SNOW = registerKey("loam_snow");
    public static final ResourceKey<PlacedFeature> CALCITE_PATCH = registerKey("calcite_patch");

    public static final ResourceKey<PlacedFeature> LUSH_CAVE_MUD_PATCH = registerKey("lush_cave_mud_patch");
    public static final ResourceKey<PlacedFeature> LUSH_CAVE_LOAM_ORE = registerKey("lush_cave_loam_ore");


    public static final ResourceKey<PlacedFeature> GLOW_LICHEN_WOODED_MEADOW = registerKey("glow_lichen_wooded_meadow");
    public static final ResourceKey<PlacedFeature> PATCH_BERRY_WOODED_MEADOW = registerKey("patch_berry_wooded_meadow");
    public static final ResourceKey<PlacedFeature> PATCH_FERN_WOODED_MEADOW = registerKey("patch_fern_wooded_meadow");
    public static final ResourceKey<PlacedFeature> BIRCH_CHERRY_GROVE = registerKey("birch_cherry_grove");

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
        register(bootstrap, BURIAL_SITE, configuredFeatures.getOrThrow(NWConfiguredFeatures.BURIAL_SITE), List.of());
        register(bootstrap, PATCH_BERRY_WOODED_MEADOW, configuredFeatures.getOrThrow(NWConfiguredFeatures.PATCH_BERRY_WOODED_MEADOW), List.of(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(bootstrap, GLOW_LICHEN_WOODED_MEADOW, configuredFeatures.getOrThrow(NWConfiguredFeatures.GLOW_LICHEN_WOODED_MEADOW), List.of(CountPlacement.of(UniformInt.of(104, 157)), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, RarityFilter.onAverageOnceEvery(2), SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, 30, 300), BiomeFilter.biome()));
        register(bootstrap, PATCH_FERN_WOODED_MEADOW, configuredFeatures.getOrThrow(VegetationFeatures.PATCH_TAIGA_GRASS), List.of(CountPlacement.of(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(bootstrap, BIRCH_CHERRY_GROVE, configuredFeatures.getOrThrow(VegetationFeatures.BIRCH_TALL), VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(3)));

        register(bootstrap, LOAM_PATCH_CEILING, configuredFeatures.getOrThrow(NWConfiguredFeatures.LOAM_PATCH_CEILING), List.of(CountPlacement.of(125), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.matchesBlocks(Blocks.AIR), 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome()));
        register(bootstrap, LOAM_ORE, configuredFeatures.getOrThrow(NWConfiguredFeatures.LOAM_ORE), List.of(CountPlacement.of(250), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(32)), BiomeFilter.biome()));
        register(bootstrap, LOAM_SNOW, configuredFeatures.getOrThrow(NWConfiguredFeatures.LOAM_SNOW), List.of(CountPlacement.of(8), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(32)), BiomeFilter.biome()));
        register(bootstrap, CALCITE_PATCH, configuredFeatures.getOrThrow(NWConfiguredFeatures.CALCITE_PATCH), List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.matchesBlocks(Blocks.AIR), 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(bootstrap, LUSH_CAVE_MUD_PATCH, configuredFeatures.getOrThrow(NWConfiguredFeatures.LUSH_CAVE_MUD_PATCH), List.of(CountPlacement.of(35), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(0)), BiomeFilter.biome()));
        register(bootstrap, LUSH_CAVE_LOAM_ORE, configuredFeatures.getOrThrow(NWConfiguredFeatures.LOAM_ORE),  List.of(CountPlacement.of(27), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(0)), BiomeFilter.biome()));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
