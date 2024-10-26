package com.kryptography.newworld.client;

import com.google.common.collect.ImmutableMap;
import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.entity.FirBoatEntity;
import com.kryptography.newworld.common.entity.FirChestBoatEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;
import java.util.stream.Stream;

public class NWBoatRenderer extends BoatRenderer {
    
    private final Map<FirBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public NWBoatRenderer(EntityRendererProvider.Context pContext, boolean pChestBoat) {
        super(pContext, pChestBoat);
        this.boatResources = Stream.of(FirBoatEntity.Type.values())
                .collect(ImmutableMap.toImmutableMap(type -> type,
                    type -> Pair.of(ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, getTextureLocation(type, pChestBoat)), this.createBoatModel(pContext, type, pChestBoat)))
                );
    }

    private static String getTextureLocation(FirBoatEntity.Type pType, boolean pChestBoat) {
        return pChestBoat ? "textures/entity/chest_boat/" + pType.getName() + ".png" : "textures/entity/boat/" + pType.getName() + ".png";
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context pContext, FirBoatEntity.Type pType, boolean pChestBoat) {
        ModelLayerLocation modellayerlocation = pChestBoat ? NWBoatRenderer.createChestBoatModelName(pType) : NWBoatRenderer.createBoatModelName(pType);
        ModelPart modelpart = pContext.bakeLayer(modellayerlocation);
        return pChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    public static ModelLayerLocation createBoatModelName(FirBoatEntity.Type pType) {
        return createLocation("boat/" + pType.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(FirBoatEntity.Type pType) {
        return createLocation("chest_boat/" + pType.getName(), "main");
    }

    private static ModelLayerLocation createLocation(String pPath, String pModel) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, pPath), pModel);
    }


    @Override
    public Pair<ResourceLocation, net.minecraft.client.model.ListModel<Boat>> getModelWithLocation(Boat boat) {
        if (boat instanceof FirBoatEntity firBoat) {
            return this.boatResources.get(firBoat.getModVariant());
        } else if (boat instanceof FirChestBoatEntity firChestBoat) {
            return this.boatResources.get(firChestBoat.getModVariant());
        } else {
            return null;
        }
    }
}
