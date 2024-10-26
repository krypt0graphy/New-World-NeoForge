package com.kryptography.newworld.common.blocks.entity;

import com.kryptography.newworld.init.NWBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FirSignBlockEntity extends SignBlockEntity {
    public FirSignBlockEntity(BlockPos pos, BlockState state) {
        super(NWBlockEntityTypes.FIR_SIGN_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return NWBlockEntityTypes.FIR_SIGN_BLOCK_ENTITY.get();
    }
}
