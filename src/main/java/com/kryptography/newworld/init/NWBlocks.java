package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.blocks.*;
import com.kryptography.newworld.init.data.woodset.Woodset;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NWBlocks {

    public static final BlockBehaviour.Properties FIR_LOG_PROPS = logProperties(MapColor.WOOD, MapColor.DEEPSLATE).strength(2.0F).sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties FIR_STRIPPED_PROPS = logProperties(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties FIR_WOOD_PROPS = logProperties(MapColor.DEEPSLATE).strength(2.0F).sound(SoundType.WOOD);

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NewWorld.MOD_ID);

    public static final DeferredBlock<RotatedPillarBlock> FIR_LOG = register("fir_log", () -> new FirLogBlock(FIR_LOG_PROPS));
    public static final DeferredBlock<RotatedPillarBlock> FIR_WOOD = register("fir_wood", () -> new FirLogBlock(FIR_WOOD_PROPS));

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_FIR_LOG = register("stripped_fir_log", () -> new FirLogBlock(FIR_STRIPPED_PROPS));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_FIR_WOOD = register("stripped_fir_wood", () -> new FirLogBlock(FIR_STRIPPED_PROPS));

    public static final DeferredBlock<Block> FIR_PLANKS = register("fir_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final DeferredBlock<StairBlock> FIR_STAIRS = register("fir_stairs", () -> new StairBlock(FIR_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get())));
    public static final DeferredBlock<SlabBlock> FIR_SLAB = register("fir_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get())));

    public static final DeferredBlock<DoorBlock> FIR_DOOR =  registerDoubleBlockItem("fir_door", () -> new DoorBlock(Woodset.FIR_SET, BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> FIR_TRAPDOOR = register("fir_trapdoor", () -> new TrapDoorBlock(Woodset.FIR_SET, BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).strength(3.0F).noOcclusion()));

    public static final DeferredBlock<ButtonBlock> FIR_BUTTON = register("fir_button", () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).noCollission().strength(0.5F)));
    public static final DeferredBlock<PressurePlateBlock> FIR_PRESSURE_PLATE = register("fir_pressure_plate", () -> new PressurePlateBlock(Woodset.FIR_SET, BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).forceSolidOn().noCollission().strength(0.5F)));

    public static final DeferredBlock<FenceBlock> FIR_FENCE = register("fir_fence", () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get())));
    public static final DeferredBlock<FenceGateBlock> FIR_FENCE_GATE = register("fir_fence_gate", () -> new FenceGateBlock(Woodset.FIR_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).forceSolidOn()));

    public static final DeferredBlock<LeavesBlock> FIR_LEAVES = register("fir_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).ignitedByLava().pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().noOcclusion().sound(SoundType.AZALEA_LEAVES).isSuffocating((state, getter, pos) -> false).isViewBlocking((state, getter, pos) -> false).isRedstoneConductor((state, level, pos) -> false)));

    public static final DeferredBlock<SaplingBlock> FIR_SAPLING = register("fir_sapling", () -> new SaplingBlock(TreeGrower.OAK, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).instabreak().sound(SoundType.GRASS).noCollission().randomTicks()));;
    public static final DeferredBlock<FlowerPotBlock> POTTED_FIR_SAPLING = BLOCKS.register("potted_fir_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, FIR_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion()));

    public static final DeferredBlock<StandingSignBlock> FIR_SIGN = BLOCKS.register("fir_sign", () -> new FirStandingSignBlock(Woodset.FIR_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).strength(3.0F).noOcclusion().noCollission()));
    public static final DeferredBlock<WallSignBlock> FIR_WALL_SIGN = BLOCKS.register("fir_wall_sign", () -> new FirWallSignBlock(Woodset.FIR_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).strength(3.0F).noOcclusion().noCollission()));

    public static final DeferredBlock<CeilingHangingSignBlock> FIR_HANGING_SIGN = BLOCKS.register("fir_hanging_sign", () -> new FirCeilingHangingSignBlock(Woodset.FIR_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).noCollission().strength(1.0F)));
    public static final DeferredBlock<WallHangingSignBlock> FIR_WALL_HANGING_SIGN = BLOCKS.register("fir_wall_hanging_sign", () -> new FirWallHangingSignBlock(Woodset.FIR_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).noCollission().strength(1.0F)));

    public static final DeferredBlock<Block> LOAM = register("loam", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).strength(1.2F, 3f).sound(SoundType.PACKED_MUD)));
    public static final DeferredBlock<StairBlock> LOAM_STAIRS = register("loam_stairs", () -> new StairBlock(LOAM.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LOAM.get())));
    public static final DeferredBlock<SlabBlock> LOAM_SLAB = register("loam_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LOAM.get())));
    public static final DeferredBlock<WallBlock> LOAM_WALL = register("loam_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(LOAM.get())));

    public static final DeferredBlock<Block> LOAM_BRICKS = register("loam_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(LOAM.get()).sound(SoundType.MUD_BRICKS)));
    public static final DeferredBlock<StairBlock> LOAM_BRICK_STAIRS = register("loam_brick_stairs", () -> new StairBlock(LOAM_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LOAM_BRICKS.get())));
    public static final DeferredBlock<SlabBlock> LOAM_BRICK_SLAB = register("loam_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LOAM_BRICKS.get())));
    public static final DeferredBlock<WallBlock> LOAM_BRICK_WALL = register("loam_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(LOAM_BRICKS.get())));

    public static final DeferredBlock<Block> LOAM_TILES = register("loam_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(LOAM.get()).sound(SoundType.MUD_BRICKS)));
    public static final DeferredBlock<StairBlock> LOAM_TILE_STAIRS = register("loam_tile_stairs", () -> new StairBlock(LOAM_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LOAM_TILES.get())));
    public static final DeferredBlock<SlabBlock> LOAM_TILE_SLAB = register("loam_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LOAM_TILES.get())));
    public static final DeferredBlock<WallBlock> LOAM_TILE_WALL = register("loam_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(LOAM_TILES.get())));

    public static final DeferredBlock<Block> MOSS_SPROUTS = register("moss_sprouts", () -> new MossSproutsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.MOSS_CARPET).noLootTable()));
    // TOMBSTONE

    public static final DeferredBlock<FlowerPotBlock> POTTED_POINTED_DRIPSTONE = BLOCKS.register("potted_pointed_dripstone", () -> new FlowerPotBlock(Blocks.POINTED_DRIPSTONE, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ACACIA_SAPLING).noOcclusion()));



    public static <T extends Block> DeferredBlock<T> register(String name, Supplier<T> block) {
        DeferredBlock<T> ret = BLOCKS.register(name, block);
        NWItems.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties()));
        return ret;
    }


    private static BlockBehaviour.Properties logProperties(MapColor color) {
        return BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(color);
    }
    private static BlockBehaviour.Properties logProperties(MapColor top, MapColor side) {
        return BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor((state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side);

    }
    public static <T extends Block> DeferredBlock<T> registerDoubleBlockItem(String name, Supplier<T> block) {
        DeferredBlock<T> ret = BLOCKS.register(name, block);
        NWItems.ITEMS.register(name, () -> new DoubleHighBlockItem(ret.get(), new Item.Properties()));
        return ret;
    }



}
