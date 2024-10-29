package com.kryptography.newworld.init.data;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Locale;

public class NWItemModels extends ItemModelProvider {
    public NWItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NewWorld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.basicItem(NWBlocks.FIR_DOOR.asItem());
        this.buttonItem(NWBlocks.FIR_BUTTON, NWBlocks.FIR_PLANKS);
        this.fenceItem(NWBlocks.FIR_FENCE, NWBlocks.FIR_PLANKS);

        this.basicItem(NWBlocks.FIR_SIGN.asItem());
        this.basicItem(NWBlocks.FIR_HANGING_SIGN.asItem());

        this.simpleItem(NWItems.FIR_BOAT);
        this.simpleItem(NWItems.FIR_CHEST_BOAT);

        this.wallItem(NWBlocks.LOAM_WALL, NWBlocks.LOAM);
        this.wallItem(NWBlocks.LOAM_BRICK_WALL, NWBlocks.LOAM_BRICKS);
        this.wallItem(NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM_TILES);

        this.generated(NWBlocks.FIR_SAPLING.getId().getPath(), prefix("block/" + NWBlocks.FIR_SAPLING.getId().getPath()));
        this.generated(NWBlocks.MOSS_SPROUTS.getId().getPath(), prefix("block/" + NWBlocks.MOSS_SPROUTS.getId().getPath()));
        this.basicItem(NWItems.MATTOCK_CRAFTING_TEMPLATE.get());
        this.basicItem(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get());
        this.basicItem(NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get());
        this.basicItem(NWItems.ILLAGER_TOME.get());

        this.handheldItem(NWItems.ANCIENT_MATTOCK);
    }

    
    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID,"item/" + item.getId().getPath()));
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
        return ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, name.toLowerCase(Locale.ROOT));
    }
}
