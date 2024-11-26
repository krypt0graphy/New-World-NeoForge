package com.kryptography.newworld.init.worldgen.structure;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.worldgen.structures.BuriedBunkerFeature;
import com.kryptography.newworld.init.data.tags.NWBiomeTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Map;

public class NWStructures {

    public static final ResourceKey<Structure> BURIED_BUNKER = registerKey("buried_bunker");

    public static void bootstrap(BootstrapContext<Structure> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> templatePools = context.lookup(Registries.TEMPLATE_POOL);


        context.register(BURIED_BUNKER, new BuriedBunkerFeature(new Structure.StructureSettings(
                biomes.getOrThrow(NWBiomeTags.HAS_BURIED_BUNKER),
                Map.of(),
                GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
                TerrainAdjustment.NONE
        )));

    }

    public static ResourceKey<Structure> registerKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE,  NewWorld.id(name));
    }
}
