package com.kryptography.newworld.init.data;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.datagenproviders.NWBlockStateProvider;
import com.kryptography.newworld.common.datagenproviders.NWDataMapProvider;
import com.kryptography.newworld.common.datagenproviders.NWItemModelProvider;
import com.kryptography.newworld.common.datagenproviders.NWRecipeProvider;
import com.kryptography.newworld.common.datagenproviders.loot.NWBlockLootProvider;
import com.kryptography.newworld.common.datagenproviders.loot.NWChestLootProvider;
import com.kryptography.newworld.common.datagenproviders.loot.NWGlobalLootModifierProvider;
import com.kryptography.newworld.common.datagenproviders.tags.NWBiomeTagsProvider;
import com.kryptography.newworld.common.datagenproviders.tags.NWBlockTagsProvider;
import com.kryptography.newworld.common.datagenproviders.tags.NWItemTagsProvider;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.init.worldgen.NWWorldgenData;
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
        gen.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(NWBlockLootProvider::new, LootContextParamSets.BLOCK), new LootTableProvider.SubProviderEntry(NWChestLootProvider::new, LootContextParamSets.CHEST)), lookup));
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

        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            addAfter(event, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, NWItems.MATTOCK_CRAFTING_TEMPLATE);
            addAfter(event, NWItems.MATTOCK_CRAFTING_TEMPLATE, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD);
            addAfter(event, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD, NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT);
            event.accept(NWItems.ILLAGER_TOME);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_LOG);
            addAfter(event, NWBlocks.FIR_LOG, NWBlocks.FIR_WOOD);
            addAfter(event, NWBlocks.FIR_WOOD, NWBlocks.STRIPPED_FIR_LOG);
            addAfter(event, NWBlocks.STRIPPED_FIR_LOG, NWBlocks.STRIPPED_FIR_WOOD);
            addAfter(event, NWBlocks.STRIPPED_FIR_WOOD, NWBlocks.FIR_PLANKS);
            addAfter(event, NWBlocks.FIR_PLANKS, NWBlocks.FIR_STAIRS);
            addAfter(event, NWBlocks.FIR_STAIRS, NWBlocks.FIR_SLAB);
            addAfter(event, NWBlocks.FIR_SLAB, NWBlocks.FIR_FENCE);
            addAfter(event, NWBlocks.FIR_FENCE, NWBlocks.FIR_FENCE_GATE);
            addAfter(event, NWBlocks.FIR_FENCE_GATE, NWBlocks.FIR_DOOR);
            addAfter(event, NWBlocks.FIR_DOOR, NWBlocks.FIR_TRAPDOOR);
            addAfter(event, NWBlocks.FIR_TRAPDOOR, NWBlocks.FIR_PRESSURE_PLATE);
            addAfter(event, NWBlocks.FIR_PRESSURE_PLATE, NWBlocks.FIR_BUTTON);

            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM);
            addAfter(event, NWBlocks.LOAM, NWBlocks.LOAM_STAIRS);
            addAfter(event, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM_SLAB);
            addAfter(event, NWBlocks.LOAM_SLAB, NWBlocks.LOAM_WALL);
            addAfter(event, NWBlocks.LOAM_WALL, NWBlocks.LOAM_BRICKS);
            addAfter(event, NWBlocks.LOAM_BRICKS, NWBlocks.LOAM_BRICK_STAIRS);
            addAfter(event, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICK_SLAB);
            addAfter(event, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICK_WALL);
            addAfter(event, NWBlocks.LOAM_BRICK_WALL, NWBlocks.LOAM_TILES);
            addAfter(event, NWBlocks.LOAM_TILES, NWBlocks.LOAM_TILE_STAIRS);
            addAfter(event, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILE_SLAB);
            addAfter(event, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILE_WALL);
        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            addAfter(event, Items.SPRUCE_HANGING_SIGN, NWBlocks.FIR_SIGN);
            addAfter(event, NWBlocks.FIR_SIGN, NWBlocks.FIR_HANGING_SIGN);
            event.accept(NWBlocks.TOMBSTONE);
        }

        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            addAfter(event, Items.SPRUCE_CHEST_BOAT, NWItems.FIR_BOAT);
            addAfter(event, NWItems.FIR_BOAT, NWItems.FIR_CHEST_BOAT);
            addAfter(event, Items.NETHERITE_HOE, NWItems.ANCIENT_MATTOCK);
        }
    }

    public static void addAfter(BuildCreativeModeTabContentsEvent event, ItemLike first, ItemLike second) {
        event.insertAfter(new ItemStack(first), new ItemStack(second), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
