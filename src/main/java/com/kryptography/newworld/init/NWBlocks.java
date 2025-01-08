package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.blocks.*;
import com.kryptography.newworld.common.items.TombstoneBlockItem;
import com.kryptography.newworld.common.worldgen.tree.FirTreeGrower;
import com.kryptography.newworld.init.data.woodset.FirBlockSet;

import com.kryptography.newworld.integration.FDIntegration;
import com.kryptography.newworld.integration.Mods;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class NWBlocks {

    public static final BlockBehaviour.Properties FIR_LOG_PROPS = logProperties(MapColor.WOOD, MapColor.DEEPSLATE).strength(2.0F).sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties FIR_STRIPPED_PROPS = logProperties(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties FIR_WOOD_PROPS = logProperties(MapColor.DEEPSLATE).strength(2.0F).sound(SoundType.WOOD);

    public static final DeferredRegister<net.minecraft.world.level.block.Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NewWorld.MOD_ID);

    public static final RegistryObject<RotatedPillarBlock> FIR_LOG = register("fir_log", () -> new RotatedPillarBlock(FIR_LOG_PROPS));
    public static final RegistryObject<RotatedPillarBlock> FIR_WOOD = register("fir_wood", () -> new RotatedPillarBlock(FIR_WOOD_PROPS));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_FIR_LOG = register("stripped_fir_log", () -> new RotatedPillarBlock(FIR_STRIPPED_PROPS));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_FIR_WOOD = register("stripped_fir_wood", () -> new RotatedPillarBlock(FIR_STRIPPED_PROPS));

    public static final RegistryObject<Block> FIR_PLANKS = register("fir_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<StairBlock> FIR_STAIRS = register("fir_stairs", () -> new StairBlock(FIR_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(FIR_PLANKS.get())));
    public static final RegistryObject<SlabBlock> FIR_SLAB = register("fir_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(FIR_PLANKS.get())));

    public static final RegistryObject<DoorBlock> FIR_DOOR =  registerDoubleBlockItem("fir_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(FIR_PLANKS.get()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), FirBlockSet.FIR_SET));
    public static final RegistryObject<TrapDoorBlock> FIR_TRAPDOOR = register("fir_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(FIR_PLANKS.get()).strength(3.0F).noOcclusion(), FirBlockSet.FIR_SET));

    public static final RegistryObject<ButtonBlock> FIR_BUTTON = register("fir_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(FIR_PLANKS.get()).noCollission().strength(0.5F), FirBlockSet.FIR_SET, 30, true));
    public static final RegistryObject<PressurePlateBlock> FIR_PRESSURE_PLATE = register("fir_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(FIR_PLANKS.get()).forceSolidOn().noCollission().strength(0.5F), FirBlockSet.FIR_SET));

    public static final RegistryObject<FenceBlock> FIR_FENCE = register("fir_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(FIR_PLANKS.get())));
    public static final RegistryObject<FenceGateBlock> FIR_FENCE_GATE = register("fir_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(FIR_PLANKS.get()).forceSolidOn(), FirBlockSet.FIR_WOOD_TYPE));

    public static final RegistryObject<LeavesBlock> FIR_LEAVES = register("fir_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).ignitedByLava().pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().noOcclusion().sound(SoundType.AZALEA_LEAVES).isSuffocating((state, getter, pos) -> false).isViewBlocking((state, getter, pos) -> false).isRedstoneConductor((state, level, pos) -> false)));

    public static final RegistryObject<SaplingBlock> FIR_SAPLING = register("fir_sapling", () -> new SaplingBlock(new FirTreeGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).instabreak().sound(SoundType.GRASS).noCollission().randomTicks()));
    public static final RegistryObject<FlowerPotBlock> POTTED_FIR_SAPLING = BLOCKS.register("potted_fir_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, FIR_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING).noOcclusion()));

    public static final RegistryObject<StandingSignBlock> FIR_SIGN = BLOCKS.register("fir_sign", () -> new FirStandingSignBlock(FirBlockSet.FIR_WOOD_TYPE, BlockBehaviour.Properties.copy(FIR_PLANKS.get()).strength(3.0F).noOcclusion().noCollission()));
    public static final RegistryObject<WallSignBlock> FIR_WALL_SIGN = BLOCKS.register("fir_wall_sign", () -> new FirWallSignBlock(FirBlockSet.FIR_WOOD_TYPE, BlockBehaviour.Properties.copy(FIR_PLANKS.get()).strength(3.0F).noOcclusion().noCollission()));

    public static final RegistryObject<CeilingHangingSignBlock> FIR_HANGING_SIGN = BLOCKS.register("fir_hanging_sign", () -> new FirCeilingHangingSignBlock(FirBlockSet.FIR_WOOD_TYPE, BlockBehaviour.Properties.copy(FIR_PLANKS.get()).noCollission().strength(1.0F)));
    public static final RegistryObject<WallHangingSignBlock> FIR_WALL_HANGING_SIGN = BLOCKS.register("fir_wall_hanging_sign", () -> new FirWallHangingSignBlock(FirBlockSet.FIR_WOOD_TYPE, BlockBehaviour.Properties.copy(FIR_PLANKS.get()).noCollission().strength(1.0F)));

    public static final RegistryObject<Block> LOAM = register("loam", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).strength(1.2F, 3f).sound(SoundType.PACKED_MUD)));
    public static final RegistryObject<StairBlock> LOAM_STAIRS = register("loam_stairs", () -> new StairBlock(LOAM.get().defaultBlockState(), BlockBehaviour.Properties.copy(LOAM.get())));
    public static final RegistryObject<SlabBlock> LOAM_SLAB = register("loam_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LOAM.get())));
    public static final RegistryObject<WallBlock> LOAM_WALL = register("loam_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(LOAM.get())));

    public static final RegistryObject<Block> LOAM_BRICKS = register("loam_bricks", () -> new Block(BlockBehaviour.Properties.copy(LOAM.get()).sound(SoundType.MUD_BRICKS)));
    public static final RegistryObject<StairBlock> LOAM_BRICK_STAIRS = register("loam_brick_stairs", () -> new StairBlock(LOAM_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(LOAM_BRICKS.get())));
    public static final RegistryObject<SlabBlock> LOAM_BRICK_SLAB = register("loam_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LOAM_BRICKS.get())));
    public static final RegistryObject<WallBlock> LOAM_BRICK_WALL = register("loam_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(LOAM_BRICKS.get())));

    public static final RegistryObject<Block> LOAM_TILES = register("loam_tiles", () -> new Block(BlockBehaviour.Properties.copy(LOAM.get()).sound(SoundType.MUD_BRICKS)));
    public static final RegistryObject<StairBlock> LOAM_TILE_STAIRS = register("loam_tile_stairs", () -> new StairBlock(LOAM_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(LOAM_TILES.get())));
    public static final RegistryObject<SlabBlock> LOAM_TILE_SLAB = register("loam_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LOAM_TILES.get())));
    public static final RegistryObject<WallBlock> LOAM_TILE_WALL = register("loam_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(LOAM_TILES.get())));

    public static final RegistryObject<Block> MOSS_SPROUTS = register("moss_sprouts", () -> new MossSproutsBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.MOSS_CARPET)));
    public static final RegistryObject<Block> TOMBSTONE = registerTombstone("tombstone", () -> new TombstoneBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(0.7F, 1200).pushReaction(PushReaction.IGNORE).isSuffocating(((pState, pLevel, pPos) -> false)).sound(SoundType.POLISHED_DEEPSLATE).noOcclusion()));
    public static final RegistryObject<FlowerPotBlock> POTTED_POINTED_DRIPSTONE = BLOCKS.register("potted_pointed_dripstone", () -> new FlowerPotBlock(Blocks.POINTED_DRIPSTONE, BlockBehaviour.Properties.copy(Blocks.POTTED_ACACIA_SAPLING).noOcclusion()));

    //Farmer's Delight
    public static final RegistryObject<Block> FIR_CABINET = (RegistryObject<Block>) register("fir_cabinet", Mods.FARMERSDELIGHT.isLoaded() ? () -> FDIntegration.cabinetBlock() : () -> new Block(BlockBehaviour.Properties.of()));

    public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = BLOCKS.register(name, block);
        NWItems.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties()));
        return ret;
    }

    public static <T extends Block> RegistryObject<T> registerTombstone(String name, Supplier<T> block) {
        RegistryObject<T> ret = BLOCKS.register(name, block);
        NWItems.ITEMS.register(name, () -> new TombstoneBlockItem(ret.get(), new Item.Properties().stacksTo(1)));
        return ret;
    }


    private static BlockBehaviour.Properties logProperties(MapColor color) {
        return BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(color);
    }
    private static BlockBehaviour.Properties logProperties(MapColor top, MapColor side) {
        return BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor((state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side);

    }
    public static <T extends Block> RegistryObject<T> registerDoubleBlockItem(String name, Supplier<T> block) {
        RegistryObject<T> ret = BLOCKS.register(name, block);
        NWItems.ITEMS.register(name, () -> new DoubleHighBlockItem(ret.get(), new Item.Properties()));
        return ret;
    }



}
