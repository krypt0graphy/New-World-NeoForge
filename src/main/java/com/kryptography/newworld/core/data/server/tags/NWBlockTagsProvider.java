package com.kryptography.newworld.core.data.server.tags;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.other.tags.CommonTags;
import com.kryptography.newworld.core.other.tags.NWBlockTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import com.kryptography.newworld.core.registry.NWBlocks;

import java.util.concurrent.CompletableFuture;

public class NWBlockTagsProvider extends BlockTagsProvider {

	public NWBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, NewWorld.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(
				NWBlocks.FIR_PLANKS.get(),
				NWBlocks.FIR_BUTTON.get(),
				NWBlocks.FIR_DOOR.get(),
				NWBlocks.FIR_TRAPDOOR.get(),
				NWBlocks.FIR_FENCE.get(),
				NWBlocks.FIR_SLAB.get(),
				NWBlocks.FIR_STAIRS.get(),
				NWBlocks.FIR_PRESSURE_PLATE.get(),
				NWBlocks.FIR_FENCE_GATE.get(),
				NWBlocks.FIR_SIGNS.getFirst().get(),
				NWBlocks.FIR_SIGNS.getSecond().get(),
				NWBlocks.FIR_HANGING_SIGNS.getFirst().get(),
				NWBlocks.FIR_HANGING_SIGNS.getSecond().get()
		).addTag(
				NWBlockTags.FIR_LOGS
		).addOptional(
				NWBlocks.FIR_CABINET.getId()
		).addOptional(
				NWBlocks.TRIMMED_FIR_PLANKS.getId()
		).addOptional(
				NWBlocks.FIR_BOOKSHELF.getId()
		);
		this.tag(BlockTags.LOGS_THAT_BURN).addTag(NWBlockTags.FIR_LOGS);
		this.tag(BlockTags.SAPLINGS).add(NWBlocks.FIR_SAPLING.get());
		this.tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(NWBlocks.FIR_LOG.get());
		this.tag(BlockTags.LEAVES).add(NWBlocks.FIR_LEAVES.get());
		this.tag(BlockTags.PLANKS).add(NWBlocks.FIR_PLANKS.get());
		this.tag(BlockTags.WOODEN_BUTTONS).add(NWBlocks.FIR_BUTTON.get());
		this.tag(BlockTags.WOODEN_DOORS).add(NWBlocks.FIR_DOOR.get());
		this.tag(BlockTags.WOODEN_TRAPDOORS).add(NWBlocks.FIR_TRAPDOOR.get());
		this.tag(BlockTags.WOODEN_FENCES).add(NWBlocks.FIR_FENCE.get());
		this.tag(BlockTags.WOODEN_SLABS).add(NWBlocks.FIR_SLAB.get());
		this.tag(BlockTags.WOODEN_STAIRS).add(NWBlocks.FIR_STAIRS.get());
		this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(NWBlocks.FIR_PRESSURE_PLATE.get());
		this.tag(BlockTags.FENCE_GATES).add(NWBlocks.FIR_FENCE_GATE.get());

		this.tag(BlockTags.STANDING_SIGNS).add(NWBlocks.FIR_SIGNS.getFirst().get());
		this.tag(BlockTags.WALL_SIGNS).add(NWBlocks.FIR_SIGNS.getSecond().get());
		this.tag(BlockTags.CEILING_HANGING_SIGNS).add(NWBlocks.FIR_HANGING_SIGNS.getFirst().get());
		this.tag(BlockTags.WALL_HANGING_SIGNS).add(NWBlocks.FIR_HANGING_SIGNS.getSecond().get());


		this.tag(NWBlockTags.FIR_LOGS).add(
				NWBlocks.FIR_LOG.get(),
				NWBlocks.STRIPPED_FIR_LOG.get(),
				NWBlocks.FIR_WOOD.get(),
				NWBlocks.STRIPPED_FIR_WOOD.get(),
				NWBlocks.STRIPPED_FIR_WOOD.get()
		);

