package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.entity.FirBoatEntity;
import com.kryptography.newworld.common.entity.FirChestBoatEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class NWEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, NewWorld.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<FirBoatEntity>> FIR_BOAT = ENTITIES.register("fir_boat",  () -> EntityType.Builder.<FirBoatEntity>of(FirBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build("fir_boat"));
    public static final DeferredHolder<EntityType<?>, EntityType<FirChestBoatEntity>> FIR_CHEST_BOAT = ENTITIES.register("fir_chest_boat",  () -> EntityType.Builder.<FirChestBoatEntity>of(FirChestBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build("fir_chest_boat"));
}
