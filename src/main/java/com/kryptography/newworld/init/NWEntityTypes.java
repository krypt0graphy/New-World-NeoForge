package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.entity.FirBoatEntity;
import com.kryptography.newworld.common.entity.FirChestBoatEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class NWEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, NewWorld.MOD_ID);

    public static final RegistryObject<EntityType<FirBoatEntity>> FIR_BOAT = ENTITIES.register("fir_boat",  () -> EntityType.Builder.<FirBoatEntity>of(FirBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build("fir_boat"));
    public static final RegistryObject<EntityType<FirChestBoatEntity>> FIR_CHEST_BOAT = ENTITIES.register("fir_chest_boat",  () -> EntityType.Builder.<FirChestBoatEntity>of(FirChestBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build("fir_chest_boat"));
}
