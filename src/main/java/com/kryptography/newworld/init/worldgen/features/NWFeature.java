package com.kryptography.newworld.init.worldgen.features;


import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.worldgen.features.BurialSiteFeature;
import com.kryptography.newworld.common.worldgen.features.FallenLogFeature;
import com.kryptography.newworld.common.worldgen.features.LoamSnowFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class NWFeature {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, NewWorld.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FALLEN_FIR_LOG = FEATURES.register("fallen_fir_log", () -> new FallenLogFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LOAM_SNOW = FEATURES.register("loam_snow", () -> new LoamSnowFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BURIAL_SITE = FEATURES.register("burial_site", () -> new BurialSiteFeature(NoneFeatureConfiguration.CODEC));
}
