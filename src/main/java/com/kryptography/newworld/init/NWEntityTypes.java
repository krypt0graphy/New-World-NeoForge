package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class NWEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, NewWorld.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<Boat>> FIR_BOAT = register("fir_boat", EntityType.Builder.<Boat>of((type, level) -> new Boat(type, level, NWItems.FIR_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> FIR_CHEST_BOAT = register("fir_chest_boat", EntityType.Builder.<ChestBoat>of((type, level) -> new ChestBoat(type, level, NWItems.FIR_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));



    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> register(String name, EntityType.Builder<E> builder) {
        return ENTITIES.register(name, () -> builder.build(key(name)));
    }

    private static ResourceKey<EntityType<?>> key(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE, NewWorld.id(name));
    }
}
