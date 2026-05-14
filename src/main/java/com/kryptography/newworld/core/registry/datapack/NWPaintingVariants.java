package com.kryptography.newworld.core.registry.datapack;

import com.kryptography.newworld.core.NewWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class NWPaintingVariants
{
	public static final ResourceKey<PaintingVariant> PROVIDENCE = create("providence");

	public static void bootstrap(BootstrapContext<PaintingVariant> context) {
		register(context, PROVIDENCE, 2, 3);
	}

	private static ResourceKey<PaintingVariant> create(String name) {
		return ResourceKey.create(Registries.PAINTING_VARIANT, NewWorld.id(name));
	}

	private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
		context.register(key, new PaintingVariant(width, height, key.location()));
	}

}
