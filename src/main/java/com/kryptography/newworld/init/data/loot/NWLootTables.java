package com.kryptography.newworld.init.data.loot;

import com.kryptography.newworld.NewWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class NWLootTables {

    public static final ResourceKey<LootTable> BUNKER_BARREL = registerKey("chests/bunker_barrel");
    public static final ResourceKey<LootTable> BUNKER_CACHE = registerKey("chests/bunker_cache");

    public static ResourceKey<LootTable> registerKey(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, NewWorld.id( name));
    }
}
