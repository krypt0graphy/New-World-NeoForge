package com.kryptography.newworld.core.registry;

import com.google.common.collect.ImmutableList;
import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.common.worldgen.structures.BuriedBunkerFeature;
import com.kryptography.newworld.core.other.tags.NWBiomeTags;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;

public class NWStructureTypes {
	public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, NewWorld.MOD_ID);

	public static final DeferredHolder<StructureType<?>, StructureType<BuriedBunkerFeature>> BURIED_BUNKER = STRUCTURE_TYPES.register("buried_bunker", () -> () -> BuriedBunkerFeature.CODEC);

	public static class NWProcessorLists {
		public static final ResourceKey<StructureProcessorList> BURIED_BUNKER = createKey("buried_bunker");

		public static void bootstrap(BootstrapContext<StructureProcessorList> context) {
			register(context, BURIED_BUNKER,
					ImmutableList.of(new RuleProcessor(List.of(
							new ProcessorRule(new RandomBlockMatchTest(Blocks.STONE, 0.1F), AlwaysTrueTest.INSTANCE, Blocks.INFESTED_STONE.defaultBlockState()),
							new ProcessorRule(new RandomBlockMatchTest(Blocks.DIRT, 0.1f), AlwaysTrueTest.INSTANCE, Blocks.ROOTED_DIRT.defaultBlockState()),
							new ProcessorRule(new RandomBlockMatchTest(Blocks.DIRT, 0.1f), AlwaysTrueTest.INSTANCE, Blocks.COARSE_DIRT.defaultBlockState()),
							new ProcessorRule(new RandomBlockMatchTest(Blocks.DIRT, 0.05f), AlwaysTrueTest.INSTANCE, Blocks.AIR.defaultBlockState()),
							new ProcessorRule(new RandomBlockMatchTest(Blocks.CHAIN, 0.1f), AlwaysTrueTest.INSTANCE, Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true)),
							new ProcessorRule(new RandomBlockMatchTest(Blocks.MOSSY_STONE_BRICK_STAIRS, 0.8f), AlwaysTrueTest.INSTANCE, Blocks.MOSSY_STONE_BRICK_SLAB.defaultBlockState())
					))));
		}

		private static void register(BootstrapContext<StructureProcessorList> context, ResourceKey<StructureProcessorList> key, List<StructureProcessor> processors) {
			context.register(key, new StructureProcessorList(processors));
		}

		private static ResourceKey<StructureProcessorList> createKey(String name) {
			return ResourceKey.create(Registries.PROCESSOR_LIST, NewWorld.id(name));
		}
	}

	public static class NWTemplatePools {
		public static final ResourceKey<StructureTemplatePool> BURIED_BUNKER = createKey("buried_bunker");

		public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
			Holder<StructureTemplatePool> empty = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY);
			Holder<StructureProcessorList> bunkerList = context.lookup(Registries.PROCESSOR_LIST).getOrThrow(NWProcessorLists.BURIED_BUNKER);

			context.register(
					BURIED_BUNKER,
					new StructureTemplatePool(
							empty,
							ImmutableList.of(
									Pair.of(StructurePoolElement.single(NewWorld.id("buried_bunker").toString(), bunkerList), 1),
									Pair.of(StructurePoolElement.single(NewWorld.id("buried_bunker_empty").toString(), bunkerList), 10)
							),
							StructureTemplatePool.Projection.RIGID
					)
			);
		}

		private static ResourceKey<StructureTemplatePool> createKey(String name) {
			return ResourceKey.create(Registries.TEMPLATE_POOL, NewWorld.id(name));
		}
	}

	public static class NWStructures {
		public static final ResourceKey<Structure> BURIED_BUNKER = createKey("buried_bunker");

		public static void bootstrap(BootstrapContext<Structure> context) {
			HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

			context.register(BURIED_BUNKER, new BuriedBunkerFeature(new Structure.StructureSettings(
					biomes.getOrThrow(NWBiomeTags.HAS_BURIED_BUNKER),
					Map.of(),
					GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
					TerrainAdjustment.NONE
			)));
		}

		private static ResourceKey<Structure> createKey(String name) {
			return ResourceKey.create(Registries.STRUCTURE, NewWorld.id(name));
		}
	}

	public static class NWStructureSets {
		public static final ResourceKey<StructureSet> BURIED_BUNKER = createKey("buried_bunker");

		public static void bootstrap(BootstrapContext<StructureSet> context) {
			HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

			context.register(BURIED_BUNKER, new StructureSet(
					structures.getOrThrow(NWStructures.BURIED_BUNKER),
					new RandomSpreadStructurePlacement(24, 4, RandomSpreadType.LINEAR, 1694767080)));
		}

		private static ResourceKey<StructureSet> createKey(String name) {
			return ResourceKey.create(Registries.STRUCTURE_SET, NewWorld.id(name));
		}
	}
}
