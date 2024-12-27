package com.kryptography.newworld.common.datagenproviders;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWItems;
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

public class NWItemModelProvider extends ItemModelProvider {
    public NWItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NewWorld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(NWBlocks.FIR_DOOR);
        generated(NWBlocks.FIR_DOOR.getId().getPath(), prefix("item/" + NWBlocks.FIR_DOOR.getId().getPath()));
        woodenButton(NWBlocks.FIR_BUTTON, "fir");
        fenceItem(NWBlocks.FIR_FENCE, "fir");

        simpleItem(NWBlocks.FIR_SIGN);
        simpleItem(NWBlocks.FIR_HANGING_SIGN);

        simpleItem(NWItems.FIR_BOAT);
        simpleItem(NWItems.FIR_CHEST_BOAT);

        wallItem(NWBlocks.LOAM_WALL, "loam");
        wallItem(NWBlocks.LOAM_BRICK_WALL, "loam_bricks");
        wallItem(NWBlocks.LOAM_TILE_WALL, "loam_tiles");

        generated(NWBlocks.FIR_SAPLING.getId().getPath(), prefix("block/" + NWBlocks.FIR_SAPLING.getId().getPath()));
        generated(NWBlocks.MOSS_SPROUTS.getId().getPath(), prefix("block/" + NWBlocks.MOSS_SPROUTS.getId().getPath()));
        simpleItem(NWItems.MATTOCK_CRAFTING_TEMPLATE);
        simpleItem(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD);
        simpleItem(NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT);
        simpleItem(NWItems.ILLAGER_TOME);

        handheldItem("ancient_mattock", prefix("item/ancient_mattock"));
    }


    private void woodenButton(DeferredBlock<?> button, String wood) {
        getBuilder(BuiltInRegistries.BLOCK.getKey(button.get()).getPath())
                .parent(getExistingFile(mcLoc("block/button_inventory")))
                .texture("texture", "block/" + wood+ "_planks");
    }

    public void fenceItem(DeferredBlock<?> fence, String wood) {
        getBuilder(BuiltInRegistries.BLOCK.getKey(fence.get()).getPath())
                .parent(getExistingFile(mcLoc("block/fence_inventory")))
                .texture("texture", "block/" + wood + "_planks");
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  NewWorld.id(
                        "block/" + baseBlock.getId().getPath()));
    }
    public void wallItem(DeferredBlock<?> block, String variant) {
        getBuilder(BuiltInRegistries.BLOCK.getKey(block.get()).getPath())
                .parent(getExistingFile(mcLoc("block/wall_inventory")))
                .texture("wall", "block/" + variant );
    }

    private ItemModelBuilder handheldItem(String name, ResourceLocation... layers) {
        return buildItem(name, "item/handheld", 0, layers);
    }


    private ItemModelBuilder simpleItem(DeferredHolder<?, ?> item) {
        return generated(item.getId().getPath(), prefix("item/" + item.getId().getPath()));
    }

    public static ResourceLocation prefix(String name) {
        return NewWorld.id(name.toLowerCase(Locale.ROOT));
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
}
