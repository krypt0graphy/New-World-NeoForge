package com.kryptography.newworld.core.data.server;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.registry.datapack.NWPaintingVariants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class NWPaintingVariantTagsProvider extends PaintingVariantTagsProvider {
	public NWPaintingVariantTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, provider, NewWorld.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(PaintingVariantTags.PLACEABLE).add(NWPaintingVariants.PROVIDENCE);
	}
}