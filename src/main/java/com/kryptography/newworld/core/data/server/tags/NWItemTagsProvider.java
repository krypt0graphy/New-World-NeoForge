package com.kryptography.newworld.core.data.server.tags;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.registry.NWBlocks;
import com.kryptography.newworld.core.registry.NWItems;
import com.kryptography.newworld.core.other.tags.CommonTags;
import com.kryptography.newworld.core.other.tags.NWBlockTags;
import com.kryptography.newworld.core.other.tags.NWItemTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
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
		this.tag(ItemTags.BOATS).add(NWItems.FIR_BOAT.getFirst().asItem());
		this.tag(ItemTags.CHEST_BOATS).add(NWItems.FIR_BOAT.getSecond().asItem());

		this.tag(ItemTags.BOOKSHELF_BOOKS).add(NWItems.OMINOUS_TOME.get());

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

		this.tag(ItemTags.SIGNS).add(NWBlocks.FIR_SIGNS.getFirst().asItem());

		this.tag(ItemTags.HANGING_SIGNS).add(NWBlocks.FIR_HANGING_SIGNS.getFirst().asItem());
		this.tag(NWItemTags.MATTOCK_PIECES).add(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get(), NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get());
		this.tag(NWItemTags.TOMBSTONE_MATERIALS).add(NWItems.OMINOUS_TOME.get(), NWBlocks.TOMBSTONE.get().asItem());
		
		this.tag(CommonTags.WOODEN_CABINET).addOptional(NWBlocks.FIR_CABINET.getId());

		this.tag(CommonTags.STRIPPED_LOGS_ITEM).add(NWBlocks.STRIPPED_FIR_LOG.get().asItem());
		this.tag(CommonTags.STRIPPED_WOODS_ITEM).add(NWBlocks.STRIPPED_FIR_WOOD.get().asItem());

		this.tag(CommonTags.PALISADES_ITEM).addOptional(NWBlocks.FIR_PALISADE.getId()).addOptional(NWBlocks.STRIPPED_FIR_PALISADE.getId());
		this.tag(CommonTags.SPIKED_PALISADES_ITEM).addOptional(NWBlocks.SPIKED_FIR_PALISADE.getId()).addOptional(NWBlocks.STRIPPED_SPIKED_FIR_PALISADE.getId());
		this.tag(CommonTags.WOODEN_SEATS_ITEM).addOptional(NWBlocks.FIR_SEAT.getId());
		this.tag(CommonTags.TRIMMED_PLANKS_ITEM).addOptional(NWBlocks.TRIMMED_FIR_PLANKS.getId());

		this.tag(BlueprintItemTags.WOODEN_BOOKSHELVES).add(NWBlocks.FIR_BOOKSHELF.get().asItem());
		this.tag(BlueprintItemTags.WOODEN_CHISELED_BOOKSHELVES).add(NWBlocks.CHISELED_FIR_BOOKSHELF.get().asItem());
		this.tag(BlueprintItemTags.WOODEN_LADDERS).add(NWBlocks.FIR_LADDER.get().asItem());
		this.tag(BlueprintItemTags.WOODEN_BEEHIVES).add(NWBlocks.FIR_BEEHIVE.get().asItem());
		this.tag(BlueprintItemTags.WOODEN_CHESTS).add(NWBlocks.FIR_CHEST.get().asItem());
		this.tag(BlueprintItemTags.WOODEN_TRAPPED_CHESTS).add(NWBlocks.TRAPPED_FIR_CHEST.get().asItem());
		this.tag(BlueprintItemTags.WOODEN_BOARDS).add(NWBlocks.FIR_BOARDS.get().asItem());
		this.tag(BlueprintItemTags.LEAF_PILES).add(NWBlocks.FIR_LEAF_PILE.get().asItem());
		this.tag(BlueprintItemTags.FURNACE_BOATS).add(NWItems.FIR_FURNACE_BOAT.get());
		this.tag(BlueprintItemTags.LARGE_BOATS).add(NWItems.LARGE_FIR_BOAT.get());
	}
}
