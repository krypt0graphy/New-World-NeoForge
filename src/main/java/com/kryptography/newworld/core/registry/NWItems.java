package com.kryptography.newworld.core.registry;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.common.items.*;
import com.kryptography.newworld.integration.BLIntegration;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.item.BlueprintBoatItem;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.*;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;


public class NWItems {

	public static final ItemSubRegistryHelper ITEMS = NewWorld.REGISTRY_HELPER.getItemSubHelper();

	public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> FIR_BOAT = ITEMS.createBoatAndChestBoatItem("fir", NWBlocks.FIR_PLANKS);
	public static final DeferredItem<Item> FIR_FURNACE_BOAT = ITEMS.createItem("fir_furnace_boat", ModList.get().isLoaded("boatload") ? BLIntegration.FIR_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> LARGE_FIR_BOAT = ITEMS.createItem("large_fir_boat", ModList.get().isLoaded("boatload") ? BLIntegration.LARGE_FIR_BOAT : () -> new Item(new Item.Properties()));

	public static final DeferredItem<TombstoneBlockItem> TOMBSTONE = ITEMS.createItem("tombstone", () -> new TombstoneBlockItem(NWBlocks.TOMBSTONE.get(), new Item.Properties().stacksTo(1)));

	public static final DeferredItem<AncientMattockItem> ANCIENT_MATTOCK = ITEMS.createItem("ancient_mattock", () -> new AncientMattockItem(AncientToolTier.ANCIENT, new Item.Properties().attributes(AncientMattockItem.createAttributes(AncientToolTier.ANCIENT, 0.0F, -3.0F))));

	public static final DeferredItem<Item> OMINOUS_TOME = ITEMS.createItem("illager_tome", () -> new OminousTomeItem(new Item.Properties().rarity(Rarity.EPIC)));

	public static final DeferredItem<Item> MATTOCK_CRAFTING_TEMPLATE = ITEMS.createItem("mattock_crafting_template", () -> new AncientSmithingTemplateItem("mattock_crafting"));
	public static final DeferredItem<Item> MATTOCK_CRAFTING_TEMPLATE_HEAD = ITEMS.createItem("mattock_crafting_template_head", () -> new SmithingTemplatePieceItem(new Item.Properties()));
	public static final DeferredItem<Item> MATTOCK_CRAFTING_TEMPLATE_SHAFT = ITEMS.createItem("mattock_crafting_template_shaft", () -> new SmithingTemplatePieceItem(new Item.Properties()));
}
