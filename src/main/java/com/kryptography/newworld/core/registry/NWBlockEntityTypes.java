package com.kryptography.newworld.core.registry;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.common.blocks.entity.TombstoneBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NWBlockEntityTypes {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, NewWorld.MOD_ID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TombstoneBlockEntity>> TOMBSTONE = BLOCK_ENTITIES.register("tombstone", () -> BlockEntityType.Builder.of(TombstoneBlockEntity::new, NWBlocks.TOMBSTONE.get()).build(null));
}