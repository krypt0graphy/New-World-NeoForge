package com.kryptography.newworld.common.data.providers.loot;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.integration.BBIntegration;
import com.kryptography.newworld.integration.FDIntegration;
import com.kryptography.newworld.integration.NMLIntegration;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;
import java.util.stream.Collectors;

import static com.kryptography.newworld.init.NWBlocks.*;

public class NWBlockLootProvider extends BlockLootSubProvider {
	public NWBlockLootProvider(HolderLookup.Provider registries) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
	}

	public final float[] FIR_LEAVES_SAPLING_CHANCES = new float[]{1/16F, 1/12F, 1/10F, 1/6F };

	@Override
	protected void generate() {
		this.dropSelf(FIR_BUTTON.get());

		this.add(FIR_DOOR.get(), createDoorTable(FIR_DOOR.get()));
		this.dropSelf(FIR_FENCE.get());
		this.dropSelf(FIR_FENCE_GATE.get());
		this.dropSelf(FIR_HANGING_SIGN.get());
		this.add(FIR_LEAVES.get(), createLeavesDrops(FIR_LEAVES.get(), FIR_SAPLING.get(), FIR_LEAVES_SAPLING_CHANCES));
		this.dropSelf(FIR_LOG.get());
		this.dropSelf(FIR_PLANKS.get());
		this.dropSelf(FIR_PRESSURE_PLATE.get());
		this.dropSelf(FIR_SAPLING.get());
		this.dropSelf(FIR_SIGN.get());
		this.slabDrops(FIR_SLAB.get());
		this.dropSelf(FIR_STAIRS.get());
		this.dropSelf(FIR_TRAPDOOR.get());
		this.dropSelf(FIR_WOOD.get());
		this.dropSelf(LOAM.get());
		this.slabDrops(LOAM_BRICK_SLAB.get());
		this.dropSelf(LOAM_BRICK_STAIRS.get());
		this.dropSelf(LOAM_BRICK_WALL.get());
		this.dropSelf(LOAM_BRICKS.get());
		this.slabDrops(LOAM_SLAB.get());
		this.dropSelf(LOAM_STAIRS.get());
		this.slabDrops(LOAM_TILE_SLAB.get());
		this.dropSelf(LOAM_TILE_STAIRS.get());
		this.dropSelf(LOAM_TILE_WALL.get());
		this.dropSelf(LOAM_TILES.get());
		this.dropSelf(LOAM_WALL.get());
		this.add(MOSS_SPROUTS.get(), createShearsOnlyDrop(MOSS_SPROUTS.get()));
		this.dropPottedContents(POTTED_FIR_SAPLING.get());
		this.dropPottedContents(POTTED_POINTED_DRIPSTONE.get());
		this.dropSelf(STRIPPED_FIR_LOG.get());
		this.dropSelf(STRIPPED_FIR_WOOD.get());
		this.dropOther(FIR_WALL_SIGN.get(), FIR_SIGN.get().asItem());
		this.dropOther(FIR_WALL_HANGING_SIGN.get(), FIR_HANGING_SIGN.get().asItem());

		this.dropSelf(TOMBSTONE.get());

		this.add(NMLIntegration.FIR_BOOKSHELF.get(), createSingleItemTableWithSilkTouch(NMLIntegration.FIR_BOOKSHELF.get(), Items.BOOK, ConstantValue.exactly(3.0F)));
		this.dropSelf(NMLIntegration.TRIMMED_FIR_PLANKS.get());
		this.add(FDIntegration.FIR_CABINET.get(), createNameableBlockEntityTable(FDIntegration.FIR_CABINET.get()));
		this.dropSelf(BBIntegration.STRIPPED_SPIKED_FIR_PALISADE.get());
		this.dropSelf(BBIntegration.STRIPPED_FIR_PALISADE.get());
		this.dropSelf(BBIntegration.SPIKED_FIR_PALISADE.get());
		this.dropSelf(BBIntegration.FIR_SEAT.get());
		this.dropSelf(BBIntegration.FIR_PALISADE.get());
	}

	public void slabDrops(Block block) {
		this.add(block, this.createSlabItemTable(block));
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return BuiltInRegistries.BLOCK.stream().filter(block -> NewWorld.MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace())).collect(Collectors.toList());
	}
}