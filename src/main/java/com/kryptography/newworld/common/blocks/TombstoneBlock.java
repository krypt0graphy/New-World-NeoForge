package com.kryptography.newworld.common.blocks;

import com.kryptography.newworld.common.blocks.entity.TombstoneBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class TombstoneBlock extends BaseEntityBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty CRACKED = BlockStateProperties.CRACKED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<AttachFace> FACE = BlockStateProperties.ATTACH_FACE;

    public static final VoxelShape DOWN = Block.box(0, 0, 0, 16, 9, 16);
    public static final VoxelShape UP = Block.box(0, 7, 0, 16, 16 ,16);
    public static final VoxelShape SOUTH = Block.box(0, 0, 0, 16, 16, 9);
    public static final VoxelShape NORTH = Block.box(0, 0, 7, 16, 16 ,16);
    public static final VoxelShape EAST = Block.box(0,0,0,9,16,16);
    public static final VoxelShape WEST = Block.box(7, 0, 0, 16, 16,16);

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
                        .setValue(FACE, AttachFace.FLOOR)
        );
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @org.jetbrains.annotations.Nullable Entity entity) {
        return state.getValue(CRACKED) ? SoundType.DEEPSLATE_TILES : super.getSoundType(state, level, pos, entity);
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch (pState.getValue(FACE)) {
            case FLOOR:
                return DOWN;
            case WALL:
                return switch (direction) {
                    case EAST -> EAST;
                    case WEST -> WEST;
                    case SOUTH -> SOUTH;
                    case NORTH, UP, DOWN -> NORTH;
                };
            case CEILING:
                return UP;
            default:
                return DOWN;
        }
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
        pBuilder.add(WATERLOGGED, CRACKED, FACING, FACE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        for (Direction direction : pContext.getNearestLookingDirections()) {
            BlockState blockstate;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockstate = this.defaultBlockState()
                        .setValue(FACE, direction == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR)
                        .setValue(FACING, pContext.getHorizontalDirection());
            } else {
                blockstate = this.defaultBlockState().setValue(FACE, AttachFace.WALL).setValue(FACING, direction.getOpposite());
            }

            if (blockstate.canSurvive(pContext.getLevel(), pContext.getClickedPos())) {
                return blockstate;
            }
        }

        return null;
    }

}

