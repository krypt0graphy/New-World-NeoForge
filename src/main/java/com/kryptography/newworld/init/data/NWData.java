package com.kryptography.newworld.init.data;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.data.providers.NWBlockStateProvider;
import com.kryptography.newworld.common.data.providers.NWDataMapProvider;
import com.kryptography.newworld.common.data.providers.NWItemModelProvider;
import com.kryptography.newworld.common.data.providers.NWRecipeProvider;
import com.kryptography.newworld.common.data.providers.loot.NWChestLootProvider;
import com.kryptography.newworld.common.data.providers.loot.NWGlobalLootModifierProvider;
import com.kryptography.newworld.common.data.providers.tags.NWBiomeTagsProvider;
import com.kryptography.newworld.common.data.providers.tags.NWBlockTagsProvider;
import com.kryptography.newworld.common.data.providers.tags.NWItemTagsProvider;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.init.worldgen.NWWorldgenData;
import com.kryptography.newworld.integration.BBIntegration;
import com.kryptography.newworld.integration.FDIntegration;
import com.kryptography.newworld.integration.Mods;
import com.kryptography.newworld.integration.NMLIntegration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = NewWorld.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NWData {



	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		PackOutput packOutput = gen.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();


		DatapackBuiltinEntriesProvider datapackProvider = new NWWorldgenData(packOutput, lookup);
		event.getGenerator().addProvider(event.includeServer(), datapackProvider);

		BlockTagsProvider blockTagsProvider = new NWBlockTagsProvider(packOutput, lookup,existingFileHelper);
		gen.addProvider(event.includeServer(), blockTagsProvider);
		gen.addProvider(event.includeClient(), new NWItemTagsProvider(packOutput, lookup, blockTagsProvider.contentsGetter(), existingFileHelper));
		gen.addProvider(event.includeClient(), new NWItemModelProvider(packOutput, existingFileHelper));
		gen.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(NWChestLootProvider::new, LootContextParamSets.CHEST)), lookup));
		gen.addProvider(event.includeClient(), new NWBlockStateProvider(packOutput, existingFileHelper));
		gen.addProvider(event.includeServer(), new NWDataMapProvider(packOutput, lookup));
		gen.addProvider(event.includeServer(), new NWRecipeProvider(packOutput, lookup));
		gen.addProvider(event.includeServer(), new NWGlobalLootModifierProvider(packOutput, lookup));
		gen.addProvider(event.includeServer(), new NWBiomeTagsProvider(packOutput, datapackProvider.getRegistryProvider(),existingFileHelper));
	}

	public static void addCreative(BuildCreativeModeTabContentsEvent event) {
		if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			addAfter(event, Items.SPRUCE_LOG, NWBlocks.FIR_LOG.get());
			addAfter(event, Items.SPRUCE_LEAVES, NWBlocks.FIR_LEAVES.get());
			addAfter(event, Items.SPRUCE_SAPLING, NWBlocks.FIR_SAPLING.get());
			addAfter(event, Items.SHORT_GRASS, NWBlocks.MOSS_SPROUTS.get());
			addAfter(event, Items.MUD, NWBlocks.LOAM.get());

		}

		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			addAfter(event, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, NWItems.MATTOCK_CRAFTING_TEMPLATE);
			addAfter(event, NWItems.MATTOCK_CRAFTING_TEMPLATE, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD);
			addAfter(event, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT);
			event.accept(NWItems.ILLAGER_TOME);
		}

		if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			if (Mods.BLOCKBOX.isLoaded()) {
				addAfter(event, Items.SPRUCE_BUTTON, BBIntegration.STRIPPED_SPIKED_FIR_PALISADE);
				addAfter(event, Items.SPRUCE_BUTTON, BBIntegration.STRIPPED_FIR_PALISADE);
				addAfter(event, Items.SPRUCE_BUTTON, BBIntegration.SPIKED_FIR_PALISADE);
				addAfter(event, Items.SPRUCE_BUTTON, BBIntegration.FIR_PALISADE);
			}
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_BUTTON);
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_PRESSURE_PLATE);
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_TRAPDOOR);
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_DOOR);
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_FENCE_GATE);
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_FENCE);
			if(Mods.NOMANSLAND.isLoaded()) {
				addAfter(event, Items.SPRUCE_BUTTON, NMLIntegration.TRIMMED_FIR_PLANKS.get());
				addAfter(event, Items.SPRUCE_BUTTON, NMLIntegration.FIR_BOOKSHELF.get());
			}
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_SLAB);
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_STAIRS);
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_PLANKS);
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.STRIPPED_FIR_WOOD);
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.STRIPPED_FIR_LOG);
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_WOOD);
			addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_LOG);

			addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_TILE_WALL);
			addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_TILE_SLAB);
			addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_TILE_STAIRS);
			addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_TILES);
			addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_BRICK_WALL);
			addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_BRICK_SLAB);
			addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_BRICK_STAIRS);
			addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_BRICKS);
			addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_WALL);
			addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_SLAB);
			addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_STAIRS);
			addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM);

			if (Mods.FARMERSDELIGHT.isLoaded()) {
				event.accept(FDIntegration.FIR_CABINET);
			}
		}
		if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			addAfter(event, Items.SPRUCE_HANGING_SIGN, NWBlocks.FIR_HANGING_SIGN);
			addAfter(event, Items.SPRUCE_HANGING_SIGN, NWBlocks.FIR_SIGN);
			if (Mods.BLOCKBOX.isLoaded()) {
				addAfter(event, Items.RESPAWN_ANCHOR, BBIntegration.FIR_SEAT);
			}
			event.accept(NWBlocks.TOMBSTONE);
		}

		if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			addAfter(event, Items.SPRUCE_CHEST_BOAT, NWItems.FIR_CHEST_BOAT);
			addAfter(event, Items.SPRUCE_CHEST_BOAT, NWItems.FIR_BOAT);
			addAfter(event, Items.NETHERITE_HOE, NWItems.ANCIENT_MATTOCK);
		}

	}

	public static void addAfter(BuildCreativeModeTabContentsEvent event, ItemLike first, ItemLike second) {
		event.insertAfter(new ItemStack(first), new ItemStack(second), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	}
}
