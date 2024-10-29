package com.kryptography.newworld.init.data.tags;

import com.kryptography.newworld.NewWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class NWItemTags {
    public static final TagKey<Item> FIR_LOGS = itemTag("fir_logs");
    public static final TagKey<Item> ANCIENT_TOOL_MATERIALS = itemTag("ancient_tool_materials");
    public static final TagKey<Item> MATTOCK_PIECES = itemTag("mattock_piece");


    public static TagKey<Item> itemTag(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, name));
    }
}
