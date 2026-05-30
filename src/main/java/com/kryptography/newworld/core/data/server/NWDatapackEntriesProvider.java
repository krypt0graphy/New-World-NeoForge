package com.kryptography.newworld.core.data.server;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.registry.NWBiomeModifiers;
import com.kryptography.newworld.core.registry.NWBiomes;
import com.kryptography.newworld.core.registry.datapack.NWFeatures.NWConfiguredFeatures;
import com.kryptography.newworld.core.registry.datapack.NWFeatures.NWPlacedFeatures;
import com.kryptography.newworld.core.registry.NWStructureTypes.NWProcessorLists;
import com.kryptography.newworld.core.registry.NWStructureTypes.NWStructureSets;
import com.kryptography.newworld.core.registry.NWStructureTypes.NWStructures;
import com.kryptography.newworld.core.registry.NWStructureTypes.NWTemplatePools;
import com.kryptography.newworld.core.registry.datapack.NWBiomeSlices;
import com.kryptography.newworld.core.registry.datapack.NWPaintingVariants;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class NWDatapackEntriesProvider extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.PAINTING_VARIANT, NWPaintingVariants::bootstrap)
			.add(Registries.CONFIGURED_FEATURE, NWConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, NWPlacedFeatures::bootstrap)
			.add(Registries.BIOME, NWBiomes::bootstrap)
			.add(Registries.TEMPLATE_POOL, NWTemplatePools::bootstrap)
			.add(Registries.PROCESSOR_LIST, NWProcessorLists::bootstrap)
			.add(Registries.STRUCTURE, NWStructures::bootstrap)
			.add(Registries.STRUCTURE_SET, NWStructureSets::bootstrap)
			.add(BlueprintDataPackRegistries.MODDED_BIOME_SLICES, NWBiomeSlices::bootstrap)
			.add(Keys.BIOME_MODIFIERS, NWBiomeModifiers::bootstrap);

	public NWDatapackEntriesProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider, BUILDER, Set.of(NewWorld.MOD_ID));
	}
}
