package com.kryptography.newworld.init.worldgen.structure;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.worldgen.structures.BuriedBunkerFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NWStructureTypes {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, NewWorld.MOD_ID);

    public static final DeferredHolder<StructureType<?>, StructureType<BuriedBunkerFeature>> BURIED_BUNKER = STRUCTURE_TYPES.register("buried_bunker", () -> () -> BuriedBunkerFeature.CODEC);

}
