package com.kryptography.newworld.common.blocks;

import com.kryptography.newworld.common.blocks.entity.FirSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class FirStandingSignBlock extends StandingSignBlock {
    public FirStandingSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FirSignBlockEntity(pos, state);
    }
}
