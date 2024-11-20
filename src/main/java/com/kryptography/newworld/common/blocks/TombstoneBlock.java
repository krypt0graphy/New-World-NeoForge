package com.kryptography.newworld.common.blocks;

import com.kryptography.newworld.common.blocks.entity.TombstoneBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.block.model.BlockElementFace;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class TombstoneBlock extends BaseEntityBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty CRACKED = BlockStateProperties.CRACKED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
//
//
//
//    public static final VoxelShape DOWN = Block.box(0, 0, 0, 16, 9, 16);
//    public static final VoxelShape UP = Block.box(0, 7, 0, 16, 16, 16);
//    public static final VoxelShape SOUTH = Block.box(0, 0, 0, 16, 16, 9);
//    public static final VoxelShape NORTH = Block.box(0, 0, 7, 16, 16, 16);
//    public static final VoxelShape EAST = Block.box(0, 0, 0, 9, 16, 16);
//    public static final VoxelShape WEST = Block.box(7, 0, 0, 16, 16, 16);
//
//    public static final MapCodec<TombstoneBlock> CODEC = simpleCodec(TombstoneBlock::new);
//
//    public TombstoneBlock(Properties p_49795_) {
//        super(p_49795_);
//        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
//    }
//
//    @Override
//    protected MapCodec<? extends BaseEntityBlock> codec() {
//        return null;
//    }
//
//
//    @Override
//    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
//        return UP;
//    }
//
//    @Override
//    protected RenderShape getRenderShape(BlockState pState) {
//        return RenderShape.MODEL;
//    }
//
//    @Override
//    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
//        pBuilder.add(WATERLOGGED, CRACKED);
//    }
//
//    @Override
//    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
//        return new TombstoneBlockEntity(pPos, pState);
//    }
//
//    @Override
//    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
//        if (pState.getBlock() != pNewState.getBlock()) {
//            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
//            if (blockEntity instanceof TombstoneBlockEntity) {
//                Containers.dropContentsOnDestroy(pState, pNewState, pLevel, pPos);
//            }
//        }
//        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
//    }
//
//    @Override
//    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
//        if (pLevel.isClientSide) {
//            return InteractionResult.SUCCESS;
//        } else {
//            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
//            if (blockentity instanceof TombstoneBlockEntity) {
//                pPlayer.openMenu((TombstoneBlockEntity)blockentity);
//            }
//            return InteractionResult.CONSUME;
//        }    }

    public static final MapCodec<TombstoneBlock> CODEC = simpleCodec(TombstoneBlock::new);


    @Override
    public MapCodec<TombstoneBlock> codec() {
        return CODEC;
    }

    public TombstoneBlock(BlockBehaviour.Properties p_49046_) {
        super(p_49046_);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(WATERLOGGED, false)
                        .setValue(CRACKED, false)
                        .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @org.jetbrains.annotations.Nullable Entity entity) {
        return state.getValue(CRACKED) ? SoundType.DEEPSLATE_TILES : super.getSoundType(state, level, pos, entity);
    }


    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        Containers.dropContentsOnDestroy(pState, pNewState, pLevel, pPos);
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TombstoneBlockEntity(pPos, pState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(WATERLOGGED, CRACKED, FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getNearestLookingDirection().getOpposite());
    }
}
