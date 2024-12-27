package com.kryptography.newworld.client;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWEntityTypes;
import com.kryptography.newworld.init.data.woodset.FirBlockSet;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = NewWorld.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class NWClientEvents {



    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent evt) {
        evt.enqueueWork(() -> {
            Sheets.addWoodType(FirBlockSet.FIR_WOOD_TYPE);
        });
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        LayerDefinition boatLayerDefinition = BoatModel.createBoatModel();
        LayerDefinition chestBoatLayerDefinition = BoatModel.createChestBoatModel();

        event.registerLayerDefinition(NWModelLayers.FIR_BOAT, () -> boatLayerDefinition);
        event.registerLayerDefinition(NWModelLayers.FIR_CHEST_BOAT, () -> chestBoatLayerDefinition);

    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(NWEntityTypes.FIR_BOAT.get(), context -> new BoatRenderer(context, NWModelLayers.FIR_BOAT));
        event.registerEntityRenderer(NWEntityTypes.FIR_CHEST_BOAT.get(), context -> new BoatRenderer(context, NWModelLayers.FIR_CHEST_BOAT));
    }
}
