package com.kryptography.newworld.common.blocks;

import com.kryptography.newworld.common.blocks.entity.FirHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class FirWallHangingSignBlock extends WallHangingSignBlock {
    public FirWallHangingSignBlock(WoodType type, Properties properties) {
        super(properties, type);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new FirHangingSignBlockEntity(pPos, pState);
    }
}