		this.tag(BlockTags.FLOWER_POTS).add(
		  NWBlocks.POTTED_POINTED_DRIPSTONE.get(),
		  NWBlocks.POTTED_FIR_SAPLING.get()
		);

		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
				NWBlocks.TOMBSTONE.get(),
				NWBlocks.LOAM_BRICKS.get(), NWBlocks.LOAM_BRICK_STAIRS.get(), NWBlocks.LOAM_BRICK_SLAB.get(), NWBlocks.LOAM_BRICK_WALL.get(),
				NWBlocks.LOAM_TILES.get(), NWBlocks.LOAM_TILE_STAIRS.get(), NWBlocks.LOAM_TILE_SLAB.get(), NWBlocks.LOAM_TILE_WALL.get()
		);
		this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
				NWBlocks.LOAM.get(), NWBlocks.LOAM_STAIRS.get(), NWBlocks.LOAM_SLAB.get(), NWBlocks.LOAM_WALL.get()
		);

		this.tag(BlockTags.MINEABLE_WITH_HOE).add(NWBlocks.FIR_LEAVES.get());

		this.tag(BlockTags.BASE_STONE_OVERWORLD).add(NWBlocks.LOAM.get());

		this.tag(BlockTags.STAIRS).add(
				NWBlocks.LOAM_STAIRS.get(),
				NWBlocks.LOAM_BRICK_STAIRS.get(),
				NWBlocks.LOAM_TILE_STAIRS.get()
		);
		this.tag(BlockTags.SLABS).add(
				NWBlocks.LOAM_SLAB.get(),
				NWBlocks.LOAM_BRICK_SLAB.get(),
				NWBlocks.LOAM_TILE_SLAB.get()
		);
		this.tag(BlockTags.WALLS).add(
		  NWBlocks.LOAM_WALL.get(),
		  NWBlocks.LOAM_BRICK_WALL.get(),
		  NWBlocks.LOAM_TILE_WALL.get()
		);

		this.tag(BlockTags.REPLACEABLE).add(NWBlocks.MOSS_SPROUTS.get());

		this.tag(NWBlockTags.MINEABLE_ANCIENT_MATTOCK).addTags(
				BlockTags.MINEABLE_WITH_AXE,
				BlockTags.MINEABLE_WITH_PICKAXE,
				BlockTags.MINEABLE_WITH_SHOVEL,
				BlockTags.MINEABLE_WITH_HOE
		);

		this.tag(NWBlockTags.SMALL_BUSH_PLANTABLE).addTags(
				BlockTags.DIRT,
				BlockTags.LUSH_GROUND_REPLACEABLE
		).add(
				Blocks.MUD,
				Blocks.CLAY
		);

		this.tag(NWBlockTags.TOMBSTONE_REPLACEABLE)
				.addTags(
						BlockTags.CORAL_PLANTS,
						BlockTags.SMALL_FLOWERS,
						BlockTags.LEAVES,
						BlockTags.FIRE,
						BlockTags.CROPS
				).add(
						Blocks.SHORT_GRASS,
						Blocks.TALL_GRASS,
						Blocks.WATER,
						Blocks.SEAGRASS,
						Blocks.TALL_SEAGRASS,
						Blocks.CRIMSON_ROOTS,
						Blocks.WARPED_ROOTS,
						Blocks.HANGING_ROOTS
				);

		this.tag(CommonTags.STRIPPED_LOGS).add(NWBlocks.STRIPPED_FIR_LOG.get());
		this.tag(CommonTags.STRIPPED_WOODS).add(NWBlocks.STRIPPED_FIR_WOOD.get());
		this.tag(CommonTags.CONIFEROUS_LOGS).addTag(NWBlockTags.FIR_LOGS);
		this.tag(CommonTags.TRIMMED_PLANKS).addOptional(NWBlocks.TRIMMED_FIR_PLANKS.getId());
		this.tag(CommonTags.PALISADES).addOptional(NWBlocks.FIR_PALISADE.getId()).addOptional(NWBlocks.STRIPPED_FIR_PALISADE.getId());
		this.tag(CommonTags.SPIKED_PALISADES).addOptional(NWBlocks.SPIKED_FIR_PALISADE.getId()).addOptional(NWBlocks.STRIPPED_SPIKED_FIR_PALISADE.getId());
		this.tag(CommonTags.WOODEN_SEATS).addOptional(NWBlocks.FIR_SEAT.getId());

		this.tag(BlueprintBlockTags.WOODEN_BOOKSHELVES).add(NWBlocks.FIR_BOOKSHELF.get());
		this.tag(BlueprintBlockTags.WOODEN_CHISELED_BOOKSHELVES).add(NWBlocks.CHISELED_FIR_BOOKSHELF.get());
		this.tag(BlueprintBlockTags.WOODEN_LADDERS).add(NWBlocks.FIR_LADDER.get());
		this.tag(BlueprintBlockTags.WOODEN_BEEHIVES).add(NWBlocks.FIR_BEEHIVE.get());
		this.tag(BlueprintBlockTags.WOODEN_CHESTS).add(NWBlocks.FIR_CHEST.get());
		this.tag(BlueprintBlockTags.WOODEN_TRAPPED_CHESTS).add(NWBlocks.TRAPPED_FIR_CHEST.get());
		this.tag(BlueprintBlockTags.WOODEN_BOARDS).add(NWBlocks.FIR_BOARDS.get());
		this.tag(BlueprintBlockTags.LEAF_PILES).add(NWBlocks.FIR_LEAF_PILE.get());
	}
}
