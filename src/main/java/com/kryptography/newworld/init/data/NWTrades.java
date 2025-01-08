package com.kryptography.newworld.init.data;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = NewWorld.MOD_ID)
public class NWTrades {
    @SubscribeEvent
    public static void onWandererTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> commonTrades = event.getGenericTrades();
        commonTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 5),
                new ItemStack(NWBlocks.FIR_SAPLING.get().asItem(),1),
                8,1,1));

    }

}
