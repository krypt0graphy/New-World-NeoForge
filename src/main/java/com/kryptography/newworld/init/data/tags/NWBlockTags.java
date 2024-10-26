package com.kryptography.newworld.init.data.tags;

import com.kryptography.newworld.NewWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class NWBlockTags {
    public static final TagKey<Block> FIR_LOGS = blockTag("fir_logs");
    public static final TagKey<Block> MATTOCK_MINEABLE = blockTag("mattock_mineable");
    public static final TagKey<Block> SMALL_BUSH_PLANTABLE = blockTag("small_bush_plantable");
    public static final TagKey<Block> TOMBSTONE_REPLACEABLE = blockTag("tombstone_replaceable");


    public static TagKey<Block> blockTag(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, name));
    }
}
