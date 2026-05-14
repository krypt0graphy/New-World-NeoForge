package com.kryptography.newworld.core.other;

import com.kryptography.newworld.core.NewWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class NWLootTables {
	public static final ResourceKey<LootTable> BUNKER_BARREL = create("chests/bunker_barrel");
	public static final ResourceKey<LootTable> BUNKER_CACHE = create("chests/bunker_cache");

	private static ResourceKey<LootTable> create(String name) {
		return ResourceKey.create(Registries.LOOT_TABLE, NewWorld.id(name));
	}
}
