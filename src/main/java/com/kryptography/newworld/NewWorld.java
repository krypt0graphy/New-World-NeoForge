package com.kryptography.newworld;

import com.kryptography.newworld.client.NWBoatRenderer;
import com.kryptography.newworld.init.worldgen.NWOverworldRegion;
import com.kryptography.newworld.init.worldgen.features.NWFeature;
import com.kryptography.newworld.init.NWBlockEntityTypes;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWEntityTypes;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.init.data.NWData;
import com.kryptography.newworld.init.data.NWStats;
import com.kryptography.newworld.init.data.loot.NWLootModifiers;
import com.kryptography.newworld.init.data.woodset.FirBlockSet;
import com.kryptography.newworld.init.worldgen.structure.NWStructureTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.Regions;


@Mod(NewWorld.MOD_ID)

public class NewWorld {
    public static final String MOD_ID = "newworld";
    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public NewWorld() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        NWBlocks.BLOCKS.register(bus);
        NWItems.ITEMS.register(bus);
        NWBlockEntityTypes.BLOCK_ENTITIES.register(bus);
        NWEntityTypes.ENTITIES.register(bus);
        NWLootModifiers.LOOT_MODIFIERS.register(bus);
        NWFeature.FEATURES.register(bus);
        NWStructureTypes.STRUCTURE_TYPES.register(bus);
        NWStats.STATS.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(NWData::addCreative);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            NWData.vanillaInteractions();
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NWBlocks.FIR_SAPLING.getId(), NWBlocks.POTTED_FIR_SAPLING);
            Regions.register(new NWOverworldRegion(id("overworld"), 5));
        });
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent evt) {
        evt.enqueueWork(() -> {
            Sheets.addWoodType(FirBlockSet.FIR_WOOD_TYPE);
            EntityRenderers.register(NWEntityTypes.FIR_BOAT.get(), pContext -> new NWBoatRenderer(pContext, false));
            EntityRenderers.register(NWEntityTypes.FIR_CHEST_BOAT.get(), pContext -> new NWBoatRenderer(pContext, true));
        });
    }

}
