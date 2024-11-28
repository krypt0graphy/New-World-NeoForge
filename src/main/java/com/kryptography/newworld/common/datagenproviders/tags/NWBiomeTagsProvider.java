package com.kryptography.newworld.common.datagenproviders.tags;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.data.tags.NWBiomeTags;
import com.kryptography.newworld.init.worldgen.NWBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class NWBiomeTagsProvider extends BiomeTagsProvider {
    public NWBiomeTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, NewWorld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(NWBiomeTags.HAS_BURIED_BUNKER)
                .add(Biomes.PLAINS, Biomes.MEADOW);
        this.tag(BiomeTags.IS_MOUNTAIN)
                .add(NWBiomes.WOODED_MEADOW);
        this.tag(BiomeTags.HAS_VILLAGE_TAIGA)
                .add(NWBiomes.WOODED_MEADOW);
        this.tag(BiomeTags.HAS_MINESHAFT)
                .add(NWBiomes.WOODED_MEADOW);
        this.tag(BiomeTags.HAS_TRAIL_RUINS)
                .add(NWBiomes.WOODED_MEADOW);
        this.tag(BiomeTags.IS_OVERWORLD)
                .add(NWBiomes.WOODED_MEADOW);
    }
}

