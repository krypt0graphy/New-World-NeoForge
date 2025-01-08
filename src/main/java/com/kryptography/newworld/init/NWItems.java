package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.entity.FirBoatEntity;
import com.kryptography.newworld.common.items.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class NWItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NewWorld.MOD_ID);
    public static final RegistryObject<Item> FIR_SIGN = ITEMS.register("fir_sign", () -> new SignItem(new Item.Properties().stacksTo(16), NWBlocks.FIR_SIGN.get(), NWBlocks.FIR_WALL_SIGN.get()));
    public static final RegistryObject<Item> FIR_HANGING_SIGN = ITEMS.register("fir_hanging_sign", () -> new HangingSignItem(NWBlocks.FIR_HANGING_SIGN.get(), NWBlocks.FIR_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> FIR_BOAT = ITEMS.register("fir_boat", () -> new FirBoatItem(false, FirBoatEntity.Type.FIR, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> FIR_CHEST_BOAT = ITEMS.register("fir_chest_boat", () -> new FirBoatItem(true, FirBoatEntity.Type.FIR, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<AncientMattockItem> ANCIENT_MATTOCK = ITEMS.register("ancient_mattock", () -> new AncientMattockItem(0.0F, -3.0F, AncientToolTier.ANCIENT, BlockTags.NEEDS_DIAMOND_TOOL, new Item.Properties()));

    public static final RegistryObject<Item> ILLAGER_TOME = ITEMS.register("illager_tome", () -> new IllagerTomeItem(new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MATTOCK_CRAFTING_TEMPLATE = ITEMS.register("mattock_crafting_template", () -> new AncientSmithingTemplateItem("mattock_crafting"));
    public static final RegistryObject<Item> MATTOCK_CRAFTING_TEMPLATE_HEAD = ITEMS.register("mattock_crafting_template_head", () -> new SmithingTemplatePieceItem(new Item.Properties()));
    public static final RegistryObject<Item> MATTOCK_CRAFTING_TEMPLATE_SHAFT = ITEMS.register("mattock_crafting_template_shaft", () -> new SmithingTemplatePieceItem(new Item.Properties()));
}