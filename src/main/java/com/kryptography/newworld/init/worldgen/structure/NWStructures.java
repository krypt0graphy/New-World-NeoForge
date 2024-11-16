package com.kryptography.newworld.init.worldgen.structure;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.worldgen.structures.BuriedBunkerFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NWStructures {

    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, NewWorld.MOD_ID);

    public static final DeferredHolder<StructureType<?>, StructureType<BuriedBunkerFeature>> BURIED_BUNKER = STRUCTURES.register("buried_bunker", () -> () -> BuriedBunkerFeature.CODEC);


}
