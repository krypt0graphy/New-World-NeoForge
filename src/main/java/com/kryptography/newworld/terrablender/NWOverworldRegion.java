//package com.kryptography.newworld.terrablender;
//
//import com.kryptography.newworld.init.worldgen.NWBiomes;
//import com.mojang.datafixers.util.Pair;
//import net.minecraft.core.Registry;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.biome.Biomes;
//import net.minecraft.world.level.biome.Climate;
//import terrablender.api.Region;
//import terrablender.api.RegionType;
//
//import java.util.function.Consumer;
//
//public class NWOverworldRegion extends Region {
//    public NWOverworldRegion(ResourceLocation name, int weight) {
//        super(name, RegionType.OVERWORLD, weight);
//    }
//
//    @Override
//    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
////        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
////        new ParameterUtils.ParameterPointListBuilder()
////                .temperature(Temperature.COOL, Temperature.NEUTRAL)
////                .humidity(Humidity.ARID, Humidity.DRY, Humidity.NEUTRAL, Humidity.WET)
////                .continentalness(Continentalness.FAR_INLAND)
////                .erosion(Erosion.EROSION_2, Erosion.EROSION_3)
////                .depth(Depth.SURFACE, Depth.FLOOR)
////                .weirdness(Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_NORMAL_ASCENDING)
////                .build().forEach(point -> builder.add(point, NWBiomes.WOODED_MEADOW));
////        builder.build().forEach(mapper);
//        addModifiedVanillaOverworldBiomes(mapper, (builder -> {this.addBiomeSimilar(mapper, Biomes.MEADOW, NWBiomes.WOODED_MEADOW);}));
//    }
//}
