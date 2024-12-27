package com.kryptography.newworld.client;

import com.kryptography.newworld.NewWorld;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NWModelLayers {
   public static final ModelLayerLocation FIR_BOAT = new ModelLayerLocation(NewWorld.id("boat/fir"), "main");
   public static final ModelLayerLocation FIR_CHEST_BOAT = new ModelLayerLocation(NewWorld.id("chest_boat/fir"), "main");
}
