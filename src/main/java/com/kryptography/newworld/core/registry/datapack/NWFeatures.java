package com.kryptography.newworld.core.registry.datapack;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.registry.NWBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
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
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
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
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class NWFeatures {

	public static final class NWConfiguredFeatures {
		private static final BeehiveDecorator BEES_1 = new BeehiveDecorator(1F);
		private static final BeehiveDecorator BEES_002 = new BeehiveDecorator(0.02F);

		public static final ResourceKey<ConfiguredFeature<?, ?>> FIR = createKey("fir");
		public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_BEES = createKey("fir_bees");
		public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_BEES_002 = createKey("fir_bees_002");

		public static final ResourceKey<ConfiguredFeature<?, ?>> NATURAL_FIR = createKey("natural_fir");
		public static final ResourceKey<ConfiguredFeature<?, ?>> NATURAL_FIR_BEES = createKey("natural_fir_bees");
		public static final ResourceKey<ConfiguredFeature<?, ?>> NATURAL_FIR_BEES_002 = createKey("natural_fir_bees_002");

		public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_MEADOW = createKey("fir_meadow");
		public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_SPAWN = createKey("fir_spawn");
		public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_TAIGA = createKey("fir_taiga");

		public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_FIR_LOG = createKey("fallen_fir_log");
		public static final ResourceKey<ConfiguredFeature<?, ?>> BURIAL_SITE = createKey("burial_site");

		public static final ResourceKey<ConfiguredFeature<?, ?>> GLOW_LICHEN_WOODED_MEADOW = createKey("glow_lichen_wooded_meadow");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BERRY_WOODED_MEADOW = createKey("patch_berry_wooded_meadow");

		public static final ResourceKey<ConfiguredFeature<?, ?>> LUSH_CAVE_MUD_PATCH = createKey("lush_cave_mud_patch");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LOAM_PATCH_CEILING = createKey("loam_patch_ceiling");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LOAM_ORE = createKey("loam_ore");
		public static final ResourceKey<ConfiguredFeature<?, ?>> LOAM_SNOW = createKey("loam_snow");
		public static final ResourceKey<ConfiguredFeature<?, ?>> CALCITE_PATCH = createKey("calcite_patch");
		public static final ResourceKey<ConfiguredFeature<?, ?>> CALCITE_VEGETATION = createKey("calcite_vegetation");

		public static final List<Block> VALID_BERRY_BUSH_BLOCKS = List.of(Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT, Blocks.MOSS_BLOCK);

		public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrap) {
			HolderGetter<ConfiguredFeature<?, ?>> features = bootstrap.lookup(Registries.CONFIGURED_FEATURE);
			HolderGetter<PlacedFeature> placedFeatures = bootstrap.lookup(Registries.PLACED_FEATURE);

			register(bootstrap, FIR, Feature.TREE, grownFir().build());
			register(bootstrap, FIR_BEES, Feature.TREE, grownFir().decorators(List.of(BEES_1)).build());
			register(bootstrap, FIR_BEES_002, Feature.TREE, grownFir().decorators(List.of(BEES_002)).build());

			register(bootstrap, NATURAL_FIR, Feature.TREE, grownFir().decorators(List.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());
			register(bootstrap, NATURAL_FIR_BEES, Feature.TREE, grownFir().decorators(List.of(BEES_1, new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());
			register(bootstrap, NATURAL_FIR_BEES_002, Feature.TREE, grownFir().decorators(List.of(BEES_002, new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());

			register(bootstrap, FALLEN_FIR_LOG, com.kryptography.newworld.core.registry.NWFeatures.FALLEN_FIR_LOG.get(), FeatureConfiguration.NONE);
			register(bootstrap, BURIAL_SITE, com.kryptography.newworld.core.registry.NWFeatures.BURIAL_SITE.get(), FeatureConfiguration.NONE);

			register(bootstrap, FIR_SPAWN, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(NWPlacedFeatures.NATURAL_FIR_BEES_CHECKED), 0.06F), new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.SPRUCE_CHECKED), 0.1f)), placedFeatures.getOrThrow(NWPlacedFeatures.NATURAL_FIR_CHECKED)));
			register(bootstrap, FIR_MEADOW, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(NWPlacedFeatures.FIR_BEES_CHECKED), 1.0F)), placedFeatures.getOrThrow(NWPlacedFeatures.FIR_CHECKED)));
			register(bootstrap, FIR_TAIGA, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(NWPlacedFeatures.FIR_BEES_CHECKED), 0.0F)), placedFeatures.getOrThrow(NWPlacedFeatures.FIR_CHECKED)));

			register(bootstrap, PATCH_BERRY_WOODED_MEADOW, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SWEET_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 2))), VALID_BERRY_BUSH_BLOCKS, 60));
			register(bootstrap, GLOW_LICHEN_WOODED_MEADOW, Feature.MULTIFACE_GROWTH, new MultifaceGrowthConfiguration((MultifaceBlock) Blocks.GLOW_LICHEN, 20, true, true, true, 0.5f, HolderSet.direct(Block::builtInRegistryHolder, NWBlocks.FIR_LOG.get(), Blocks.MOSSY_COBBLESTONE)));

			register(bootstrap, LOAM_PATCH_CEILING, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(NWBlocks.LOAM.get()), PlacementUtils.inlinePlaced(features.getOrThrow(MiscOverworldFeatures.ICE_SPIKE)), CaveSurface.CEILING, UniformInt.of(1, 2), 0.0F, 5, 0.08F, UniformInt.of(4, 7), 0.3F));
			register(bootstrap, LOAM_ORE, Feature.ORE, new OreConfiguration(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), NWBlocks.LOAM.get().defaultBlockState(), 64));
			register(bootstrap, LOAM_SNOW, com.kryptography.newworld.core.registry.NWFeatures.LOAM_SNOW.get(), FeatureConfiguration.NONE);

			register(bootstrap, CALCITE_VEGETATION, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.AIR.defaultBlockState(), 16).build())));
			register(bootstrap, CALCITE_PATCH, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.CALCITE), PlacementUtils.inlinePlaced(features.getOrThrow(CALCITE_VEGETATION)), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.08F, UniformInt.of(4, 7), 0.3F));

			register(bootstrap, LUSH_CAVE_MUD_PATCH, Feature.ORE, new OreConfiguration(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.MUD.defaultBlockState(), 64));
		}

		private static TreeConfigurationBuilder grownFir() {
			return new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(NWBlocks.FIR_LOG.get()),
					new StraightTrunkPlacer(7, 1, 2),
					BlockStateProvider.simple(NWBlocks.FIR_LEAVES.get()),
					new SpruceFoliagePlacer(UniformInt.of(1, 3), UniformInt.of(0, 1), UniformInt.of(3, 4)),
					new TwoLayersFeatureSize(2, 0, 2))
					.ignoreVines();
		}

		public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
			return ResourceKey.create(Registries.CONFIGURED_FEATURE, NewWorld.id(name));
		}

		private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
			context.register(key, new ConfiguredFeature<>(feature, configuration));
		}
	}

	public static final class NWPlacedFeatures {
		public static final ResourceKey<PlacedFeature> FIR_CHECKED = createKey("fir_checked");
		public static final ResourceKey<PlacedFeature> FIR_BEES_CHECKED = createKey("fir_bees_checked");
		public static final ResourceKey<PlacedFeature> NATURAL_FIR_CHECKED = createKey("natural_fir_checked");
		public static final ResourceKey<PlacedFeature> NATURAL_FIR_BEES_CHECKED = createKey("natural_fir_bees_checked");

		public static final ResourceKey<PlacedFeature> TREES_FIR = createKey("trees_fir");
		public static final ResourceKey<PlacedFeature> TREES_FIR_SCARCE = createKey("trees_fir_scarce");
		public static final ResourceKey<PlacedFeature> TREES_FIR_MEADOW = createKey("trees_fir_meadow");

		public static final ResourceKey<PlacedFeature> FALLEN_FIR_LOG = createKey("fallen_fir_log");
		public static final ResourceKey<PlacedFeature> BURIAL_SITE = createKey("burial_site");

		public static final ResourceKey<PlacedFeature> LOAM_PATCH_CEILING = createKey("loam_patch_ceiling");
		public static final ResourceKey<PlacedFeature> LOAM_ORE = createKey("loam_ore");
		public static final ResourceKey<PlacedFeature> LOAM_SNOW = createKey("loam_snow");
		public static final ResourceKey<PlacedFeature> CALCITE_PATCH = createKey("calcite_patch");

		public static final ResourceKey<PlacedFeature> LUSH_CAVE_MUD_PATCH = createKey("lush_cave_mud_patch");
		public static final ResourceKey<PlacedFeature> LUSH_CAVE_LOAM_ORE = createKey("lush_cave_loam_ore");

		public static final ResourceKey<PlacedFeature> GLOW_LICHEN_WOODED_MEADOW = createKey("glow_lichen_wooded_meadow");
		public static final ResourceKey<PlacedFeature> PATCH_BERRY_WOODED_MEADOW = createKey("patch_berry_wooded_meadow");
		public static final ResourceKey<PlacedFeature> PATCH_FERN_WOODED_MEADOW = createKey("patch_fern_wooded_meadow");
		public static final ResourceKey<PlacedFeature> BIRCH_CHERRY_GROVE = createKey("birch_cherry_grove");

		public static void bootstrap(BootstrapContext<PlacedFeature> bootstrap) {
			HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = bootstrap.lookup(Registries.CONFIGURED_FEATURE);

			register(bootstrap, FIR_CHECKED, configuredFeatures.getOrThrow(NWConfiguredFeatures.FIR), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
			register(bootstrap, FIR_BEES_CHECKED, configuredFeatures.getOrThrow(NWConfiguredFeatures.FIR_BEES), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
			register(bootstrap, NATURAL_FIR_CHECKED, configuredFeatures.getOrThrow(NWConfiguredFeatures.NATURAL_FIR), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
			register(bootstrap, NATURAL_FIR_BEES_CHECKED, configuredFeatures.getOrThrow(NWConfiguredFeatures.NATURAL_FIR_BEES), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
			register(bootstrap, TREES_FIR, configuredFeatures.getOrThrow(NWConfiguredFeatures.FIR_SPAWN), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 5)));
			register(bootstrap, TREES_FIR_SCARCE, configuredFeatures.getOrThrow(NWConfiguredFeatures.FIR_TAIGA), VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(1)));
			register(bootstrap, TREES_FIR_MEADOW, configuredFeatures.getOrThrow(NWConfiguredFeatures.FIR_MEADOW), VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(40)));
			register(bootstrap, FALLEN_FIR_LOG, configuredFeatures.getOrThrow(NWConfiguredFeatures.FALLEN_FIR_LOG), List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
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
			register(bootstrap, LUSH_CAVE_LOAM_ORE, configuredFeatures.getOrThrow(NWConfiguredFeatures.LOAM_ORE), List.of(CountPlacement.of(27), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(0)), BiomeFilter.biome()));
		}

		private static ResourceKey<PlacedFeature> createKey(String name) {
			return ResourceKey.create(Registries.PLACED_FEATURE, NewWorld.id(name));
		}

		private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
			context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
		}
	}
}
