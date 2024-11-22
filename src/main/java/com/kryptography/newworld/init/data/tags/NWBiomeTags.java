package com.kryptography.newworld.init.data.tags;

import com.kryptography.newworld.NewWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class NWBiomeTags {

    public static final TagKey<Biome> HAS_BURIED_BUNKER= biomeTag("has_structure/buried_bunker");

    public static TagKey<Biome> biomeTag(String name) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, name));
    }
}
