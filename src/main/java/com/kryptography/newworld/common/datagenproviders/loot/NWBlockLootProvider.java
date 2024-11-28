package com.kryptography.newworld.common.datagenproviders.loot;

import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class NWBlockLootProvider extends BlockLootSubProvider {


    public NWBlockLootProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        this.dropSelf(NWBlocks.FIR_LOG.get());
        this.dropSelf(NWBlocks.FIR_WOOD.get());
        this.dropSelf(NWBlocks.STRIPPED_FIR_LOG.get());
        this.dropSelf(NWBlocks.STRIPPED_FIR_WOOD.get());
        this.dropSelf(NWBlocks.FIR_PLANKS.get());
        this.dropSelf(NWBlocks.FIR_SAPLING.get());
        this.dropPottedContents(NWBlocks.POTTED_FIR_SAPLING.get());
        this.add(NWBlocks.FIR_SLAB.get(), createSlabItemTable(NWBlocks.FIR_SLAB.get()));
        this.dropSelf(NWBlocks.FIR_STAIRS.get());
        this.add(NWBlocks.FIR_DOOR.get(), createDoorTable(NWBlocks.FIR_DOOR.get()));
        this.dropSelf(NWBlocks.FIR_TRAPDOOR.get());
        this.dropSelf(NWBlocks.FIR_PRESSURE_PLATE.get());
        this.dropSelf(NWBlocks.FIR_BUTTON.get());
        this.dropSelf(NWBlocks.FIR_FENCE.get());
        this.dropSelf(NWBlocks.FIR_FENCE_GATE.get());
        this.dropSelf(NWBlocks.FIR_SIGN.get());
        this.dropOther(NWBlocks.FIR_WALL_SIGN.get(), NWBlocks.FIR_SIGN.get());
        this.dropSelf(NWBlocks.FIR_HANGING_SIGN.get());
        this.dropOther(NWBlocks.FIR_WALL_HANGING_SIGN.get(), NWBlocks.FIR_HANGING_SIGN.get());

        this.add(NWBlocks.FIR_LEAVES.get(), (block) -> createLeavesDrops(block, NWBlocks.FIR_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(NWBlocks.LOAM.get());
        this.dropSelf(NWBlocks.LOAM_STAIRS.get());
        this.add(NWBlocks.LOAM_SLAB.get(), createSlabItemTable(NWBlocks.LOAM_SLAB.get()));
        this.dropSelf(NWBlocks.LOAM_WALL.get());
        this.dropSelf(NWBlocks.LOAM_BRICKS.get());
        this.dropSelf(NWBlocks.LOAM_BRICK_STAIRS.get());
        this.add(NWBlocks.LOAM_BRICK_SLAB.get(), createSlabItemTable(NWBlocks.LOAM_BRICK_SLAB.get()));
        this.dropSelf(NWBlocks.LOAM_BRICK_WALL.get());
        this.dropSelf(NWBlocks.LOAM_TILES.get());
        this.dropSelf(NWBlocks.LOAM_TILE_STAIRS.get());
        this.add(NWBlocks.LOAM_TILE_SLAB.get(), createSlabItemTable(NWBlocks.LOAM_TILE_SLAB.get()));
        this.dropSelf(NWBlocks.LOAM_TILE_WALL.get());

        this.dropPottedContents(NWBlocks.POTTED_POINTED_DRIPSTONE.get());

        this.dropSelf(NWBlocks.TOMBSTONE.get());
    }
    protected Iterable<Block> getKnownBlocks() {
        return NWBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
