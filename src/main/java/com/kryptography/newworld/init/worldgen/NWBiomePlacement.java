package com.kryptography.newworld.init.worldgen;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import net.minecraft.world.level.biome.Biomes;

public class NWBiomePlacement {

    public static void register() {
        BiomePlacement.replaceOverworld(Biomes.MEADOW, NWBiomes.WOODED_MEADOW, 0.2);
    }

}
