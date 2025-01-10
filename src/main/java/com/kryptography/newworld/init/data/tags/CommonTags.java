package com.kryptography.newworld.init.data.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CommonTags {

    public static final TagKey<Item> WOODEN_CABINET = fdItemTag("cabinets/wooden");
    public static final TagKey<Block> BOOKSHELVES = commonBlockTag("bookshelves");
    public static final TagKey<Block> CONIFEROUS_LOGS = nmlBlockTag("coniferous_logs");
    public static final TagKey<Block> TRIMMED_PLANKS = nmlBlockTag("trimmed_planks");

    private static TagKey<Item> fdItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("farmersdelight", path));
    }

    private static TagKey<Block> nmlBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("nomansland", path));
    }
    private static TagKey<Item> commonItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }
    private static TagKey<Block> commonBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }
}
