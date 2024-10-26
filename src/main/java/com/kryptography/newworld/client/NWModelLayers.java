package com.kryptography.newworld.client;

import com.kryptography.newworld.NewWorld;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class NWModelLayers {
    public static final ModelLayerLocation FIR_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, "boat/fir"), "main");
    public static final ModelLayerLocation FIR_CHEST_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, "chest_boat/fir"), "main");
}
