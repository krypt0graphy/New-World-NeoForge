package com.kryptography.newworld.core.data.client;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.registry.NWBlocks;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.kryptography.newworld.core.registry.NWBlocks.*;

public class NWBlockStateProvider extends BlueprintBlockStateProvider {
	public NWBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, NewWorld.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.baseBlocks(FIR_PLANKS, FIR_STAIRS, FIR_SLAB);
		this.logBlocks(FIR_LOG, FIR_WOOD);
		this.logBlocks(STRIPPED_FIR_LOG, STRIPPED_FIR_WOOD);
		this.fenceBlock(FIR_PLANKS.get(), FIR_FENCE.get());
		this.fenceGateBlock(FIR_PLANKS.get(), FIR_FENCE_GATE.get());
		this.doorBlocks(FIR_DOOR.get(), FIR_TRAPDOOR.get());
		this.buttonBlock(FIR_PLANKS.get(), FIR_BUTTON.get());
		this.pressurePlateBlock(FIR_PLANKS.get(), FIR_PRESSURE_PLATE.get());
		this.signBlocks(FIR_PLANKS.get(), FIR_SIGNS.getFirst().get(), FIR_SIGNS.getSecond().get());
		this.hangingSignBlocks(STRIPPED_FIR_LOG, FIR_HANGING_SIGNS.getFirst(), FIR_HANGING_SIGNS.getSecond());

		this.woodworksBlocks(FIR_PLANKS, FIR_BOARDS, FIR_LADDER, FIR_BOOKSHELF, FIR_BEEHIVE, FIR_CHEST, TRAPPED_FIR_CHEST);
		this.leavesBlock(FIR_LEAVES);
		this.leafPileBlock(FIR_LEAVES, FIR_LEAF_PILE);
		this.crossBlockWithPotCutout(FIR_SAPLING, POTTED_FIR_SAPLING);
		this.simpleBlock(NWBlocks.POTTED_POINTED_DRIPSTONE.get(), models().singleTexture("potted_pointed_dripstone", ResourceLocation.withDefaultNamespace("block/flower_pot_cross"), "plant", blockTexture(Blocks.POINTED_DRIPSTONE).withSuffix("_up_tip")).renderType("cutout"));

		this.chiseledBookshelfBlock(CHISELED_FIR_BOOKSHELF, ALTERNATE_BOOKSHELF_POSITIONS);

		this.block(NWBlocks.LOAM.get());
		this.block(NWBlocks.LOAM_BRICKS.get());
		this.block(NWBlocks.LOAM_TILES.get());
		this.stairsBlock(NWBlocks.LOAM_STAIRS.get(), blockTexture(NWBlocks.LOAM.get()));
		this.stairsBlock(NWBlocks.LOAM_BRICK_STAIRS.get(), blockTexture(NWBlocks.LOAM_BRICKS.get()));
		this.stairsBlock(NWBlocks.LOAM_TILE_STAIRS.get(), blockTexture(NWBlocks.LOAM_TILES.get()));
		this.slabBlock(NWBlocks.LOAM_SLAB.get(), blockTexture(NWBlocks.LOAM.get()), blockTexture(NWBlocks.LOAM.get()));
		this.slabBlock(NWBlocks.LOAM_BRICK_SLAB.get(), blockTexture(NWBlocks.LOAM_BRICKS.get()), blockTexture(NWBlocks.LOAM_BRICKS.get()));
		this.slabBlock(NWBlocks.LOAM_TILE_SLAB.get(), blockTexture(NWBlocks.LOAM_TILES.get()), blockTexture(NWBlocks.LOAM_TILES.get()));
		this.wallBlock(NWBlocks.LOAM_WALL.get(), blockTexture(NWBlocks.LOAM.get()));
		this.wallBlock(NWBlocks.LOAM_BRICK_WALL.get(), blockTexture(NWBlocks.LOAM_BRICKS.get()));
		this.wallBlock(NWBlocks.LOAM_TILE_WALL.get(), blockTexture(NWBlocks.LOAM_TILES.get()));

		blockItem(NWBlocks.LOAM_STAIRS);
		blockItem(NWBlocks.LOAM_BRICK_STAIRS);
		blockItem(NWBlocks.LOAM_TILE_STAIRS);
		blockItem(NWBlocks.LOAM_SLAB);
		blockItem(NWBlocks.LOAM_BRICK_SLAB);
		blockItem(NWBlocks.LOAM_TILE_SLAB);


		mossSproutBlock(NWBlocks.MOSS_SPROUTS.get());
	}

	private void mossSproutBlock(Block moss) {
		this.simpleBlock(NWBlocks.MOSS_SPROUTS.get(), models().withExistingParent(NWBlocks.MOSS_SPROUTS.getRegisteredName(), "block/tinted_cross").renderType("cutout").texture("cross", blockTexture(NWBlocks.MOSS_SPROUTS.get())));
	}

	public void crossBlockWithPotCutout(DeferredHolder<Block, ?> cross, DeferredHolder<Block, ?> flowerPot) {
		this.simpleBlock(cross.get(), models().cross(name(cross.get()), blockTexture(cross.get())).renderType("minecraft:cutout"));
		this.generatedItem(cross.get(), "block");
		this.simpleBlock(flowerPot.get(), models().singleTexture(name(flowerPot.get()), ResourceLocation.withDefaultNamespace("block/flower_pot_cross"), "plant", blockTexture(cross.get())).renderType("minecraft:cutout"));
	}

}