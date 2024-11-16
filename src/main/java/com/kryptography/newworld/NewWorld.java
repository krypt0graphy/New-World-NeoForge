package com.kryptography.newworld;

import com.kryptography.newworld.client.NWBoatRenderer;
import com.kryptography.newworld.common.worldgen.NWFeature;
import com.kryptography.newworld.init.NWBlockEntityTypes;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWEntityTypes;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.init.data.NWDataMaps;
import com.kryptography.newworld.init.data.loot.NWLootModifiers;
import com.kryptography.newworld.init.data.woodset.Woodset;
import com.kryptography.newworld.init.worldgen.NWOverworldRegion;
import com.kryptography.newworld.init.worldgen.structure.NWStructures;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import terrablender.api.Regions;

@Mod(NewWorld.MOD_ID)

public class NewWorld {
    public static final String MOD_ID = "newworld";
    public static final String MOD_NAME = "New World";

    public NewWorld(IEventBus bus) {
        NWBlocks.BLOCKS.register(bus);
        NWItems.ITEMS.register(bus);
        NWBlockEntityTypes.BLOCK_ENTITIES.register(bus);
        NWEntityTypes.ENTITIES.register(bus);
        NWLootModifiers.LOOT_MODIFIERS.register(bus);
        NWFeature.FEATURES.register(bus);
        //NWStructures.STRUCTURES.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::addCreative);

    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            NWDataMaps.register();
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NWBlocks.FIR_SAPLING.getId(), NWBlocks.POTTED_FIR_SAPLING);
            Regions.register(new NWOverworldRegion(ResourceLocation.fromNamespaceAndPath(MOD_ID, "overworld"), 5));
        });
    }

    public void addCreative(BuildCreativeModeTabContentsEvent event) {
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
        }

        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            addAfter(event, Items.SPRUCE_BOAT, NWItems.FIR_BOAT);
            addAfter(event, NWItems.FIR_BOAT, NWItems.FIR_CHEST_BOAT);
            addAfter(event, Items.NETHERITE_HOE, NWItems.ANCIENT_MATTOCK);
        }
    }


    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent evt) {
        evt.enqueueWork(() -> {
            Sheets.addWoodType(Woodset.FIR_WOOD_TYPE);
            EntityRenderers.register(NWEntityTypes.FIR_BOAT.get(), pContext -> new NWBoatRenderer(pContext, false));
            EntityRenderers.register(NWEntityTypes.FIR_CHEST_BOAT.get(), pContext -> new NWBoatRenderer(pContext, true));
        });
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void addAfter(BuildCreativeModeTabContentsEvent event, ItemLike first, ItemLike second) {
        event.insertAfter(new ItemStack(first), new ItemStack(second), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

}
