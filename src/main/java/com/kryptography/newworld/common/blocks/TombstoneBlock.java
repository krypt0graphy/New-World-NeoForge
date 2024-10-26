//package com.kryptography.newworld.common.blocks;
//
//import com.google.common.collect.ImmutableMap;
//import com.kryptography.newworld.common.blocks.entity.TombstoneBlockEntity;
//import com.kryptography.newworld.init.NWBlockEntityTypes;
//import com.mojang.serialization.MapCodec;
//import net.minecraft.client.renderer.block.model.BlockElementFace;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.core.NonNullList;
//import net.minecraft.world.Containers;
//import net.minecraft.world.entity.player.Inventory;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.*;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.state.properties.BlockStateProperties;
//import net.minecraft.world.level.block.state.properties.BooleanProperty;
//import net.minecraft.world.level.block.state.properties.DirectionProperty;
//import net.minecraft.world.level.material.MapColor;
//import net.minecraft.world.level.material.PushReaction;
//import net.minecraft.world.level.storage.loot.LootParams;
//import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
//import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
//import net.minecraft.world.phys.shapes.VoxelShape;
//import org.jetbrains.annotations.Nullable;
//
//import javax.swing.text.html.BlockView;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//public class TombstoneBlock extends Block implements EntityBlock, SimpleWaterloggedBlock {
//    public static final BooleanProperty CRACKED = BlockStateProperties.CRACKED;
//    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
//    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
//    public static final MapCodec<TombstoneBlock> CODEC = simpleCodec(TombstoneBlock::new);
//
//
//    public TombstoneBlock() {
//        super(Properties.of().mapColor(MapColor.DEEPSLATE).strength(0.7F, 1200).pushReaction(PushReaction.IGNORE).sound(SoundType.POLISHED_DEEPSLATE).isSuffocating((pState, pLevel, pPos) -> false).noOcclusion());
//        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
//    }
//
//    @Override
//    public SoundType getSoundType(BlockState state) {
//        return state.getValue(CRACKED) ? SoundType.DEEPSLATE_TILES : super.getSoundType(state);
//    }
//
//    public void onStateReplaced(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved) {
//        if (state.getBlock() != newState.getBlock()) {
//            BlockEntity blockEntity = level.getBlockEntity(pos);
//            if (blockEntity instanceof TombstoneBlockEntity) {
//                dropItems(level, pos, ((TombstoneBlockEntity) blockEntity).getDeath().getAllItems());
//            }
//            super.onRemove(state, level, pos, newState, moved);
//        }
//    }
//
//    public List<ItemStack> getDroppedStacks(BlockState state, LootParams.Builder builder) {
//        if(state.getValue(CRACKED)){
//            return Collections.emptyList();
//        }
//        return super.getDrops(state, builder);
//    }
//
//    public boolean hasComparatorOutput(BlockState state) {
//        return true;
//    }
//
//    public int getComparatorOutput(BlockState state, Lev world, BlockPos pos) {
//        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
//    }
//
//    @Override
//    protected MapCodec<? extends BaseEntityBlock> codec() {
//        return CODEC;
//    }
//
//    @Nullable
//    @Override
//    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
//        return new TombstoneBlockEntity(pos, state);
//    }
//
//    public void dropItems(Level world, BlockPos pos, NonNullList<ItemStack> items) {
//        for (ItemStack item : items) {
//            Containers.dropItemStack(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, item);
//        }
//    }
//
//
//}
