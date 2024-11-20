package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.blocks.entity.FirHangingSignBlockEntity;
import com.kryptography.newworld.common.blocks.entity.FirSignBlockEntity;
import com.kryptography.newworld.common.blocks.entity.TombstoneBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NWBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, NewWorld.MOD_ID);


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FirSignBlockEntity>> FIR_SIGN_BLOCK_ENTITY = BLOCK_ENTITIES.register("fir_sign", () -> BlockEntityType.Builder.of(FirSignBlockEntity::new, NWBlocks.FIR_SIGN.get(), NWBlocks.FIR_WALL_SIGN.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FirHangingSignBlockEntity>> FIR_HANGING_SIGN_BLOCK_ENTITY = BLOCK_ENTITIES.register("fir_hanging_sign", () -> BlockEntityType.Builder.of(FirHangingSignBlockEntity::new, NWBlocks.FIR_HANGING_SIGN.get(), NWBlocks.FIR_WALL_HANGING_SIGN.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TombstoneBlockEntity>> TOMBSTONE = BLOCK_ENTITIES.register("tombstone", () -> BlockEntityType.Builder.of(TombstoneBlockEntity::new, NWBlocks.TOMBSTONE.get()).build(null));
}
