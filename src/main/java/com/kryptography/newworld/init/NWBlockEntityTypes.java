package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.blocks.entity.TombstoneBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NWBlockEntityTypes {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, NewWorld.MOD_ID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TombstoneBlockEntity>> TOMBSTONE = BLOCK_ENTITIES.register("tombstone", () -> BlockEntityType.Builder.of(TombstoneBlockEntity::new, NWBlocks.TOMBSTONE.get()).build(null));

	public static void addBlockEntities(BlockEntityTypeAddBlocksEvent event) {
		event.modify(
				BlockEntityType.SIGN,
				NWBlocks.FIR_SIGN.get(), NWBlocks.FIR_WALL_SIGN.get()
		);
		event.modify(
				BlockEntityType.HANGING_SIGN,
				NWBlocks.FIR_HANGING_SIGN.get(), NWBlocks.FIR_WALL_HANGING_SIGN.get()
		);
	}
}
