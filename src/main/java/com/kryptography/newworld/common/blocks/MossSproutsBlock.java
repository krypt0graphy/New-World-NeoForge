package com.kryptography.newworld.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MossSproutsBlock extends TallGrassBlock implements BonemealableBlock {
    public MossSproutsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getVisualShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
    }


}
