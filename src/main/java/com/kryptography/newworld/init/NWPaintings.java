package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NWPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, NewWorld.MOD_ID);


    public static final RegistryObject<PaintingVariant> PROVIDENCE = PAINTINGS.register("providence", () -> new PaintingVariant(32, 48));
}
