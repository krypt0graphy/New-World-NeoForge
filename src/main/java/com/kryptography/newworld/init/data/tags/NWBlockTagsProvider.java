package com.kryptography.newworld.init.data.tags;

import com.kryptography.newworld.NewWorld;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import com.kryptography.newworld.init.NWBlocks;

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
                NWBlocks.FIR_SIGN.get(),
                NWBlocks.FIR_WALL_SIGN.get(),
                NWBlocks.FIR_HANGING_SIGN.get(),
                NWBlocks.FIR_WALL_HANGING_SIGN.get()
        ).addTag(
                NWBlockTags.FIR_LOGS
        );
        this.tag(BlockTags.LOGS_THAT_BURN).addTags(NWBlockTags.FIR_LOGS);
        this.tag(BlockTags.SAPLINGS).add(NWBlocks.FIR_SAPLING.get());
        this.tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(NWBlocks.FIR_LOG.get());
        this.tag(BlockTags.LEAVES).add(NWBlocks.FIR_LEAVES.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(NWBlocks.FIR_BUTTON.get());
        this.tag(BlockTags.WOODEN_DOORS).add(NWBlocks.FIR_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(NWBlocks.FIR_TRAPDOOR.get());
        this.tag(BlockTags.WOODEN_FENCES).add(NWBlocks.FIR_FENCE.get());
        this.tag(BlockTags.WOODEN_SLABS).add(NWBlocks.FIR_SLAB.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(NWBlocks.FIR_STAIRS.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(NWBlocks.FIR_PRESSURE_PLATE.get());
        this.tag(BlockTags.FENCE_GATES).add(NWBlocks.FIR_FENCE_GATE.get());

        this.tag(BlockTags.STANDING_SIGNS).add(NWBlocks.FIR_SIGN.get());
        this.tag(BlockTags.WALL_SIGNS).add(NWBlocks.FIR_WALL_SIGN.get());
        this.tag(BlockTags.CEILING_HANGING_SIGNS).add(NWBlocks.FIR_HANGING_SIGN.get());
        this.tag(BlockTags.WALL_HANGING_SIGNS).add(NWBlocks.FIR_WALL_HANGING_SIGN.get());

        this.tag(BlockTags.LEAVES).add(NWBlocks.FIR_LEAVES.get());

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

        //this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add( TOMBSTONE );
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                NWBlocks.LOAM.get(), NWBlocks.LOAM_STAIRS.get(), NWBlocks.LOAM_SLAB.get(), NWBlocks.LOAM_WALL.get(),
                NWBlocks.LOAM_BRICKS.get(), NWBlocks.LOAM_BRICK_STAIRS.get(), NWBlocks.LOAM_BRICK_SLAB.get(), NWBlocks.LOAM_BRICK_WALL.get(),
                NWBlocks.LOAM_TILES.get(), NWBlocks.LOAM_TILE_STAIRS.get(), NWBlocks.LOAM_TILE_SLAB.get(), NWBlocks.LOAM_TILE_WALL.get()

        );


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

        this.tag(NWBlockTags.MATTOCK_MINEABLE).addTags(
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
    }
}
