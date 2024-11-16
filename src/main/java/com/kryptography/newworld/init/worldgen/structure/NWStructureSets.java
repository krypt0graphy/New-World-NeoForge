package com.kryptography.newworld.init.worldgen.structure;

import com.kryptography.newworld.NewWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class NWStructureSets {

    public static final ResourceKey<StructureSet> BURIED_BUNKER = registerKey("buried_bunker");



    public static void bootstrap(BootstrapContext<StructureSet> context) {


    }



    public static ResourceKey<StructureSet> registerKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, name));
    }
}
