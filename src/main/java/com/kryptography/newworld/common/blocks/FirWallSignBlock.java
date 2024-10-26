package com.kryptography.newworld.common.blocks;

import com.kryptography.newworld.common.blocks.entity.FirSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class FirWallSignBlock extends WallSignBlock {
    public FirWallSignBlock(WoodType p_58069_, Properties p_58068_) {
        super(p_58069_, p_58068_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new FirSignBlockEntity(pPos, pState);
    }
}
