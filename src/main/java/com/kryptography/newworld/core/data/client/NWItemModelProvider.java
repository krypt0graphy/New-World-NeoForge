package com.kryptography.newworld.core.data.client;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.registry.NWBlocks;
import com.kryptography.newworld.core.registry.NWItems;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class NWItemModelProvider extends ItemModelProvider {
	public NWItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, NewWorld.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		this.simpleItem(NWItems.FIR_BOAT.getFirst());
		this.simpleItem(NWItems.FIR_BOAT.getSecond());
		this.simpleItem(NWItems.FIR_FURNACE_BOAT);
		this.simpleItem(NWItems.LARGE_FIR_BOAT);

		this.wallItem(NWBlocks.LOAM_WALL, NWBlocks.LOAM);
		this.wallItem(NWBlocks.LOAM_BRICK_WALL, NWBlocks.LOAM_BRICKS);
		this.wallItem(NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM_TILES);

		this.generated(NWBlocks.MOSS_SPROUTS.getId().getPath(), prefix("block/" + NWBlocks.MOSS_SPROUTS.getId().getPath()));
		this.basicItem(NWItems.MATTOCK_CRAFTING_TEMPLATE.get());
		this.basicItem(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get());
		this.basicItem(NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get());
		this.basicItem(NWItems.OMINOUS_TOME.get());

		this.handheldItem(NWItems.ANCIENT_MATTOCK);

		this.simpleBlockItem(NWBlocks.FIR_CABINET.get());
		this.simpleBlockItem(NWBlocks.TRIMMED_FIR_PLANKS.get());
		this.simpleBlockItem(NWBlocks.FIR_SEAT.get());

		//BLOCKBOX
		Set<Item> items = BuiltInRegistries.ITEM.stream().filter(i -> NewWorld.MOD_ID.equals(BuiltInRegistries.ITEM.getKey(i).getNamespace()))
				.collect(Collectors.toSet());
		palisadeModel(NWBlocks.FIR_PALISADE.asItem(), items);
		palisadeModel(NWBlocks.SPIKED_FIR_PALISADE.asItem(), items);
		palisadeModel(NWBlocks.STRIPPED_FIR_PALISADE.asItem(), items);
		palisadeModel(NWBlocks.STRIPPED_SPIKED_FIR_PALISADE.asItem(), items);
	}

	public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
		this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
				.texture("texture",  NewWorld.id(
						"block/" + baseBlock.getId().getPath()));
	}

	public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
		this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
				.texture("texture",  NewWorld.id(
						"block/" + baseBlock.getId().getPath()));
	}

	public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
		this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
				.texture("wall",  NewWorld.id(
						"block/" + baseBlock.getId().getPath()));
	}

	private ItemModelBuilder handheldItem(DeferredItem<?> item) {
		return withExistingParent(item.getId().getPath(),
				ResourceLocation.parse("item/handheld")).texture("layer0",
				NewWorld.id("item/" + item.getId().getPath()));
	}

	private ItemModelBuilder buildItem(String name, String parent, int emissivity, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		if (emissivity > 0) builder = builder.customLoader(ItemLayerModelBuilder::begin).emissive(emissivity, emissivity, 0).renderType("minecraft:translucent", 0).end();
		return builder;
	}

	private ItemModelBuilder generated(String name, ResourceLocation... layers) {
		return buildItem(name, "item/generated", 0, layers);
	}

	private ItemModelBuilder simpleItem(DeferredHolder<?, ?> item) {
		return generated(item.getId().getPath(), prefix("item/" + item.getId().getPath()));
	}

	public static ResourceLocation prefix(String name) {
		return NewWorld.id(name.toLowerCase(Locale.ROOT));
	}

	//FROM VECTOR WING, USED FOR BLOCKBOX INTEGRATION

	public void palisadeModel(Item item, Set<Item> items) {
		blockBasedModelFrontLight(item, "_post");
		items.remove(item);
	}

	public void blockBasedModelFrontLight(Item item, String suffix) {
		withExistingParent(itemName(item), resourceBlock(itemName(item) + suffix)).guiLight(BlockModel.GuiLight.FRONT);
	}

	private String itemName(Item item) {
		return BuiltInRegistries.ITEM.getKey(item).getPath();
	}

	public ResourceLocation resourceBlock(String path) {
		return ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, "block/" + path);
	}
}
