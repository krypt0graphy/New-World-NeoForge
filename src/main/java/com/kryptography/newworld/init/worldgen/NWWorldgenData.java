package com.kryptography.newworld.init.worldgen;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.worldgen.features.NWConfiguredFeatures;
import com.kryptography.newworld.init.worldgen.features.NWPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class NWWorldgenData extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, NWConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, NWPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, NWBiomeModifiers::bootstrap);

    public NWWorldgenData(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(NewWorld.MOD_ID));
    }
}
