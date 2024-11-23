package com.kryptography.newworld.mixin;


import com.kryptography.newworld.common.blocks.TombstoneBlock;
import com.kryptography.newworld.common.blocks.entity.TombstoneBlockEntity;
import com.kryptography.newworld.init.NWBlockEntityTypes;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.data.NWStats;
import com.kryptography.newworld.init.data.tags.NWBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(Inventory.class)
public abstract class PlayerDeathMixin {
    private static final Logger log = LoggerFactory.getLogger(PlayerDeathMixin.class);
    @Shadow @Final public Player player;
    @Shadow @Final private List<NonNullList<ItemStack>> compartments;

    @Inject(method = "dropAll", at = @At("HEAD"))
    private void method(CallbackInfo ci) {
        Level level = player.level();
        BlockPos pos = getValidPos(level, player.blockPosition());
        if (pos != null && hasTombstone()) {
        if (!(level.getBlockState(pos).getBlock() instanceof TombstoneBlock)) {
            level.playSound(player, pos, level.getBlockState(pos).getSoundType(level, pos, player).getBreakSound(), SoundSource.BLOCKS);
            level.setBlockAndUpdate(pos, NWBlocks.TOMBSTONE.get().defaultBlockState().setValue(BlockStateProperties.CRACKED, true));
        }

            level.getBlockEntity(pos, NWBlockEntityTypes.TOMBSTONE.get()).ifPresent(tombstone -> {
                player.awardStat(NWStats.TOMBSTONE_ACTIVATION.get(), 1);

                boolean decrementedTombstone = false;
                player.captureDrops(new ArrayList<>());
                int t = -2;

                for (NonNullList<ItemStack> itemStacks : compartments) {
                    for (int i = 0; i < itemStacks.size(); ++i) {
                        boolean skip = false;
                        ItemStack itemStack = itemStacks.get(i);
                        if (!itemStack.isEmpty()) {
                            if (itemStack.is(NWBlocks.TOMBSTONE.asItem()) && !decrementedTombstone) {
                                t = i;
                                itemStacks.set(i, ItemStack.EMPTY);
                                decrementedTombstone = true;
                                skip = decrementedTombstone;
                            }

                            if (level instanceof ServerLevel &&!skip ) {
                                placeOrDropStack(level, tombstone, itemStack);
                            }
                            if (!itemStack.isEmpty() && !skip) {
                                itemStacks.set(i, ItemStack.EMPTY);
                            }
                        }
                    }
                }
            });
        }
    }

    @Unique
    private void placeOrDropStack(Level world, TombstoneBlockEntity tombstoneEntity, ItemStack currentStack) {
        int compatibleSlot = tombstoneEntity.getCompatibleSlot(currentStack);

        if (!currentStack.isEmpty() && compatibleSlot >= 0) {
            ItemStack tombstoneStack = tombstoneEntity.getItem(compatibleSlot);

            if (tombstoneStack.isEmpty()) {
                tombstoneEntity.setItem(compatibleSlot, currentStack);
            }

            else if (ItemStack.isSameItemSameComponents(tombstoneStack, currentStack)) {
                if (tombstoneStack.getCount() + currentStack.getCount() > tombstoneStack.getMaxStackSize()) {
                    int maxSize = tombstoneStack.getMaxStackSize();
                    currentStack.setCount(tombstoneStack.getCount() + currentStack.getCount() - tombstoneStack.getMaxStackSize());
                    tombstoneStack.setCount(maxSize);

                    placeOrDropStack(world, tombstoneEntity, currentStack.copyWithCount(tombstoneStack.getCount() + currentStack.getCount() - tombstoneStack.getMaxStackSize()));
                    currentStack.copyAndClear();
                }
                else {
                    currentStack.setCount(tombstoneStack.getCount() + currentStack.getCount());
                }
            }
        } else {
            tombstoneEntity.setItem(44, currentStack);
        }
    }

    @Unique
    private boolean hasTombstone() {
        for (NonNullList<ItemStack> itemStacks : this.compartments) {
            if (itemStacks.stream().anyMatch(stack -> stack.is(NWBlocks.TOMBSTONE.asItem())) ) {
                return true;
            }
        }
        return false;
    }

    @Unique
    private BlockPos getValidPos(Level level, BlockPos pos) {
        BlockPos finalPos = pos;
        while (finalPos.getY() < pos.getY() + 6) {
            if (level.getBlockState(finalPos).is(NWBlockTags.TOMBSTONE_REPLACEABLE) || level.getBlockState(finalPos).isAir()) {
                return finalPos;
            }
            finalPos = finalPos.above();
        }
        return null;
    }
}
