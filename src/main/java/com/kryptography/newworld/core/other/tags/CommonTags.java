package com.kryptography.newworld.core.other.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class  CommonTags {

	public static final TagKey<Item> WOODEN_CABINET = fdItemTag("cabinets/wooden");
	public static final TagKey<Item> STRIPPED_LOGS_ITEM = commonItemTag("stripped_logs");
	public static final TagKey<Item> STRIPPED_WOODS_ITEM = commonItemTag("stripped_woods");
	public static final TagKey<Item> TRIMMED_PLANKS_ITEM = nmlItemTag("trimmed_planks");
	public static final TagKey<Item> PALISADES_ITEM = bbItemTag("palisades");
	public static final TagKey<Item> SPIKED_PALISADES_ITEM = bbItemTag("spiked_palisades");
	public static final TagKey<Item> WOODEN_SEATS_ITEM = bbItemTag("wooden_seats");

	public static final TagKey<Block> CONIFEROUS_LOGS = nmlBlockTag("coniferous_logs");
	public static final TagKey<Block> TRIMMED_PLANKS = nmlBlockTag("trimmed_planks");
	public static final TagKey<Block> STRIPPED_WOODS = commonBlockTag("stripped_woods");
	public static final TagKey<Block> STRIPPED_LOGS = commonBlockTag("stripped_logs");
	public static final TagKey<Block> PALISADES = bbBlockTag("palisades");
	public static final TagKey<Block> SPIKED_PALISADES = bbBlockTag("spiked_palisades");
	public static final TagKey<Block> WOODEN_SEATS = bbBlockTag("wooden_seats");

	private static TagKey<Item> fdItemTag(String path) {
		return ItemTags.create(ResourceLocation.fromNamespaceAndPath("farmersdelight", path));
	}
	private static TagKey<Block> fdBlockTag(String path) {
		return BlockTags.create(ResourceLocation.fromNamespaceAndPath("farmersdelight", path));
	}
	private static TagKey<Item> bbItemTag(String path) {
		return ItemTags.create(ResourceLocation.fromNamespaceAndPath("blockbox", path));
	}
	private static TagKey<Block> bbBlockTag(String path) {
		return BlockTags.create(ResourceLocation.fromNamespaceAndPath("blockbox", path));
	}
	private static TagKey<Item> nmlItemTag(String path) {
		return ItemTags.create(ResourceLocation.fromNamespaceAndPath("nomansland", path));
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
