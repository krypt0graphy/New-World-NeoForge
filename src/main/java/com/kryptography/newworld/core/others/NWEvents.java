package com.kryptography.newworld.core.others;

import com.kryptography.newworld.common.blocks.TombstoneBlock;
import com.kryptography.newworld.common.blocks.entity.TombstoneBlockEntity;
import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.other.tags.NWBlockTags;
import com.kryptography.newworld.core.registry.NWBlockEntityTypes;
import com.kryptography.newworld.core.registry.NWBlocks;
import com.kryptography.newworld.core.registry.NWStats;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

import java.util.Iterator;

@EventBusSubscriber(modid = NewWorld.MOD_ID)
public class NWEvents {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onLivingDrops(LivingDropsEvent event) {
		if (!event.isCanceled() && event.getEntity() instanceof Player player && player.level() instanceof ServerLevel level) {
			ItemEntity tombstoneDrop = event.getDrops().stream()
				.filter(drop -> !drop.getItem().isEmpty() && drop.getItem().is(NWBlocks.TOMBSTONE.asItem()))
				.findFirst().orElse(null);
			if (tombstoneDrop != null) {
				BlockPos pos = getValidPos(level, player.blockPosition());
				if (pos != null) {
					BlockState state = level.getBlockState(pos);
					boolean placingTombstone = !(state.getBlock() instanceof TombstoneBlock);
					if (placingTombstone) {
						level.setBlockAndUpdate(pos, NWBlocks.TOMBSTONE.get().defaultBlockState().setValue(BlockStateProperties.CRACKED, true));
						level.playSound(player, pos, state.getSoundType(level, pos, player).getBreakSound(), SoundSource.BLOCKS);
					}
					TombstoneBlockEntity tombstone = level.getBlockEntity(pos, NWBlockEntityTypes.TOMBSTONE.get()).orElse(null);
					if (tombstone != null) {
						ItemStack tombstoneStack = tombstoneDrop.getItem().copy();
						tombstoneStack.shrink(1);
						if (tombstoneStack.isEmpty()) {
							event.getDrops().remove(tombstoneDrop);
						} else {
							tombstoneDrop.setItem(tombstoneStack);
						}
						player.awardStat(NWStats.TOMBSTONE_ACTIVATION.get(), 1);

						Iterator<ItemEntity> drops = event.getDrops().iterator();
						while (drops.hasNext()) {
							ItemEntity drop = drops.next();
							ItemStack remaining = tombstone.storeItem(drop.getItem());
							if (remaining.isEmpty()) {
								drops.remove();
							} else if (remaining.getCount() != drop.getItem().getCount()) {
								drop.setItem(remaining);
							}
						}
					}
				}
			}
		}
	}

	private static BlockPos getValidPos(ServerLevel level, BlockPos pos) {
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
