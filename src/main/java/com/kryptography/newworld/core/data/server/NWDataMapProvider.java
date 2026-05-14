package com.kryptography.newworld.core.data.server;

import com.kryptography.newworld.core.registry.NWBlocks;
import com.teamabnormals.blueprint.core.data.server.BlueprintDataMapProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class NWDataMapProvider extends BlueprintDataMapProvider {
	public NWDataMapProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void gather(HolderLookup.Provider provider) {
		this.builder(NeoForgeDataMaps.COMPOSTABLES).add(NWBlocks.MOSS_SPROUTS.getId(), new Compostable(0.3f), false);
	}
}
