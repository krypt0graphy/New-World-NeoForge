package com.kryptography.newworld.common.worldgen;

import com.kryptography.newworld.init.worldgen.features.NWPlacedFeatures;
import com.kryptography.newworld.init.worldgen.structure.NWProcessorsList;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.Nullable;

public class NWOverworldBiomeCreator {

    protected static int getSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = Mth.clamp(f, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }
    private static Biome biome(boolean hasPrecipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return biome(hasPrecipitation, temperature, downfall, 4159204, spawnBuilder, biomeBuilder, music);
    }
    private static Biome biome(boolean hasPrecipitation, float temperature, float downfall, int waterColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).hasPrecipitation(hasPrecipitation).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder pGenerationSettings) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(pGenerationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(pGenerationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(pGenerationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(pGenerationSettings);
        BiomeDefaultFeatures.addDefaultSprings(pGenerationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(pGenerationSettings);
    }

    public static Biome createWoodedMeadow(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {


        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 2));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 2, 6));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 2, 2, 4));
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder featureBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);

        globalOverworldGeneration(featureBuilder);

        addFeature(featureBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, NWPlacedFeatures.TREES_FIR);
        addFeature(featureBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, NWPlacedFeatures.GLOW_LICHEN_WOODED_MEADOW);
        addFeature(featureBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, NWPlacedFeatures.PATCH_BERRY_WOODED_MEADOW);
        addFeature(featureBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, NWPlacedFeatures.PATCH_FERN_WOODED_MEADOW );
        addFeature(featureBuilder, GenerationStep.Decoration.LOCAL_MODIFICATIONS, MiscOverworldPlacements.FOREST_ROCK);
        addFeature(featureBuilder, GenerationStep.Decoration.LOCAL_MODIFICATIONS, NWPlacedFeatures.FALLEN_FIR_LOG);

        BiomeDefaultFeatures.addFerns(featureBuilder);
        BiomeDefaultFeatures.addForestFlowers(featureBuilder);
        BiomeDefaultFeatures.addPlainGrass(featureBuilder);
        BiomeDefaultFeatures.addDefaultOres(featureBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(featureBuilder);
        BiomeDefaultFeatures.addMeadowVegetation(featureBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(featureBuilder);
        BiomeDefaultFeatures.addInfestedStone(featureBuilder);


        Music musicSound = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_MEADOW);

        return biome(true, 0.5F, 0.8F, 937679, spawnBuilder, featureBuilder, musicSound);
    }

    private static void addFeature(BiomeGenerationSettings.Builder builder, GenerationStep.Decoration step, ResourceKey<PlacedFeature> feature)
    {
        builder.addFeature(step, feature);
    }

}
