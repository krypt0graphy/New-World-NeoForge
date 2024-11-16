package com.kryptography.newworld.common.datagens.tags;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.init.data.tags.NWBlockTags;
import com.kryptography.newworld.init.data.tags.NWItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class NWItemTagsProvider extends ItemTagsProvider {


    public NWItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, NewWorld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.copy(NWBlockTags.FIR_LOGS, NWItemTags.FIR_LOGS);
        this.tag(NWItemTags.ANCIENT_TOOL_MATERIALS).add(Items.FLINT);
        this.tag(ItemTags.LOGS_THAT_BURN).addTag(NWItemTags.FIR_LOGS);
        this.tag(ItemTags.PLANKS).add(NWBlocks.FIR_PLANKS.get().asItem());
        this.tag(ItemTags.SAPLINGS).add(NWBlocks.FIR_SAPLING.get().asItem());
        this.tag(ItemTags.BOATS).add(NWItems.FIR_BOAT.get());
        this.tag(ItemTags.CHEST_BOATS).add(NWItems.FIR_CHEST_BOAT.get());

        this.tag(ItemTags.BOOKSHELF_BOOKS).add(NWItems.ILLAGER_TOME.get());

        this.tag(ItemTags.DURABILITY_ENCHANTABLE).add(NWItems.ANCIENT_MATTOCK.get());
        this.tag(ItemTags.VANISHING_ENCHANTABLE).add(NWItems.ANCIENT_MATTOCK.get());

        this.tag(ItemTags.LEAVES).add(NWBlocks.FIR_LEAVES.get().asItem());
        this.tag(ItemTags.WOODEN_BUTTONS).add(NWBlocks.FIR_BUTTON.get().asItem());
        this.tag(ItemTags.WOODEN_DOORS).add(NWBlocks.FIR_DOOR.get().asItem());
        this.tag(ItemTags.WOODEN_TRAPDOORS).add(NWBlocks.FIR_TRAPDOOR.get().asItem());
        this.tag(ItemTags.WOODEN_FENCES).add(NWBlocks.FIR_FENCE.get().asItem());
        this.tag(ItemTags.WOODEN_SLABS).add(NWBlocks.FIR_SLAB.get().asItem());
        this.tag(ItemTags.WOODEN_STAIRS).add(NWBlocks.FIR_STAIRS.get().asItem());
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(NWBlocks.FIR_PRESSURE_PLATE.get().asItem());
        this.tag(ItemTags.FENCE_GATES).add(NWBlocks.FIR_FENCE_GATE.get().asItem());

        this.tag(ItemTags.SIGNS).add(NWBlocks.FIR_SIGN.get().asItem());

        this.tag(ItemTags.HANGING_SIGNS).add(NWBlocks.FIR_HANGING_SIGN.get().asItem());
        this.tag(NWItemTags.MATTOCK_PIECES).add(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get(), NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get());
    }
}
