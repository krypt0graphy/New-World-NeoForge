package com.kryptography.newworld.common.data.providers.tags;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWPaintings;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class NWPaintingVariantTagProvider extends PaintingVariantTagsProvider {
    public NWPaintingVariantTagProvider(PackOutput p_255750_, CompletableFuture<HolderLookup.Provider> p_256184_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_255750_, p_256184_, NewWorld.MOD_ID, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.Provider p_256017_) {
        this.tag(PaintingVariantTags.PLACEABLE).add(NWPaintings.PROVIDENCE.getKey());
    }
}
