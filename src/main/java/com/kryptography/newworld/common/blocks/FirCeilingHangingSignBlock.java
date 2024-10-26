package com.kryptography.newworld.common.blocks;

import com.kryptography.newworld.common.blocks.entity.FirHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class FirCeilingHangingSignBlock extends CeilingHangingSignBlock {
    public FirCeilingHangingSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FirHangingSignBlockEntity(pos, state);
    }
}
