package com.kryptography.newworld.common.datagenproviders.tags;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class NWEntityTagsProvider extends EntityTypeTagsProvider {
    public NWEntityTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, NewWorld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(EntityTypeTags.BOAT).add(
                NWEntityTypes.FIR_BOAT.get()
        );
    }
}
