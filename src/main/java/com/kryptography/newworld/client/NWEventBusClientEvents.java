package com.kryptography.newworld.client;


import com.kryptography.newworld.init.NWBlockEntityTypes;
import com.kryptography.newworld.init.NWEntityTypes;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;


@EventBusSubscriber(modid = "newworld", bus = EventBusSubscriber.Bus.MOD)
public class NWEventBusClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(NWEntityTypes.FIR_BOAT.get(), pContext -> new NWBoatRenderer(pContext, false));
        EntityRenderers.register(NWEntityTypes.FIR_CHEST_BOAT.get(), pContext -> new NWBoatRenderer(pContext, true));
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(NWBlockEntityTypes.FIR_HANGING_SIGN_BLOCK_ENTITY.get(), HangingSignRenderer::new);
        event.registerBlockEntityRenderer(NWBlockEntityTypes.FIR_SIGN_BLOCK_ENTITY.get(), SignRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NWModelLayers.FIR_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(NWModelLayers.FIR_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }
}
