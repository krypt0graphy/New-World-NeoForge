package com.kryptography.newworld.init.data.loot;

import com.google.common.collect.Sets;
import com.kryptography.newworld.NewWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Set;

public class NWLootTables {

    private static final Set<ResourceLocation> NW_LOOT_TABLES = Sets.newHashSet();

    public static final ResourceLocation BUNKER_BARREL = register("chests/bunker_barrel");
    public static final ResourceLocation BUNKER_CACHE = register("chests/bunker_cache");

    private static ResourceLocation register(String id) {
        return register(NewWorld.id(id));
    }

    private static ResourceLocation register(ResourceLocation id) {
        if (NW_LOOT_TABLES.add(id)) {
            return id;
        } else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }
}