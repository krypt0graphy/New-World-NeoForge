package com.kryptography.newworld.client;

import com.kryptography.newworld.init.NWEntityTypes;
import com.kryptography.newworld.init.data.woodset.FirBlockSet;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class NWClientSetup {
    public static void ClientSetup() {
        Sheets.addWoodType(FirBlockSet.FIR_WOOD_TYPE);
        EntityRenderers.register(NWEntityTypes.FIR_BOAT.get(), pContext -> new NWBoatRenderer(pContext, false));
        EntityRenderers.register(NWEntityTypes.FIR_CHEST_BOAT.get(), pContext -> new NWBoatRenderer(pContext, true));
    }
}
