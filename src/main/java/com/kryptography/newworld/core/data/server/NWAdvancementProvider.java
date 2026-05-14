package com.kryptography.newworld.core.data.server;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.registry.NWItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.AdvancementProvider.AdvancementGenerator;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class NWAdvancementProvider implements AdvancementGenerator {

	public static AdvancementProvider create(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		return new AdvancementProvider(output, provider, helper, List.of(new NWAdvancementProvider()));
	}

	@Override
	public void generate(Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper helper) {
		AdvancementHolder collectTemplateFragment = createAdvancement("collect_template_fragment", "adventure", ResourceLocation.withDefaultNamespace("adventure/salvage_sherd"), NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get(), AdvancementType.TASK, true, true, true)
				.addCriterion("mattock_template", InventoryChangeTrigger.TriggerInstance.hasItems(
						ItemPredicate.Builder.item().of(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD.get(), NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT.get())))
				.save(consumer, NewWorld.MOD_ID + ":adventure/collect_template_fragment");

		createAdvancement("collect_ancient_mattock", "adventure", collectTemplateFragment, NWItems.ANCIENT_MATTOCK.get(), AdvancementType.TASK, true, true, true)
				.addCriterion("ancient_mattock", InventoryChangeTrigger.TriggerInstance.hasItems(NWItems.ANCIENT_MATTOCK.get()))
				.save(consumer, NewWorld.MOD_ID + ":adventure/collect_ancient_mattock");
	}

	private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, ItemLike icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return createAdvancement(name, category, Advancement.Builder.advancement().build(parent), icon, frame, showToast, announceToChat, hidden);
	}

	private static Advancement.Builder createAdvancement(String name, String category, AdvancementHolder parent, ItemLike icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
		return Advancement.Builder.advancement().parent(parent).display(icon,
				Component.translatable("advancements." + category + "." + name + ".title"),
				Component.translatable("advancements." + category + "." + name + ".description"),
				null, frame, showToast, announceToChat, hidden);
	}
}
