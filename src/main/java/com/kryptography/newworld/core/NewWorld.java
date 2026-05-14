package com.kryptography.newworld.core;

import com.kryptography.newworld.common.worldgen.NWFeature;
import com.kryptography.newworld.core.data.client.NWBlockStateProvider;
import com.kryptography.newworld.core.data.client.NWItemModelProvider;
import com.kryptography.newworld.core.data.server.*;
import com.kryptography.newworld.core.data.server.loot.NWBlockLootProvider;
import com.kryptography.newworld.core.data.server.loot.NWChestLootProvider;
import com.kryptography.newworld.core.data.server.loot.NWGlobalLootModifierProvider;
import com.kryptography.newworld.core.data.server.tags.NWBiomeTagsProvider;
import com.kryptography.newworld.core.data.server.tags.NWBlockTagsProvider;
import com.kryptography.newworld.core.data.server.tags.NWItemTagsProvider;
import com.kryptography.newworld.core.other.NWCompat;
import com.kryptography.newworld.core.registry.*;
import com.kryptography.newworld.integration.FDIntegration;
import com.kryptography.newworld.integration.Mods;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod(NewWorld.MOD_ID)

public class NewWorld {
	public static final String MOD_ID = "newworld";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);
	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	public NewWorld(IEventBus bus) {
		NWBlocks.BLOCKS.register(bus);
		NWItems.ITEMS.register(bus);
		NWBlockEntityTypes.BLOCK_ENTITIES.register(bus);
		NWLootModifiers.LOOT_MODIFIERS.register(bus);
		NWFeature.FEATURES.register(bus);
		NWStructureTypes.STRUCTURE_TYPES.register(bus);
		NWStats.STATS.register(bus);
		NWBlocks.setupTabEditors();
		bus.addListener(this::commonSetup);
		bus.addListener(this::dataSetup);
		bus.addListener(this::addPackFinders);

		if (Mods.FARMERSDELIGHT.isLoaded()) {
			bus.addListener(FDIntegration::addBlockEntities);
		}
	}

	private void addPackFinders(AddPackFindersEvent event) {
		if (event.getPackType() == PackType.CLIENT_RESOURCES) {
			event.addPackFinders(
				id("resourcepacks/nml_fir_bookshelf"),
				PackType.CLIENT_RESOURCES,
				Component.literal("NML Fir Bookshelf"),
				PackSource.BUILT_IN,
				Mods.NOMANSLAND.isLoaded() && !Mods.WOODWORKS.isLoaded(),
				Pack.Position.TOP);
		}
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		PackOutput packOutput = gen.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();

		boolean server = event.includeServer();
		boolean client = event.includeClient();

		DatapackBuiltinEntriesProvider datapackProvider = new NWDatapackEntriesProvider(packOutput, lookup);
		gen.addProvider(server, datapackProvider);
		lookup = datapackProvider.getRegistryProvider();

		BlockTagsProvider blockTagsProvider = new NWBlockTagsProvider(packOutput, lookup, existingFileHelper);

		gen.addProvider(server, blockTagsProvider);
		gen.addProvider(server, new NWBiomeTagsProvider(packOutput, lookup, existingFileHelper));
		gen.addProvider(server, new NWPaintingVariantTagsProvider(packOutput, lookup, existingFileHelper));
		gen.addProvider(server, new NWRecipeProvider(packOutput, lookup));
		gen.addProvider(server, NWAdvancementProvider.create(packOutput, lookup, existingFileHelper));
		gen.addProvider(server, new LootTableProvider(packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(NWChestLootProvider::new, LootContextParamSets.CHEST), new LootTableProvider.SubProviderEntry(NWBlockLootProvider::new, LootContextParamSets.BLOCK)), lookup));
		gen.addProvider(server, new NWGlobalLootModifierProvider(packOutput, lookup));
		gen.addProvider(server, new NWDataMapProvider(packOutput, lookup));

		gen.addProvider(client, new NWItemTagsProvider(packOutput, lookup, blockTagsProvider.contentsGetter(), existingFileHelper));
		gen.addProvider(client, new NWItemModelProvider(packOutput, existingFileHelper));
		gen.addProvider(client, new NWBlockStateProvider(packOutput, existingFileHelper));
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NWBlocks.FIR_SAPLING.getId(), NWBlocks.POTTED_FIR_SAPLING);
			NWCompat.register();
		});
	}
}