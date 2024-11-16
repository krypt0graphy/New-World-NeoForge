package com.kryptography.newworld.init.worldgen;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.worldgen.NWOverworldBiomeCreator;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class NWBiomes {

    public static final ResourceKey<Biome> WOODED_MEADOW = registerKey("wooded_meadow");

    private static ResourceKey<Biome> registerKey(String name)
    {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, name));
    }

    public static void bootstrap(BootstrapContext<Biome> context) {

        HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);
        register(context, WOODED_MEADOW, NWOverworldBiomeCreator.createWoodedMeadow(placedFeatureGetter, carverGetter));
    }

    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome)
    {
        context.register(key, biome);
    }

}
