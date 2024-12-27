package com.kryptography.newworld.init;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.common.items.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;


public class NWItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NewWorld.MOD_ID);
    public static final DeferredItem<Item> FIR_SIGN = register("fir_sign", properties -> new SignItem(NWBlocks.FIR_SIGN.get(), NWBlocks.FIR_WALL_SIGN.get(), properties), () -> new Item.Properties().stacksTo(16));
    public static final DeferredItem<Item> FIR_HANGING_SIGN = register("fir_hanging_sign", properties -> new HangingSignItem(NWBlocks.FIR_HANGING_SIGN.get(), NWBlocks.FIR_WALL_HANGING_SIGN.get(), properties), () -> new Item.Properties().stacksTo(16));

    public static final DeferredItem<Item> FIR_BOAT = register("fir_boat", properties -> new BoatItem(NWEntityTypes.FIR_BOAT.get(), properties), () -> new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> FIR_CHEST_BOAT = register("fir_chest_boat", properties -> new BoatItem(NWEntityTypes.FIR_CHEST_BOAT.get(), properties), () -> new Item.Properties().stacksTo(1));

    public static final DeferredItem<AncientMattockItem> ANCIENT_MATTOCK = register("ancient_mattock", properties -> new AncientMattockItem(AncientToolTier.ANCIENT, 0.0f, -3.0F, properties), () -> new Item.Properties());

    public static final DeferredItem<Item> ILLAGER_TOME = register("illager_tome", properties -> new IllagerTomeItem(properties), () -> new Item.Properties().rarity(Rarity.EPIC));

    public static final DeferredItem<Item> MATTOCK_CRAFTING_TEMPLATE = register("mattock_crafting_template", properties -> new AncientSmithingTemplateItem("mattock_crafting", properties), () -> new Item.Properties());
    public static final DeferredItem<Item> MATTOCK_CRAFTING_TEMPLATE_HEAD = register("mattock_crafting_template_head", properties -> new SmithingTemplatePieceItem(properties), () -> new Item.Properties());
    public static final DeferredItem<Item> MATTOCK_CRAFTING_TEMPLATE_SHAFT = register("mattock_crafting_template_shaft", properties -> new SmithingTemplatePieceItem(properties), () -> new Item.Properties());

    public static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> item, Supplier<Item.Properties> properties) {
        return ITEMS.register(name, () -> item.apply(properties.get().setId(ResourceKey.create(Registries.ITEM, NewWorld.id(name)))));
    }
}