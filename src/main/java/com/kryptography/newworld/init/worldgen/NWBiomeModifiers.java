package com.kryptography.newworld.init.worldgen;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.worldgen.features.NWPlacedFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddFeaturesBiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class NWBiomeModifiers {
    public static final ResourceKey<BiomeModifier> FIR_TAIGA = registerKey("fir_taiga");
    public static final ResourceKey<BiomeModifier> FIR_MEADOW = registerKey("fir_meadow");
    public static final ResourceKey<BiomeModifier> LOAM_SNOW = registerKey("loam_snow");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        HolderSet<Biome> taiga = context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_TAIGA);

        context.register(FIR_TAIGA, new AddFeaturesBiomeModifier(
                taiga,
                HolderSet.direct(placedFeatures.getOrThrow(NWPlacedFeatures.TREES_FIR_SCARCE)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(FIR_MEADOW, new AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.MEADOW)),
                HolderSet.direct(placedFeatures.getOrThrow(NWPlacedFeatures.TREES_FIR_MEADOW)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, name));
    }
}
