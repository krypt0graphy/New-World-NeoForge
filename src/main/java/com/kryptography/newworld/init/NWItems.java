package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.entity.FirBoatEntity;
import com.kryptography.newworld.common.items.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NWItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NewWorld.MOD_ID);
    public static final DeferredItem<Item> FIR_SIGN = ITEMS.register("fir_sign", () -> new SignItem(new Item.Properties().stacksTo(16), NWBlocks.FIR_SIGN.get(), NWBlocks.FIR_WALL_SIGN.get()));
    public static final DeferredItem<Item> FIR_HANGING_SIGN = ITEMS.register("fir_hanging_sign", () -> new HangingSignItem(NWBlocks.FIR_HANGING_SIGN.get(), NWBlocks.FIR_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> FIR_BOAT = ITEMS.register("fir_boat", () -> new FirBoatItem(false, FirBoatEntity.Type.FIR, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> FIR_CHEST_BOAT = ITEMS.register("fir_chest_boat", () -> new FirBoatItem(true, FirBoatEntity.Type.FIR, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<AncientMattockItem> ANCIENT_MATTOCK = ITEMS.register("ancient_mattock", () -> new AncientMattockItem(AncientToolTier.ANCIENT, new Item.Properties().attributes(AncientMattockItem.createAttributes(AncientToolTier.ANCIENT, 0.0F, -3.0F))));

    public static final DeferredItem<Item> ILLAGER_TOME = ITEMS.register("illager_tome", () -> new IllagerTomeItem(new Item.Properties().rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> MATTOCK_CRAFTING_TEMPLATE = ITEMS.register("mattock_crafting_template", () -> new AncientSmithingTemplateItem("mattock_crafting"));
    public static final DeferredItem<Item> MATTOCK_CRAFTING_TEMPLATE_HEAD = ITEMS.register("mattock_crafting_template_head", () -> new SmithingTemplatePieceItem(new Item.Properties()));
    public static final DeferredItem<Item> MATTOCK_CRAFTING_TEMPLATE_SHAFT = ITEMS.register("mattock_crafting_template_shaft", () -> new SmithingTemplatePieceItem(new Item.Properties()));
}
