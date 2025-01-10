package com.kryptography.newworld.client;


import com.kryptography.newworld.init.NWBlockEntityTypes;
import com.kryptography.newworld.init.NWEntityTypes;
import com.kryptography.newworld.init.data.woodset.FirBlockSet;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = "newworld", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NWEventBusClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        Sheets.addWoodType(FirBlockSet.FIR_WOOD_TYPE);
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
