package com.kryptography.newworld.init.data;

import com.google.common.collect.Maps;
import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.data.providers.NWBlockStateProvider;
import com.kryptography.newworld.common.data.providers.NWItemModelProvider;
import com.kryptography.newworld.common.data.providers.NWRecipeProvider;
import com.kryptography.newworld.common.data.providers.loot.NWBlockLootProvider;
import com.kryptography.newworld.common.data.providers.loot.NWChestLootProvider;
import com.kryptography.newworld.common.data.providers.loot.NWGlobalLootModifierProvider;
import com.kryptography.newworld.common.data.providers.tags.NWBiomeTagsProvider;
import com.kryptography.newworld.common.data.providers.tags.NWBlockTagsProvider;
import com.kryptography.newworld.common.data.providers.tags.NWItemTagsProvider;
import com.kryptography.newworld.common.data.providers.tags.NWPaintingVariantTagProvider;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.init.worldgen.NWWorldgenData;
import com.kryptography.newworld.integration.Mods;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = NewWorld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NWData {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();


        DatapackBuiltinEntriesProvider datapackProvider = new NWWorldgenData(packOutput, lookup);
        event.getGenerator().addProvider(event.includeServer(), datapackProvider);

        BlockTagsProvider blockTagsProvider = new NWBlockTagsProvider(packOutput, lookup, existingFileHelper);
        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeClient(), new NWItemTagsProvider(packOutput, lookup, blockTagsProvider.contentsGetter(), existingFileHelper));
        gen.addProvider(event.includeClient(), new NWItemModelProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(NWChestLootProvider::new, LootContextParamSets.CHEST), new LootTableProvider.SubProviderEntry(NWBlockLootProvider::new, LootContextParamSets.BLOCK))));
        gen.addProvider(event.includeClient(), new NWBlockStateProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), new NWRecipeProvider(packOutput));
        gen.addProvider(event.includeServer(), new NWGlobalLootModifierProvider(packOutput, lookup));
        gen.addProvider(event.includeServer(), new NWBiomeTagsProvider(packOutput, datapackProvider.getRegistryProvider(),existingFileHelper));
        gen.addProvider(event.includeClient(), new NWPaintingVariantTagProvider(packOutput, lookup, existingFileHelper));
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            addAfter(event, Items.SPRUCE_LOG, NWBlocks.FIR_LOG.get());
            addAfter(event, Items.SPRUCE_LEAVES, NWBlocks.FIR_LEAVES.get());
            addAfter(event, Items.SPRUCE_SAPLING, NWBlocks.FIR_SAPLING.get());
            addAfter(event, Items.GRASS, NWBlocks.MOSS_SPROUTS.get());
            addAfter(event, Items.MUD, NWBlocks.LOAM.get());

        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            addAfter(event, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, NWItems.MATTOCK_CRAFTING_TEMPLATE.get());
            addAfter(event, NWItems.MATTOCK_CRAFTING_TEMPLATE.get(), NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get());
            addAfter(event, NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get(), NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get());
            event.accept(NWItems.ILLAGER_TOME);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_BUTTON.get());
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_PRESSURE_PLATE.get());
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_TRAPDOOR.get());
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_DOOR.get());
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_FENCE_GATE.get());
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_FENCE.get());
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_SLAB.get());
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_STAIRS.get());
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_PLANKS.get());
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.STRIPPED_FIR_WOOD.get());
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.STRIPPED_FIR_LOG.get());
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_WOOD.get());
            addAfter(event, Items.SPRUCE_BUTTON, NWBlocks.FIR_LOG.get());

            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_TILE_WALL.get());
            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_TILE_SLAB.get());
            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_TILE_STAIRS.get());
            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_TILES.get());
            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_BRICK_WALL.get());
            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_BRICK_SLAB.get());
            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_BRICK_STAIRS.get());
            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_BRICKS.get());
            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_WALL.get());
            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_SLAB.get());
            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM_STAIRS.get());
            addAfter(event, Items.MUD_BRICK_WALL, NWBlocks.LOAM.get());

            if (Mods.FARMERSDELIGHT.isLoaded()) {
                NWBlocks.FIR_CABINET.ifPresent(event::accept);
            }
        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            addAfter(event, Items.SPRUCE_HANGING_SIGN, NWBlocks.FIR_HANGING_SIGN.get());
            addAfter(event, Items.SPRUCE_HANGING_SIGN, NWBlocks.FIR_SIGN.get());
            event.accept(NWBlocks.TOMBSTONE);
        }

        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            addAfter(event, Items.SPRUCE_CHEST_BOAT, NWItems.FIR_CHEST_BOAT.get());
            addAfter(event, Items.SPRUCE_CHEST_BOAT, NWItems.FIR_BOAT.get());
            addAfter(event, Items.NETHERITE_HOE, NWItems.ANCIENT_MATTOCK.get());
        }

    }

    public static void addAfter(BuildCreativeModeTabContentsEvent event, ItemLike first, ItemLike second) {
        event.getEntries().putAfter(new ItemStack(first), new ItemStack(second), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    public static void vanillaInteractions() {
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;

        ComposterBlock.add(0.3f, NWBlocks.FIR_LEAVES.get());
        ComposterBlock.add(0.3f, NWBlocks.FIR_SAPLING.get());

        fireBlock.setFlammable(NWBlocks.FIR_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(NWBlocks.FIR_SLAB.get(), 5, 20);
        fireBlock.setFlammable(NWBlocks.FIR_STAIRS.get(), 5, 20);
        fireBlock.setFlammable(NWBlocks.FIR_FENCE.get(), 5, 20);
        fireBlock.setFlammable(NWBlocks.FIR_FENCE_GATE.get(), 5, 20);
        fireBlock.setFlammable(NWBlocks.FIR_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(NWBlocks.FIR_LOG.get(), 5, 5);
        fireBlock.setFlammable(NWBlocks.FIR_WOOD.get(), 5, 5);
        fireBlock.setFlammable(NWBlocks.STRIPPED_FIR_LOG.get(), 5, 5);
        fireBlock.setFlammable(NWBlocks.STRIPPED_FIR_WOOD.get(), 5, 5);

        AxeItem.STRIPPABLES.put(NWBlocks.FIR_LOG.get(), NWBlocks.STRIPPED_FIR_LOG.get());
        AxeItem.STRIPPABLES.put(NWBlocks.FIR_WOOD.get(), NWBlocks.STRIPPED_FIR_WOOD.get());
    }
}
