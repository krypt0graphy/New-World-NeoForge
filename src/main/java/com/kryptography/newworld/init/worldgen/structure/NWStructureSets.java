package com.kryptography.newworld.init.worldgen.structure;

import com.kryptography.newworld.NewWorld;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class NWStructureSets {

    public static final ResourceKey<StructureSet> BURIED_BUNKER = registerKey("buried_bunker");

    public static void bootstrap(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        context.register(BURIED_BUNKER, new StructureSet(
                structures.getOrThrow(NWStructures.BURIED_BUNKER),
                new RandomSpreadStructurePlacement(24, 4, RandomSpreadType.LINEAR, 1694767080)));
    }



    public static ResourceKey<StructureSet> registerKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, name));
    }
}
