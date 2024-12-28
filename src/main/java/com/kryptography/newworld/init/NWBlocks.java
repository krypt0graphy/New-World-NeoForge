package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.blocks.*;
import com.kryptography.newworld.common.items.TombstoneBlockItem;
import com.kryptography.newworld.common.worldgen.tree.FirTreeGrower;
import com.kryptography.newworld.init.data.woodset.FirBlockSet;
import com.kryptography.newworld.integration.Mods;
import com.kryptography.newworld.integration.FDIntegration;
import com.kryptography.newworld.integration.NMLIntegration;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class NWBlocks {

    public static final BlockBehaviour.Properties FIR_LOG_PROPS = logProperties(MapColor.WOOD, MapColor.DEEPSLATE).strength(2.0F).sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties FIR_STRIPPED_PROPS = logProperties(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD);
    public static final BlockBehaviour.Properties FIR_WOOD_PROPS = logProperties(MapColor.DEEPSLATE).strength(2.0F).sound(SoundType.WOOD);

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NewWorld.MOD_ID);

    public static final DeferredBlock<RotatedPillarBlock> FIR_LOG = registerWithItem("fir_log", RotatedPillarBlock::new, () -> FIR_LOG_PROPS);
    public static final DeferredBlock<RotatedPillarBlock> FIR_WOOD = registerWithItem("fir_wood", RotatedPillarBlock::new, () -> FIR_WOOD_PROPS);

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_FIR_LOG = registerWithItem("stripped_fir_log", RotatedPillarBlock::new, () -> FIR_STRIPPED_PROPS);
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_FIR_WOOD = registerWithItem("stripped_fir_wood", RotatedPillarBlock::new, () -> FIR_STRIPPED_PROPS);

    public static final DeferredBlock<Block> FIR_PLANKS = registerWithItem("fir_planks", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final DeferredBlock<StairBlock> FIR_STAIRS = registerWithItem("fir_stairs", p -> new StairBlock(FIR_PLANKS.get().defaultBlockState(), p), () -> BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()));
    public static final DeferredBlock<SlabBlock> FIR_SLAB = registerWithItem("fir_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()));

    public static final DeferredBlock<DoorBlock> FIR_DOOR =  registerDoubleBlockItem("fir_door", p -> new DoorBlock(FirBlockSet.FIR_SET, p), () -> BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).strength(3.0F).sound(SoundType.WOOD).noOcclusion());
    public static final DeferredBlock<TrapDoorBlock> FIR_TRAPDOOR = registerWithItem("fir_trapdoor", p -> new TrapDoorBlock(FirBlockSet.FIR_SET, p), () -> BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).strength(3.0F).noOcclusion());

    public static final DeferredBlock<ButtonBlock> FIR_BUTTON = registerWithItem("fir_button", p -> new ButtonBlock(FirBlockSet.FIR_SET, 30, p), ()  -> BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).noCollission().strength(0.5F));
    public static final DeferredBlock<PressurePlateBlock> FIR_PRESSURE_PLATE = registerWithItem("fir_pressure_plate", p -> new PressurePlateBlock(FirBlockSet.FIR_SET, p), () -> BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).forceSolidOn().noCollission().strength(0.5F));

    public static final DeferredBlock<FenceBlock> FIR_FENCE = registerWithItem("fir_fence", FenceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()));
    public static final DeferredBlock<FenceGateBlock> FIR_FENCE_GATE = registerWithItem("fir_fence_gate", p -> new FenceGateBlock(FirBlockSet.FIR_WOOD_TYPE, p), () -> BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).forceSolidOn());

    public static final DeferredBlock<LeavesBlock> FIR_LEAVES = registerWithItem("fir_leaves", LeavesBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).ignitedByLava().pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().noOcclusion().sound(SoundType.AZALEA_LEAVES).isSuffocating((state, getter, pos) -> false).isViewBlocking((state, getter, pos) -> false).isRedstoneConductor((state, level, pos) -> false));

    public static final DeferredBlock<SaplingBlock> FIR_SAPLING = registerWithItem("fir_sapling", p -> new SaplingBlock(FirTreeGrower.FIR, p), () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).instabreak().sound(SoundType.GRASS).noCollission().randomTicks());
    public static final DeferredBlock<FlowerPotBlock> POTTED_FIR_SAPLING = register("potted_fir_sapling", p -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, FIR_SAPLING, p), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion());

    public static final DeferredBlock<StandingSignBlock> FIR_SIGN = register("fir_sign", p -> new StandingSignBlock(FirBlockSet.FIR_WOOD_TYPE, p), () -> BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).strength(3.0F).noOcclusion().noCollission());
    public static final DeferredBlock<WallSignBlock> FIR_WALL_SIGN = register("fir_wall_sign", p -> new WallSignBlock(FirBlockSet.FIR_WOOD_TYPE, p), () -> BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).strength(3.0F).noOcclusion().noCollission());

    public static final DeferredBlock<CeilingHangingSignBlock> FIR_HANGING_SIGN = register("fir_hanging_sign", p -> new CeilingHangingSignBlock(FirBlockSet.FIR_WOOD_TYPE, p), () -> BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).noCollission().strength(1.0F));
    public static final DeferredBlock<WallHangingSignBlock> FIR_WALL_HANGING_SIGN = register("fir_wall_hanging_sign", p -> new WallHangingSignBlock(FirBlockSet.FIR_WOOD_TYPE, p), () -> BlockBehaviour.Properties.ofFullCopy(FIR_PLANKS.get()).noCollission().strength(1.0F));

    public static final DeferredBlock<Block> LOAM = registerWithItem("loam", Block::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).strength(1.2F, 3f).sound(SoundType.PACKED_MUD));
    public static final DeferredBlock<StairBlock> LOAM_STAIRS = registerWithItem("loam_stairs", p -> new StairBlock(LOAM.get().defaultBlockState(), p), () ->  BlockBehaviour.Properties.ofFullCopy(LOAM.get()));
    public static final DeferredBlock<SlabBlock> LOAM_SLAB = registerWithItem("loam_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(LOAM.get()));
    public static final DeferredBlock<WallBlock> LOAM_WALL = registerWithItem("loam_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(LOAM.get()));

    public static final DeferredBlock<Block> LOAM_BRICKS = registerWithItem("loam_bricks", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(LOAM.get()).sound(SoundType.MUD_BRICKS));
    public static final DeferredBlock<StairBlock> LOAM_BRICK_STAIRS = registerWithItem("loam_brick_stairs", p -> new StairBlock(LOAM_BRICKS.get().defaultBlockState(), p), () -> BlockBehaviour.Properties.ofFullCopy(LOAM_BRICKS.get()));
    public static final DeferredBlock<SlabBlock> LOAM_BRICK_SLAB = registerWithItem("loam_brick_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(LOAM_BRICKS.get()));
    public static final DeferredBlock<WallBlock> LOAM_BRICK_WALL = registerWithItem("loam_brick_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(LOAM_BRICKS.get()));

    public static final DeferredBlock<Block> LOAM_TILES = registerWithItem("loam_tiles", Block::new, () -> BlockBehaviour.Properties.ofFullCopy(LOAM.get()).sound(SoundType.MUD_BRICKS));
    public static final DeferredBlock<StairBlock> LOAM_TILE_STAIRS = registerWithItem("loam_tile_stairs", p -> new StairBlock(LOAM_TILES.get().defaultBlockState(), p), () -> BlockBehaviour.Properties.ofFullCopy(LOAM_TILES.get()));
    public static final DeferredBlock<SlabBlock> LOAM_TILE_SLAB = registerWithItem("loam_tile_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(LOAM_TILES.get()));
    public static final DeferredBlock<WallBlock> LOAM_TILE_WALL = registerWithItem("loam_tile_wall", WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(LOAM_TILES.get()));

    public static final DeferredBlock<Block> MOSS_SPROUTS = registerWithItem("moss_sprouts", MossSproutsBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.MOSS_CARPET));
    public static final DeferredBlock<Block> TOMBSTONE = registerTombstone("tombstone", TombstoneBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(0.7F, 1200).pushReaction(PushReaction.IGNORE).isSuffocating(((pState, pLevel, pPos) -> false)).sound(SoundType.POLISHED_DEEPSLATE).noOcclusion());
    public static final DeferredBlock<FlowerPotBlock> POTTED_POINTED_DRIPSTONE = register("potted_pointed_dripstone", p -> new FlowerPotBlock(Blocks.POINTED_DRIPSTONE, p), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ACACIA_SAPLING).noOcclusion());

    //Farmer's Delight
    //public static final Optional<DeferredBlock<Block>> FIR_CABINET = Mods.FARMERSDELIGHT.runIfInstalled(() -> (DeferredBlock<Block>) register("fir_cabinet", FDIntegration.cabinetBlock()));

    //No Man's Land
    //public static final Optional<DeferredBlock<Block>> FIR_BOOKSHELF = Mods.NOMANSLAND.runIfInstalled(() -> register("fir_bookshelf", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF))));
    //public static final Optional<DeferredBlock<Block>> TRIMMED_FIR_PLANKS = Mods.NOMANSLAND.runIfInstalled(() -> (DeferredBlock < Block >) register("trimmed_fir_planks", NMLIntegration.trimmedPlanks()));

    public static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
        return BLOCKS.register(name, () -> block.apply(properties.get().setId(ResourceKey.create(Registries.BLOCK, NewWorld.id(name)))));
    }
    public static <T extends Block> DeferredBlock<T> registerWithItem(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
        DeferredBlock<T> ret = BLOCKS.register(name, () -> block.apply(properties.get().setId(ResourceKey.create(Registries.BLOCK, NewWorld.id(name)))));
        NWItems.register(name, itemProps -> new BlockItem(ret.get(), itemProps), () -> new Item.Properties());
        return ret;
    }
    public static <T extends Block> DeferredBlock<T> registerTombstone(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
        DeferredBlock<T> ret = BLOCKS.register(name, () -> block.apply(properties.get().setId(ResourceKey.create(Registries.BLOCK, NewWorld.id(name)))));
        NWItems.register(name, itemProps -> new TombstoneBlockItem(ret.get(), itemProps), () -> new Item.Properties().stacksTo(1));
        return ret;
    }
    public static <T extends Block> DeferredBlock<T> registerDoubleBlockItem(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
        DeferredBlock<T> ret = BLOCKS.register(name, () -> block.apply(properties.get().setId(ResourceKey.create(Registries.BLOCK, NewWorld.id(name)))));
        NWItems.register(name, itemProps -> new DoubleHighBlockItem(ret.get(), itemProps), () -> new Item.Properties());
        return ret;
    }
    private static BlockBehaviour.Properties logProperties(MapColor color) {
        return BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor(color);
    }
    private static BlockBehaviour.Properties logProperties(MapColor top, MapColor side) {
        return BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).mapColor((state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side);

    }
}
