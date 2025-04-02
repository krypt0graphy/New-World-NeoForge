package com.kryptography.newworld;

import com.kryptography.newworld.common.worldgen.NWFeature;
import com.kryptography.newworld.init.NWBlockEntityTypes;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWEntityTypes;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.common.data.providers.NWDataMapProvider;
import com.kryptography.newworld.init.data.NWData;
import com.kryptography.newworld.init.data.NWStats;
import com.kryptography.newworld.init.data.loot.NWLootModifiers;
import com.kryptography.newworld.init.worldgen.NWBiomePlacement;
import com.kryptography.newworld.init.worldgen.structure.NWStructureTypes;
import com.kryptography.newworld.integration.BBIntegration;
import com.kryptography.newworld.integration.FDIntegration;
import com.kryptography.newworld.integration.Mods;
import com.kryptography.newworld.integration.NMLIntegration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(NewWorld.MOD_ID)

public class NewWorld {
	public static final String MOD_ID = "newworld";
	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	public NewWorld(IEventBus bus) {
		NWBlocks.BLOCKS.register(bus);
		NWItems.ITEMS.register(bus);
		NWBlockEntityTypes.BLOCK_ENTITIES.register(bus);
		NWEntityTypes.ENTITIES.register(bus);
		NWLootModifiers.LOOT_MODIFIERS.register(bus);
		NWFeature.FEATURES.register(bus);
		NWStructureTypes.STRUCTURE_TYPES.register(bus);
		NWStats.STATS.register(bus);
		NWBiomePlacement.register();

		bus.addListener(this::commonSetup);
		bus.addListener(NWData::addCreative);
		bus.addListener(NWBlockEntityTypes::addBlockEntities);

		if (Mods.FARMERSDELIGHT.isLoaded()) {
			FDIntegration.register();
			bus.addListener(FDIntegration::addBlockEntities);
		}
		if (Mods.NOMANSLAND.isLoaded()) {
			NMLIntegration.register();
		}
		if (Mods.BLOCKBOX.isLoaded()) {
			BBIntegration.register();
		}
	}
	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			NWDataMapProvider.register();
			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(NWBlocks.FIR_SAPLING.getId(), NWBlocks.POTTED_FIR_SAPLING);
		});
	}
}