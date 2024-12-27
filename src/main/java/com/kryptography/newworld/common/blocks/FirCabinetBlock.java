package com.kryptography.newworld.common.blocks;

import com.kryptography.newworld.common.blocks.entity.FirCabinetBlockEntity;
import com.kryptography.newworld.init.NWBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
//import vectorwing.farmersdelight.common.block.CabinetBlock;
//import vectorwing.farmersdelight.common.block.entity.CabinetBlockEntity;

public class FirCabinetBlock /* extends CabinetBlock */ {
//    public FirCabinetBlock(Properties properties) {
//        super(properties);
//    }
//
//    @Override
//    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
//        if (!level.isClientSide) {
//            BlockEntity tile = level.getBlockEntity(pos);
//            if (tile instanceof FirCabinetBlockEntity) {
//                player.openMenu((CabinetBlockEntity)tile);
//            }
//        }
//
//        return InteractionResult.SUCCESS;
//    }
//
//    @Override
//    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
//        BlockEntity tileEntity = level.getBlockEntity(pos);
//        if (tileEntity instanceof FirCabinetBlockEntity) {
//            ((CabinetBlockEntity)tileEntity).recheckOpen();
//        }
//    }
//
//    @Override
//    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
//        return NWBlockEntityTypes.FIR_CABINET.get().create(pos, state);
//    }
}
