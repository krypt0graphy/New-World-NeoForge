package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.blocks.entity.FirHangingSignBlockEntity;
import com.kryptography.newworld.common.blocks.entity.FirSignBlockEntity;
import com.kryptography.newworld.common.blocks.entity.TombstoneBlockEntity;
import com.kryptography.newworld.integration.Mods;
import com.kryptography.newworld.integration.FDIntegration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NWBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, NewWorld.MOD_ID);


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SignBlockEntity>> FIR_SIGN_BLOCK_ENTITY = BLOCK_ENTITIES.register("fir_sign", () -> new BlockEntityType<>(SignBlockEntity::new, NWBlocks.FIR_SIGN.get(), NWBlocks.FIR_WALL_SIGN.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HangingSignBlockEntity>> FIR_HANGING_SIGN_BLOCK_ENTITY = BLOCK_ENTITIES.register("fir_hanging_sign", () -> new BlockEntityType<>(HangingSignBlockEntity::new, NWBlocks.FIR_HANGING_SIGN.get(), NWBlocks.FIR_WALL_HANGING_SIGN.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TombstoneBlockEntity>> TOMBSTONE = BLOCK_ENTITIES.register("tombstone", () -> new BlockEntityType<>(TombstoneBlockEntity::new, NWBlocks.TOMBSTONE.get()));

    //public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> FIR_CABINET = Mods.FARMERSDELIGHT.isLoaded() ? BLOCK_ENTITIES.register("fir_cabinet", FDIntegration.cabinetBlockEntity()) : null;
}
