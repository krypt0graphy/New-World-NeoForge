package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.blocks.entity.FirHangingSignBlockEntity;
import com.kryptography.newworld.common.blocks.entity.FirSignBlockEntity;
import com.kryptography.newworld.common.blocks.entity.TombstoneBlockEntity;
import com.kryptography.newworld.integration.Mods;
import com.kryptography.newworld.integration.FDIntegration;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class NWBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, NewWorld.MOD_ID);


    public static final RegistryObject<BlockEntityType<FirSignBlockEntity>> FIR_SIGN_BLOCK_ENTITY = BLOCK_ENTITIES.register("fir_sign", () -> BlockEntityType.Builder.of(FirSignBlockEntity::new, NWBlocks.FIR_SIGN.get(), NWBlocks.FIR_WALL_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<FirHangingSignBlockEntity>> FIR_HANGING_SIGN_BLOCK_ENTITY = BLOCK_ENTITIES.register("fir_hanging_sign", () -> BlockEntityType.Builder.of(FirHangingSignBlockEntity::new, NWBlocks.FIR_HANGING_SIGN.get(), NWBlocks.FIR_WALL_HANGING_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<TombstoneBlockEntity>> TOMBSTONE = BLOCK_ENTITIES.register("tombstone", () -> BlockEntityType.Builder.of(TombstoneBlockEntity::new, NWBlocks.TOMBSTONE.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> FIR_CABINET = Mods.FARMERSDELIGHT.isLoaded() ? BLOCK_ENTITIES.register("fir_cabinet", FDIntegration.cabinetBlockEntity()) : null;
}
